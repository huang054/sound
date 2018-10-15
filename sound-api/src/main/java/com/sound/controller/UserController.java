package com.sound.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import com.sound.common.ParamCode;
import com.sound.common.QiNiuConfig;
import com.sound.common.Response;
import com.sound.config.GitHubLookupService;
import com.sound.config.WebChinese;
import com.sound.model.AudioModel;
import com.sound.model.CucrrencyRecord;
import com.sound.model.DailyTask;
import com.sound.model.Label;
import com.sound.model.PaymentPassword;
import com.sound.model.PaymentsRecords;
import com.sound.model.UserModel;

import com.sound.model.UserTemporary;
import com.sound.service.CucrrencyRecordService;
import com.sound.service.DailyTaskService;
import com.sound.service.LabelService;
import com.sound.service.NickNameService;
import com.sound.service.PaymentPasswordService;
import com.sound.service.PaymentsRecordsService;
import com.sound.service.RedisService;
import com.sound.service.UserService;
import com.sound.service.UserTemporaryService;
import com.sound.utils.AJpushUtils;
import com.sound.utils.CalculatingForceLeve;
import com.sound.utils.CheckUtils;
import com.sound.utils.GsonUtil;
import com.sound.utils.Rank;
import com.sound.vo.SmallChainPurse;
import com.sound.vo.Test;

import ch.qos.logback.classic.gaffer.GafferUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@Api(description = "用户")
@RequestMapping(path = "/user")
public class UserController extends BaseController<UserModel> {

	@Autowired
	private static String MSG_CODE = "msgCode";

	@Autowired
	private UserService userSevice;

	@Autowired
	private PaymentPasswordService paymentPasswordService;
	@Autowired
	private GitHubLookupService git;
	@Autowired
	private PaymentsRecordsService paymentService;

	@Autowired
	public QiNiuConfig qiNiuConfig;

	@Autowired
	public DailyTaskService dailyTaskService;
	@Resource
	private RedisService redisService;

	@Autowired
	private WebChinese webChinese;

	@Autowired
	private DailyTaskService dailyService;
	@Autowired
	private NickNameService nickNameService;

	@Value("${qiniu.sound.download}")
	private String soundDownload;

	@Value("${qiniu.imgs.download}")
	private String imgsDsownload;
	@Value("${h5host}")
	private String h5host;
	@Value("${sweetshost}")
	private String sweetshost;
	@Value("${netty.server.url}")
	private String nettyServerURL;
	@Value("${img}")
	private String img;// 未绑定的图片
	@Value("${shop}")
	private String shop;
	@Value("${url}")
	private String url;
	@Value("${postUrl}")
	private String postUrl;
	@Value("${raiseMoneyUrl}")
	private String raiseMoneyPostUrl;
	@Value("${getUrl}")
	private String getUrl;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserTemporaryService userTemporaryService;

	@Autowired
	private CucrrencyRecordService cucrrencyRecordService;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 登陆接口
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "登陆注册接口")
	@GetMapping(path = "/login.do")
	public Response login(@RequestParam("token") String token, @RequestParam("userPhone") String userPhone,
			@RequestParam("msgCode") String msgCode, HttpServletRequest request, HttpServletResponse response) {
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		HttpSession session = request.getSession();
		DailyTask daily = new DailyTask();
		Response resp = new Response();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long count = userSevice.findUserCout();
		if (CheckUtils.checkString(token)) {// 传入的token不为空根据token判断用户是否登陆

			if (CheckUtils.checkCellphone(userPhone)) {
				String value = redisService.getCode(userPhone);

				if (!msgCode.equals(value)) {
					resp.setRespone(ParamCode.FAIL);
					resp.setMsg("请输入正确的验证码");
					return resp;
				}
				redisService.deleteCode(userPhone + redisService.CODE);
				try {
					UserModel user = userSevice.findByUserName(userPhone);
					String token_st = userPhone + (new Date()).getTime();
					String token1 = DigestUtils.md5DigestAsHex(token_st.getBytes("utf-8"));
					if (user == null) {

						user = new UserModel();
						user.setName(nickNameService.findById(this.getRandomId()).getName());
						user.setUserLevel(0);
						user.setSex("");
						user.setAdress("");
						user.setBirthday(sdf.parse("1990-01-01"));
						user.setBaiduAccount("");
						user.setNowDeviceId("");
						user.setPushSysMsg(true);
						user.setTotal(new BigDecimal(0));
						user.setYesterdayTotal(new BigDecimal(0));
						user.setPhoneNum(userPhone);
						user.setUserToken(token1);
						user.setCalculatingForceValue(new BigDecimal(0));
						user.setHeaderUrl("FkMbMiVFBkCiX6a2jBEDyVvw7BHp");
						user.setHeadFileName("头像.jpg");
						user.setTheFirstFew(Rank.rank(count));
						user.setBoundOrNot(false);
						UserModel u = userSevice.save(user);
						logger.info("注册成功" + u.toString());
						paramMap.add("mobileNumber", u.getPhoneNum());
						String pa = this.getCode() + "pw";
						paramMap.add("loginPword", pa);
                        paramMap.add("countryCode", "+86");
						PaymentsRecords p = new PaymentsRecords();
						p.setUserId(u.getId());
						p.setCurrencyName("LXB");
						p.setPayments(new BigDecimal(Double.toString(0)));
						paymentService.save(p);
						daily.setUserId(u.getId());
						dailyService.save(daily);
						u.setUserToken(token1);
						redisService.saveUser(token1, u);
						session.setMaxInactiveInterval(24 * 3600);
						Cookie cookie = new Cookie("ssoToken", token1);
						cookie.setPath("/");
						response.addCookie(cookie);
						response.addCookie(cookie);
						Map<String, Object> map = new HashMap<>();
						logger.info("异步请求");
						Future<String> page1 = git.findUser(postUrl, paramMap);
						/*
						 * String body = restTemplate.postForObject(postUrl, paramMap,String.class );
						 */
						//logger.info("线程休眠");
						//Thread.sleep(2000);
						//if(page1.isDone()) {
						String body = page1.get();
						JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
						logger.info("注册交易所返回结果" + jsonObject.toString());
						// String code=JSON.parseObject(body).getJSONObject("code").toString();
						String code = jsonObject.get("code").getAsString();
						if (!code.equals("0")) {
							UserTemporary us = new UserTemporary();
							us.setPhone(u.getPhoneNum());
							us.setPassword(pa);
							userTemporaryService.save(us);
						}
						if (code.equals("0")) {
							webChinese.sendMsg1(u.getPhoneNum(), pa);
						}
					//	}
						map.put("userInfo", u);
						map.put("img", img);
						map.put("url", url);
						// map.put("ssoToken",token1);
						resp.setData(map);
						resp.setRespone(ParamCode.SUCSESS);
						resp.setMsg("注册成功并且登录成功！");
						return resp;
					}

					userSevice.updateTokenByPhone(token1, userPhone);
					// System.out.println("00000000000000"+token1);
					redisService.removeUser(userPhone);
					JSONObject jo = new JSONObject();
		 			jo.put("report", "ok");
		 			jo.put("audioModel", "ok");
		 			AJpushUtils.pushIOSMsg("18929567567","data",jo.toJSONString());
					redisService.saveUser(token1, user);
					session.setMaxInactiveInterval(24 * 3600);
					Cookie cookie = new Cookie("ssoToken", user.getUserToken());
					cookie.setPath("/");
					response.addCookie(cookie);
					response.addCookie(cookie);
					Map<String, Object> map = new HashMap<>();
					map.put("userInfo", user);
					map.put("img", img);
					map.put("url", url);
					// map.put("ssoToken",token1);
					resp.setData(map);
					resp.setRespone(ParamCode.SUCSESS);
					resp.setMsg("登录成功！");
					return resp;
				} catch (Exception e) {
					logger.error("user login fail:", e);
				}
			}
			// System.out.println("自动登陆");
			UserModel user = userSevice.findByUserToken(token);
			if (null == user) {
				resp.setRespone(ParamCode.NOLOGIN);
				resp.setMsg("用户重新登录");
				return resp;
			}
			// System.out.println(user.toString());
			// System.out.println("111111111111"+token);
			// redisService.removeUser(userPhone);
			JSONObject jo = new JSONObject();
 			jo.put("report", "ok");
 			jo.put("audioModel", "ok");
 			AJpushUtils.pushIOSMsg("18929567567","data",jo.toJSONString());
			redisService.saveUser(token, user);
			session.setMaxInactiveInterval(24 * 3600);
			Cookie cookie = new Cookie("ssoToken", token);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie);
			resp.setRespone(ParamCode.SUCSESS);
			resp.setMsg("用户登录成功");
			Map<String, Object> map = new HashMap<>();
			map.put("userInfo", user);
			map.put("img", img);
			map.put("url", url);
			// map.put("ssoToken",token);
			resp.setData(map);
			return resp;
		}
		// 传入的token为空 手机快速注册登陆
		String value = redisService.getCode(userPhone);

		if (!msgCode.equals(value)) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("请输入正确的验证码");
			return resp;
		}
		redisService.deleteCode(userPhone + redisService.CODE);
		if (!CheckUtils.checkCellphone(userPhone)) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("用户手机格式不正确");
			return resp;
		}

		try {
			UserModel user = userSevice.findByUserName(userPhone);
			String token_st = userPhone + (new Date()).getTime();
			String token1 = DigestUtils.md5DigestAsHex(token_st.getBytes("utf-8"));
			if (user == null) {

				user = new UserModel();
				user.setName(nickNameService.findById(this.getRandomId()).getName());
				user.setPhoneNum(userPhone);
				user.setUserLevel(0);
				user.setSex("");
				user.setBirthday(sdf.parse("1990-01-01"));
				user.setAdress("");
				user.setBaiduAccount("");
				user.setNowDeviceId("");
				user.setPushSysMsg(true);
				user.setTotal(new BigDecimal(0));
				user.setYesterdayTotal(new BigDecimal(0));
				user.setUserToken(token1);
				user.setCalculatingForceValue(new BigDecimal(0));
				user.setTheFirstFew(Rank.rank(count));
				user.setBoundOrNot(false);
				user.setHeaderUrl("FkMbMiVFBkCiX6a2jBEDyVvw7BHp");
				user.setHeadFileName("头像.jpg");
				UserModel u = userSevice.save(user);
				logger.info("注册成功" + u.toString());
				paramMap.add("mobileNumber", u.getPhoneNum());
				String ps = this.getCode() + "pw";
				paramMap.add("loginPword", ps);
				 paramMap.add("countryCode", "+86");
				daily.setUserId(u.getId());
				dailyService.save(daily);
				PaymentsRecords p = new PaymentsRecords();
				p.setUserId(u.getId());
				p.setPayments(new BigDecimal(Double.toString(0)));
				p.setCurrencyName("LXB");
				paymentService.save(p);
				u.setUserToken(token1);
				redisService.saveUser(token1, u);
				session.setMaxInactiveInterval(24 * 3600);
				Cookie cookie = new Cookie("ssoToken", token1);
				cookie.setPath("/");
				response.addCookie(cookie);
				response.addCookie(cookie);
				Map<String, Object> map = new HashMap<>();
				Future<String> page1 = git.findUser(postUrl, paramMap);
				/*
				 * String body = restTemplate.postForObject(postUrl, paramMap,String.class );
				 */
				//logger.info("线程休眠");
				//Thread.sleep(2000);
				//异步防止交易所服务器挂掉
				//if(page1.isDone()) {
				String body = page1.get();
				JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
				logger.info("注册交易所返回结果" + jsonObject.toString());
				// String code=JSON.parseObject(body).getJSONObject("code").toString();
				String code = jsonObject.get("code").getAsString();
				if (!code.equals("0")) {
					UserTemporary us = new UserTemporary();
					us.setPhone(u.getPhoneNum());
					us.setPassword(ps);
					userTemporaryService.save(us);
				}
				if (code.equals("0")) {
					webChinese.sendMsg1(u.getPhoneNum(), ps);
				}
			//	}
				map.put("userInfo", u);
				map.put("img", img);
				map.put("url", url);
				// map.put("ssoToken",token1);
				resp.setData(map);
				resp.setRespone(ParamCode.SUCSESS);
				resp.setMsg("注册成功并且登录成功！");
				return resp;
			}
			// System.out.println("登录的token"+token1);
			userSevice.updateTokenByPhone(token1, userPhone);
			JSONObject jo = new JSONObject();
 			jo.put("report", "ok");
 			jo.put("audioModel", "ok");
 			AJpushUtils.pushIOSMsg("18929567567","data",jo.toJSONString());
			user.setUserToken(token1);
			redisService.removeUser(userPhone);
			redisService.saveUser(token1, user);
			session.setMaxInactiveInterval(24 * 3600);
			Cookie cookie = new Cookie("ssoToken", token1);
			cookie.setPath("/");
			response.addCookie(cookie);
			response.addCookie(cookie);
			Map<String, Object> map = new HashMap<>();
			map.put("userInfo", user);
			// map.put("ssoToken",token1);
			map.put("img", img);
			map.put("url", url);
			resp.setData(map);
		
			resp.setRespone(ParamCode.SUCSESS);
			resp.setMsg("登录成功！");
			return resp;

		} catch (Exception e) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("注册或者登录失败");
			logger.error("user login fail:", e);
		}
		
		return resp;

	}

	@ApiOperation(value = "个人中心首页接口")
	@PostMapping(path = "/personal.do")
	public Response personal(HttpServletRequest request) {
		Response res = new Response<>();
		UserModel userModel = redisService.getUserInfo(request);
		//System.out.println("_____________________________");
		//System.out.println(null==userModel);
		//System.out.println((null==userModel)?"meiyou":userModel.toString());
		// boolean b = this.isLogin(request);
		boolean isOnline;
		if (null == userModel) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户没有登陆，请登录");
			return res;
		}
		// System.out.println("======================="+userModel.toString());
		try {

			if (!userModel.getBoundOrNot()) {
				isOnline = false;
			} else {
				isOnline = restTemplate.postForObject(nettyServerURL + "/remote/deviceIsOnline",
						userModel.getNowDeviceId(), Boolean.class);
			}
			logger.info("用户设备是否在线" + isOnline);
			// List<Label> labels=labelService.findLabelsByUserId(userModel.getId());
			List<String> labels = new ArrayList<String>();
			labels.add(CalculatingForceLeve.lable(userModel.getCalculatingForceValue()));
			labels.add(CalculatingForceLeve.lableByCalculating(userModel.getCalculatingForceValue()));
			labels.add(CalculatingForceLeve.lableByBirthday(userModel.getBirthday()));
			logger.info("用户标签" + labels.toString());
			logger.info("个人首页中心的用户信息" + userModel.toString());
			Map<String, Object> map = new HashMap<>();
			map.put("isOnline", isOnline);
			/*
			 * if(null==labels||labels.size()==0) { map.put("labels", null); }else {
			 * 
			 * map.put("labels", labels); }
			 */
			map.put("labels", labels);
			map.put("user", userModel);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("个人信息查询成功");
			res.setData(map);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("个人首页中心的用户信息异常", e);
			res.setMsg("查询个人信息失败");
			res.setRespone(ParamCode.FAIL);
		}

		return res;
	}

	/**
	 * 头像上传
	 * 
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "头像上传接口")
	@PostMapping(path = "/uploadhead.do")
	public Response upLoadhead(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(request);
	
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		String token = userModel.getUserToken();
		if (file.isEmpty()) {
			logger.error("文件内容为空");
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("文件为空");
			return resp;
		}
		// 上传文件到七牛云
		File file1 = null;
		String key = null;
		logger.info(file.getName());
		try {
			file1 = File.createTempFile(file.getName(), ".tmp");
			file.transferTo(file1);
			key = qiNiuConfig.upLoadImg(file1);
		} catch (IOException e) {
			logger.error("头像上传失败" + e);
			e.printStackTrace();
		}

		if (StringUtils.isEmpty(key)) {
			logger.error("头像上传失败");
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("上传头像失败");
			return resp;
		}
		userModel.setHeaderUrl(key);
		userModel.setHeadFileName(file.getOriginalFilename());
		UserModel resultModel = userSevice.save(userModel);
		// System.out.println("touxiang " + resultModel.getHeaderUrl());
		redisService.saveUser(token, resultModel);
		logger.info("头像上传成功");
		resp.setRespone(ParamCode.SUCSESS);

		resp.setData(resultModel);
		return resp;
	}

	
	@ApiOperation(value = "文件上传接口")
	@PostMapping(path = "/upload.do")
	public Response upLoad(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Response resp = new Response();
	/*	UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		String token = userModel.getUserToken();*/
		if (file.isEmpty()) {
			logger.error("文件内容为空");
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("文件为空");
			return resp;
		}
		// 上传文件到七牛云
		File file1 = null;
		String key = null;
		logger.info(file.getName());
		try {
			file1 = File.createTempFile(file.getName(), ".tmp");
			file.transferTo(file1);
			key = qiNiuConfig.upLoadAudio(file1);
		} catch (IOException e) {
			logger.error("文件上传失败" + e);
			e.printStackTrace();
		}

		if (StringUtils.isEmpty(key)) {
			logger.error("文件上传失败");
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("上传文件失败");
			return resp;
		}
		logger.info("file path:"+file.getOriginalFilename());
		//userModel.setHeaderUrl(key);
		//userModel.setHeadFileName(file.getOriginalFilename());
		//UserModel resultModel = userSevice.save(userModel);
		// System.out.println("touxiang " + resultModel.getHeaderUrl());
		//redisService.saveUser(token, resultModel);
		logger.info("头像上传成功");
		resp.setRespone(ParamCode.SUCSESS);

		//resp.setData(resultModel);
		return resp;
	}
	/**
	 * 修改昵称
	 */
	@ApiOperation(value = "修改昵称接口")
	@PostMapping(path = "/changename.do")
	public Response upLoadhead(String name, HttpServletRequest request) {
		Response resp = new Response();

		UserModel userModel = redisService.getUserInfo(request);

		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		String token = userModel.getUserToken();
		UserModel user = userSevice.findById(userModel.getId());
		if (user == null || user.getId() <= 0) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("该用户不存在！");
			return resp;
		}

		if (!CheckUtils.checkName(name)) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("请输入2-10位英文或汉字");
			return resp;
		}
		user.setName(name);
		try {
			UserModel resultModel = userSevice.save(user);
			logger.info("用户头像上传完" + resultModel.toString());
			redisService.saveUser(token, resultModel);
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(resultModel);
		} catch (Exception e) {
			logger.error("modify user name fail:", e);
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("名称修改失败");
		}
		return resp;
	}

	/**
	 * 发送短信码
	 * 
	 * @param userId
	 * @return
	 */

	@ApiOperation(value = "发送短信接口")
	@GetMapping(path = "/sendMsg.do")
	public Response sendMsg(String userId, HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Response resp = new Response();
		String code = getCode();
		String codeMsg = redisService.getCode(userId);
		if (codeMsg != null) {
			webChinese.sendMsg(userId, codeMsg);
			resp.setRespone(ParamCode.SUCSESS);
			resp.setMsg("发送成功");
			return resp;
		}
		if (code != null) {

			if (webChinese.sendMsg(userId, code)) {

				redisService.saveCode(code, userId);
				logger.info("短信发送成功");
				resp.setRespone(ParamCode.SUCSESS);
				resp.setMsg("发送成功");
				return resp;
			}
		}
		logger.error("短信发送失败");
		resp.setRespone(ParamCode.FAIL);
		resp.setMsg("短信发送失败");
		return resp;
	}

	@ApiOperation(value = "签到")
	@GetMapping(path = "/signIn.do")
	public Response signIn(HttpServletRequest request) {
		Response res = new Response();
		try {
			UserModel userModel = redisService.getUserInfo(request);
			if (userModel == null || userModel.getId() <= 0) {
				res.setRespone(ParamCode.NOLOGIN);
				res.setMsg("用户请登录！");
				return res;
			}
			UserModel u = userSevice.findByUserName(userModel.getPhoneNum());
			if (u.isSignIn() == true) {
				res.setRespone(ParamCode.FAIL);
				res.setMsg("用户已经签到了！");
				return res;
			}
			String token = userModel.getUserToken();
			userModel.setSignIn(true);
			if (u.isYesterdayIsSignIn() == true) {
				userModel.setSignInTotal(userModel.getSignInTotal() + 1);
				if (userModel.getSignInTotal() >= 7) {
					userModel.setCalculatingForceValue(userModel.getCalculatingForceValue().add(new BigDecimal(7)));
				} else {
					userModel.setCalculatingForceValue(
							userModel.getCalculatingForceValue().add(new BigDecimal(userModel.getSignInTotal())));
				}
			} else {
				userModel.setYesterdayIsSignIn(true);
				userModel.setSignInTotal(1);
				userModel.setCalculatingForceValue(userModel.getCalculatingForceValue().add(new BigDecimal(1)));
			}
			userModel.setUserLevel(CalculatingForceLeve.changLevel(userModel.getCalculatingForceValue()));
			UserModel user = userSevice.save(userModel);
			logger.info("用户签到完" + user.toString());
			redisService.deleteCode(token);
			redisService.saveUser(token, user);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("签到成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户签到异常" , e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("签到失败");
		}

		return res;
	}

	@ApiOperation(value = "关注公众号")
	@GetMapping(path = "/follow.do")
	public Response follow(HttpServletRequest request) {
		Response res = new Response();
		try {
			UserModel userModel = redisService.getUserInfo(request);
			if (userModel == null || userModel.getId() <= 0) {
				res.setRespone(ParamCode.NOLOGIN);
				res.setMsg("用户请登录！");
				return res;
			}
			String token = userModel.getUserToken();
			userModel.setFollow(true);
			userModel.setCalculatingForceValue(userModel.getCalculatingForceValue().add(new BigDecimal(5)));
			userModel.setUserLevel(CalculatingForceLeve.changLevel(userModel.getCalculatingForceValue()));
			UserModel user = userSevice.save(userModel);
			logger.info("用户关注公众号完" + user.toString());
			redisService.deleteCode(token);
			redisService.saveUser(token, user);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("关注公众号成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户关注公众号异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("关注公众号失败");
		}

		return res;
	}

	@GetMapping(path = "/valitionMsg.do")
	public Response valitionMsg(String userId, String code) {
		String codeMsg = null;
		Response resp = new Response(true);
		try {
			codeMsg = redisService.getCode(userId);
			redisService.deleteCode(userId);
			if (code.equals(codeMsg)) {

				return resp;
			}
		} catch (Exception e) {
			logger.error("valitionMsg fail:", e);
		}
		resp.setRespone(ParamCode.FAIL);
		resp.setMsg("验证码有误");
		return resp;
	}

	/**
	 * 注册用户
	 * 
	 * @param userId
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "邀请好友注册")
	@GetMapping(path = "/register.do")
	public Response register(String phoneNum, String invitePhoneNum, String msgCode, HttpServletResponse response)
			throws UnsupportedEncodingException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Response res = new Response();
		DailyTask daily = new DailyTask();
		if (null == phoneNum || phoneNum.trim().length() == 0) {
			res.setRespone(ParamCode.FAIL);
			res.setMsg("请输入手机号");
			return res;
		}
		if (null == invitePhoneNum || invitePhoneNum.trim().length() == 0) {
			res.setRespone(ParamCode.FAIL);
			res.setMsg("推荐人的号码为空");
			return res;
		}
		if (phoneNum.trim().equals(invitePhoneNum.trim())) {

			res.setRespone(ParamCode.FAIL);
			res.setMsg("注册的号码与推荐人号码一致");
			return res;
		}
		if (!CheckUtils.checkCellphone(phoneNum.trim())) {

			res.setRespone(ParamCode.FAIL);
			res.setMsg("请输入正确的手机号");
			return res;
		}
		if (!CheckUtils.checkCellphone(invitePhoneNum.trim())) {

			res.setRespone(ParamCode.FAIL);
			res.setMsg("推荐人手机号不正确");
			return res;
		}
		String value = redisService.getCode(phoneNum);
		if (!msgCode.equals(value)) {
			res.setRespone(ParamCode.FAIL);
			res.setMsg("请输入正确的验证码");
			return res;
		}
		redisService.deleteCode(phoneNum + redisService.CODE);
		UserModel user1 = userSevice.findByUserName(phoneNum.trim());
		if (null != user1) {
			// System.out.println("号码已经注册");
			res.setRespone(ParamCode.FAIL);
			res.setMsg("改手机号已被注册");
			return res;
		}
		UserModel user2 = userSevice.findByUserName(invitePhoneNum.trim());
		if (null == user2) {
			// System.out.println("没有注册");

			res.setRespone(ParamCode.FAIL);
			res.setMsg("推荐人号码没有注册");
			return res;
		}
		try {
			String token_st = phoneNum + (new Date()).getTime();
			String token1 = DigestUtils.md5DigestAsHex(token_st.getBytes("utf-8"));

			UserModel user = new UserModel();

			user.setName(nickNameService.findById(this.getRandomId()).getName());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long count = userSevice.findUserCout();
			user.setTheFirstFew(Rank.rank(count));
			user.setSex("");
			user.setAdress("");
			user.setBirthday(sdf.parse("1990-01-01"));
			user.setBaiduAccount("");
			user.setNowDeviceId("");
			user.setPushSysMsg(true);
			user.setTotal(new BigDecimal(0));
			user.setYesterdayTotal(new BigDecimal(0));

			user.setPhoneNum(phoneNum);
			user.setUserLevel(0);
			user.setUserToken(token1);
			user.setInviteUserId(user2.getId());
			user.setCalculatingForceValue(new BigDecimal(0));
			// user.setTheFirstFew(Rank.rank(count));
			user.setBoundOrNot(false);
			user.setHeaderUrl("FvODcxS12ISiWmEvcKsCL4POFaEy");
			user.setHeadFileName("头像.jpg");
			UserModel u = userSevice.save(user);
			logger.info("被邀请的用户注册" + user.toString());
			daily.setUserId(u.getId());
			dailyService.save(daily);
			user2.setCalculatingForceValue(user2.getCalculatingForceValue().add(new BigDecimal(5)));
			user2.setUserLevel(CalculatingForceLeve.changLevel(user2.getCalculatingForceValue()));
			userSevice.save(user2);
			redisService.deleteCode(user2.getUserToken());
			redisService.saveUser(user2.getUserToken(), user2);
			
			logger.info("邀请别人注册的用户" + user2.toString());
			logger.info("异步请求");
			MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
			paramMap.add("mobileNumber", u.getPhoneNum());
			String ps = this.getCode() + "pw";
			paramMap.add("loginPword", ps);
			Future<String> page1 = git.findUser(postUrl, paramMap);
			/*
			 * String body = restTemplate.postForObject(postUrl, paramMap,String.class );
			 */
			//logger.info("线程休眠");
			//Thread.sleep(2000);
			//if(page1.isDone()) {
			String body = page1.get();
			JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
			logger.info("注册交易所返回结果" + jsonObject.toString());
			// String code=JSON.parseObject(body).getJSONObject("code").toString();
			String code = jsonObject.get("code").getAsString();
			if (!code.equals("0")) {
				UserTemporary us = new UserTemporary();
				us.setPhone(u.getPhoneNum());
				us.setPassword(ps);
				userTemporaryService.save(us);
			}
			if (code.equals("0")) {
				webChinese.sendMsg1(u.getPhoneNum(), ps);
			}
		//	}
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("注册成功！");

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("邀请用户注册异常" ,e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("注册失败！");
		}
		return res;
	}

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param password
	 * @return
	 */
	// @GetMapping(path = "/updatePwd.do")
	// public Response changePassword(@RequestParam("msgCode") String msgCode,
	// @RequestParam("userId") String userId,
	// @RequestParam("password") String password,
	// HttpServletRequest request, HttpServletResponse rep){
	//
	// Response resp =new Response();
	//// UserModel userModel = redisService.getUserInfo(request);
	////
	//// if(userModel==null || userModel.getId()<=0){
	//// resp.setRespone(ParamCode.FAIL);
	//// resp.setMsg("该用户不存在！");
	//// return resp;
	//// }
	// String msgcode = redisService.getCode(userId);
	// if(!msgCode.equals(msgcode)){
	// resp.setRespone(ParamCode.FAIL);
	// resp.setMsg("验证码不正确");
	// return resp;
	// }
	// if(!CheckUtils.checkPwd(password)){
	// resp.setRespone(ParamCode.FAIL);
	// resp.setMsg("密码格式不正确");
	// return resp;
	// }
	// UserModel mo =userSevice.findByUserName(userId);
	// if(mo==null) {
	// resp.setRespone(ParamCode.FAIL);
	// resp.setMsg("该用户不存在！");
	// return resp;
	// }
	// mo.setPassword(password);
	// userSevice.save(mo);
	// resp.setRespone(ParamCode.SUCSESS);
	// resp.setMsg("修改成功");
	// return resp;
	// }
	//
	//
	private static String getCode() {
		String res = "";
		Random r = new Random();
		for (int i = 0; i < 6; i++) {
			int m = 0 + r.nextInt(9 - 0);
			res = res + m;
		}
		logger.info("获取验证码" + res);
		return res;
	}

	private static Long getRandomId() {
		Random r = new Random();
		int i = r.nextInt(30 - 1);

		return Long.parseLong(String.valueOf(i));
	}

	@GetMapping(path = "/hostUrl.do")
	@ApiOperation(value = "获取域名地址")
	public Response getList(HttpServletRequest request) throws InterruptedException, ExecutionException {

		Response resp = new Response();
		/*UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}*/
		Map<String, String> map = new HashMap<>();
		logger.info("获取商铺的域名" + shop);
		logger.info("获取音频下载的域名" + soundDownload);
		logger.info("获取图片的域名" + imgsDsownload);
		logger.info("获取邀请好友注册的域名" + h5host);
		logger.info("获取活动详情的域名" + sweetshost);

		map.put("shop", shop);
		map.put("audiohost", soundDownload);
		map.put("imghost", imgsDsownload);
		map.put("h5host", h5host);
		map.put("sweetshost", sweetshost);
		resp.setData(map);
		resp.setRespone(ParamCode.SUCSESS);
		return resp;

	}

	@GetMapping(path = "/loginout.do")
	@ApiOperation(value = "退出登录", tags = "", response = Boolean.class)
	public Response loginOut(HttpServletRequest request) {
		Response resp = new Response(true);
		try {
			UserModel userModel = redisService.getUserInfo(request);
			if (null != userModel && userModel.getId() > 0) {
				redisService.loginout(userModel.getPhoneNum());
				String token_st = userModel.getPhoneNum() + (new Date()).getTime();
				String token1 = DigestUtils.md5DigestAsHex(token_st.getBytes("utf-8"));
				userModel.setUserToken(token1);
				userSevice.save(userModel);
				logger.info("用户退出登录" + userModel.toString());
			}
		} catch (Exception e) {
			logger.info("login out exception", e);
		}
		return resp;
	}

	@PostMapping(path = "/accountSettings.do")
	@ApiOperation(value = "账户设置")
	public Response accountSettings(HttpServletRequest request, String phoneNum, String birthday, String nickName,
			boolean pushSysMsg, String sex, String adress) throws ParseException {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		//
		// System.out.println("账户设置");
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		logger.info("用户信息" + userModel.toString());
		if (null == nickName || nickName.trim().length() <= 0) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("请输入用户名");
			return resp;
		}
		if (!CheckUtils.checkName(nickName)) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("请输入2-10位英文或汉字");
			return resp;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {

			String token = userModel.getUserToken();
			if (null == birthday || birthday.trim().equals("")) {
				resp.setMsg("生日不能为空");
				resp.setRespone(ParamCode.FAIL);
			}
			if (null == adress || adress.trim().equals("")) {
				resp.setMsg("地址不能为空");
				resp.setRespone(ParamCode.FAIL);
			}
		/*	if (userModel.isPerfectInformation() == false) {
				userModel.setCalculatingForceValue(userModel.getCalculatingForceValue().add(new BigDecimal(5)));
			}*/
			userModel.setPerfectInformation(true);
			userModel.setAdress(adress);
			// Calendar rightNow = Calendar.getInstance();
			// rightNow.set(Integer.parseInt(birthday.split("-")[0]),
			// Integer.parseInt(birthday.split("-")[1]),
			// Integer.parseInt(birthday.split("-")[2]));
			userModel.setBirthday(sdf.parse(birthday));
			// logger.info(rightNow.getTime().toString());
			// userModel.setBirthday(rightNow.getTime());
			userModel.setSex(sex);
			userModel.setPhoneNum(phoneNum);
			userModel.setName(nickName);
			userModel.setPushSysMsg(pushSysMsg);
			UserModel user = userSevice.save(userModel);
			logger.info("用户修改完资料" + user.toString());
			redisService.deleteCode(token);
			redisService.saveUser(token, userModel);
			resp.setData(user);
			resp.setMsg("完善修改资料成功");
			resp.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户更改资料异常" , e);
			resp.setMsg("完善修改资料失败");
			resp.setRespone(ParamCode.FAIL);
		}

		return resp;
	}

	@PostMapping(path = "/userCharts.do")
	@ApiOperation(value = "用户排名榜")
	public Response userCharts(HttpServletRequest request) {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		Sort sort = Sort.by(Sort.Direction.DESC, "calculatingForceValue");
		PageRequest pageable = PageRequest.of(0, 10, sort);
		try {
			Page<UserModel> pages = userSevice.findAll(pageable);
			logger.info("用户的排名榜" + pages.toString());
			resp.setData(pages);
			resp.setMsg("用户排行成功");
			resp.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			resp.setMsg("用户排行失败");
			resp.setRespone(ParamCode.FAIL);
			logger.error("用户的排名榜异常" , e);
		}

		return resp;
	}

	@PostMapping(path = "/userLevel.do")
	@ApiOperation(value = "用户信息等级")
	public Response userLevel(@RequestParam("token") String token) {
		Response resp = new Response();
		try {
			UserModel user = userSevice.findByUserToken(token);
			logger.info("用户的信息" + user.toString());
			resp.setData(user);
			resp.setMsg("用户等级成功");
			resp.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			resp.setMsg("用户等级失败");
			resp.setRespone(ParamCode.FAIL);
			logger.error("用户的信息异常" , e);
		}

		return resp;
	}

	@PostMapping(path = "/ranking.do")
	@ApiOperation(value = "用户排名打败人数")
	public Response ranking(HttpServletRequest request) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			DailyTask dailyTask = dailyTaskService.findDailyTaskByUserId(userModel.getId());
			long num = userSevice.findUserCount(userModel.getCalculatingForceValue().intValue());
			long count = userSevice.findUserCout();

			int singIn = 0;
			int persions = new BigDecimal(1)
					.subtract(new BigDecimal(num).divide(new BigDecimal(count), 2, RoundingMode.HALF_UP))
					.multiply(new BigDecimal(100)).intValue();
			if (userModel.isYesterdayIsSignIn() == true) {
				if (userModel.getSignInTotal() >= 7) {
					singIn = 7;
				} else {
					singIn = userModel.getSignInTotal() + 1;
				}
			} else {
				singIn = 1;
			}
			logger.info("用户是否分享" + dailyTask.isSharingAudio());
			logger.info("用户打败人数" + persions);
			logger.info("用户签到获取的算力" + singIn);
			map.put("share", dailyTask.isSharingAudio());
			map.put("persions", persions);
			map.put("singIn", singIn);
			res.setData(map);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("打败人数查询成功");

		} catch (Exception e) {
			// TODO: handle exception
			res.setMsg("打败人数查询失败");
			res.setRespone(ParamCode.FAIL);
			logger.error("用户打败人数异常" , e);
		}

		return res;

	}

	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "分享音频")
	@GetMapping(path = "/share.do")
	public Response share(HttpServletRequest request) {

		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {

			DailyTask dailyTask = dailyTaskService.findDailyTaskByUserId(userModel.getId());
			if (dailyTask.isSharingAudio() == true) {
				res.setRespone(ParamCode.FAIL);
				res.setMsg("用户已经分享");
				return res;
			}
			userModel.setCalculatingForceValue(userModel.getCalculatingForceValue().add(new BigDecimal(5)));
			dailyTask.setSharingAudio(true);
			userSevice.save(userModel);
			dailyTaskService.save(dailyTask);
			redisService.deleteCode(userModel.getUserToken());
			redisService.saveUser(userModel.getUserToken(), userModel);
			logger.info("用户分享完音频的信息" + userModel.toString());
			logger.info("用户分享完音频的每日任务信息" + dailyTask.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("用户分享成功");
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
			res.setMsg("用户分享失败");
			logger.error("用户分享音频失败" , e);
		}
		return res;

	}

	@ApiOperation(value = "用户邀请人数以及获得的算力")
	@GetMapping(path = "/inviteUser.do")
	public Response inviteUser(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {
			int count = userSevice.inviteUser(userModel.getId());
			int calculating = count * 5;
			map.put("count", count);
			map.put("calculating", calculating);
			logger.info("用户邀请人数" + count);
			logger.info("用户邀请获取的算力" + calculating);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("用户邀请人数以及获得的算力成功");
			res.setData(map);
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
			res.setMsg("用户邀请人数以及获得的算力失败");
			logger.error("用户邀请人数接口异常", e);
		}

		return res;
	}

	@ApiOperation(value = "获取用户算力")
	@GetMapping(path = "/findCalculating.do")
	public Response findCalculating(HttpServletRequest req) {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		logger.info("用户蒜力"+userModel.getCalculatingForceValue());
		resp.setRespone(ParamCode.SUCSESS);
		resp.setMsg("获取算力成功");
		resp.setData(userModel.getCalculatingForceValue());
		return resp;
	}

	@ApiOperation(value = "小链钱包")
	@GetMapping(path = "/smallChainPurse.do")
	public Response smallChainPurse(HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}

		String body = restTemplate.getForObject(getUrl + "?phone=" + userModel.getPhoneNum(), String.class);
		if (null != body && body.trim().length() > 0) {
			JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
			// String code=JSON.parseObject(body).getJSONObject("code").toString();

			String code = jsonObject.get("code").getAsString();
			if (code.equals("0")) {

				String data = jsonObject.get("data").toString();
				logger.info("钱包返回的json" + data);
				List<SmallChainPurse> smallChainPurses = new Gson().fromJson(data,
						new TypeToken<List<SmallChainPurse>>() {
						}.getType());

				String total = "0";
				for (int i = 0; i < smallChainPurses.size(); i++) {
					SmallChainPurse smallChainPurse = smallChainPurses.get(i);
					logger.info("钱包返回的数据" + smallChainPurse.getBalance());
					smallChainPurse.setTotal(new BigDecimal(smallChainPurse.getBalance())
							.multiply(new BigDecimal(smallChainPurse.getRate())).setScale(2, BigDecimal.ROUND_HALF_UP)
							.toString());
					total = new BigDecimal(total).add(new BigDecimal(smallChainPurse.getTotal()))
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
				}
				logger.info("钱包返回的总资产" + total);
				logger.info("钱包返回的资产详情" + smallChainPurses);
				map.put("total", total);
				map.put("smallChainPurses", smallChainPurses);
				// System.out.println(s.toString());
				resp.setRespone(ParamCode.SUCSESS);
				resp.setMsg("小链钱包金额查询成功");
				// System.out.println(jsonObject.get("data").getAsString());
				resp.setData(map);
			} else {
				resp.setRespone(ParamCode.FAIL);
				resp.setMsg("小链钱包金额查询失败");
				logger.error("获取钱包信息异常");
			}
		} else {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("小链钱包金额查询失败");
			logger.error("获取钱包信息失败");
		}

		return resp;
	}

	@ApiOperation(value = "收支记录")
	@GetMapping(path = "/record.do")
	public Response<Page<CucrrencyRecord>> record(HttpServletRequest req, String cucrrencyName, int page,
			@RequestParam(required = true, defaultValue = "10") int size) {
		Response<Page<CucrrencyRecord>> resp = new Response<Page<CucrrencyRecord>>(true);
		CucrrencyRecord curr = new CucrrencyRecord();

		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		curr.setUserId(userModel.getId());
		curr.setCucrrencyName(cucrrencyName);
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(page, size, sort);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("userId", ExampleMatcher.GenericPropertyMatchers.endsWith()).withIgnorePaths("audioId")
				.withIgnorePaths("id").withIgnorePaths("accees");
		Example<CucrrencyRecord> e = Example.of(curr, exampleMatcher);

		try {
			Page<CucrrencyRecord> pages = cucrrencyRecordService.findAll(e, pageable);
			logger.info("收支记录" + pages);
			resp.setRespone(ParamCode.SUCSESS);
			resp.setMsg("收支记录查询成功");
			resp.setData(pages);
		} catch (Exception e2) {
			// TODO: handle exception
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("收支记录查询失败");
			logger.info("收支记录异常" , e2);
		}

		return resp;
	}
	/*
	 * @ApiOperation(value = "签到更改")
	 * 
	 * @GetMapping(path = "/sigu.do") public Response sigu(HttpServletRequest req) {
	 * Response resp = new Response(); UserModel userModel =
	 * userSevice.findByUserName("18811432760");
	 * System.out.println(userModel.toString()); // 验证登陆态 if (userModel == null ||
	 * userModel.getId() <= 0) { resp.setRespone(ParamCode.NOLOGIN);
	 * resp.setMsg("用户请登录！"); return resp; } userModel.setSignIn(false);
	 * redisService.deleteCode(userModel.getUserToken());
	 * redisService.saveUser(userModel.getUserToken(), userModel);
	 * userSevice.save(userModel); resp.setRespone(ParamCode.SUCSESS); return resp;
	 * }
	 */
	@ApiOperation(value = "用户提现")
	@PostMapping(path = "/raiseMoney.do")
	public Response raiseMoney(HttpServletRequest req,String raiseMoneyUrl,String amountStr,String symbol,String password) {
		Response res = new Response();
		Map<String, Object> map = new HashMap<String, Object>();
		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		PaymentPassword p = paymentPasswordService.findPaymentPasswordByuserId(userModel.getId());
		if(!p.getPaymentPassword().equals(password.trim())) {
			res.setRespone(ParamCode.PASSWORDFAIL);
			res.setMsg("支付密码错误");
			return res;
		}
		if(new BigDecimal(amountStr).compareTo(new BigDecimal("0.01"))==-1) {
			res.setRespone(ParamCode.FAIL);
			res.setMsg("体现的金额小于手续费");
			return res;
			
		}

		paramMap.add("amountStr", amountStr);
		paramMap.add("symbol", symbol);
		paramMap.add("phone", userModel.getPhoneNum());
		paramMap.add("addressIdStr", raiseMoneyUrl);
		paramMap.add("feeStr", 0.01);
		try {
			 String body = restTemplate.postForObject(raiseMoneyPostUrl,
						paramMap,String.class );
 		 
			JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
			System.out.println(jsonObject.toString());
			String code = jsonObject.get("code").getAsString();
			String msg = jsonObject.get("msg").getAsString();
			if (!code.equals("0")) {
				res.setRespone(ParamCode.FAIL);
				res.setMsg(msg);
				
				return res;
			}
			if (code.equals("0")) {
				res.setRespone(ParamCode.SUCSESS);
				res.setMsg("体现成功");
				res.setData(jsonObject.get("data").toString());
			}
	} catch (Exception e) {
			// TODO Auto-generated catch block
			res.setRespone(ParamCode.FAIL);
			res.setMsg("体现失败");
			logger.error("体现失败",e);
		}
		
	   return res;
	}
}
