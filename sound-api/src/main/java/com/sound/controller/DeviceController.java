package com.sound.controller;

import com.alibaba.fastjson.JSON;
import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.Device;
import com.sound.model.UserModel;
import com.sound.service.DeviceService;
import com.sound.service.DeviceSettingService;
import com.sound.service.RedisService;
import com.sound.service.UserService;
import com.sound.utils.CalculatingForceLeve;
import com.sound.vo.DeviceVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
 
/** 
 * Created by Administrator on 2018/5/4.
 */
@RestController
@Api(description = "系统设备")
@RequestMapping(path = "/device")
public class DeviceController extends BaseController<Device> {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private DeviceSettingService deviceSettingService;

	private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

	@Value("${netty.server.url}")
	private String nettyServerURL;

	@GetMapping(path = "findById.do")
	@Override
	public Response<Device> findById(Long id) {
		return super.findById(id);
	}

	@GetMapping(path = "findAll.do")
	public Response<Iterable<Device>> findAll(@RequestParam(value = "deviceIds[]") String[] deviceIds) {
		return super.findAll();
	}

	/**
	 * 判断用户绑定得设备是否在线
	 * 
	 * @param deviceId
	 * @param request
	 * @return
	 */
	public Response deviceIsOnline(HttpServletRequest request) {
		// UserModel userModel = redisService.getUserInfo(request);
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		Map<String, Object> map = new HashMap<>();
		boolean isOnline = restTemplate.postForObject(nettyServerURL + "/remote/deviceIsOnline",
				userModel.getNowDeviceId(), Boolean.class);
		// logger.info("result map:" + JSON.toJSONString(map));
		if (!userModel.getBoundOrNot()) {
			res.setMsg("用户没有绑定");
			res.setRespone(ParamCode.FAIL);
			return res;
		}
		/**
		 * Device device = new Device(); String devi = userModel.getNowDeviceId(); if
		 * (map.containsKey(devi)) { LinkedHashMap result1 = map.get(devi);
		 * 
		 * device.setStatus((Boolean) result1.get("online")); }
		 */
		map.put("isOnline", isOnline);
		res.setData(map);
		res.setRespone(ParamCode.SUCSESS);
		return res;
	}

	@GetMapping(path = "findByPage.do")
	public Response<Page<Device>> findByPage(int page, int size) {

		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		PageRequest pageable = PageRequest.of(page, size, sort);
		Response<Page<Device>> result = new Response<>();
		try {
			Page<Device> pages = super.service.findAll(pageable);
			List<Device> list = pages.getContent();
			if (list == null)
				return new Response<Page<Device>>();
			String[] deviceIds = new String[list.size()];

			for (int i = 0; i < list.size(); i++) {
				deviceIds[i] = list.get(i).getDeviceId();
			}
			logger.info("restTemplate url: " + nettyServerURL + "/remote/isOnline");

			Map<String, String[]> paramMap = new HashMap<>();
			paramMap.put("deviceIds[]", deviceIds);
			logger.info("restTemplate params: " + JSON.toJSONString(paramMap));

			Map<String, LinkedHashMap> map = restTemplate.postForObject(nettyServerURL + "/remote/isOnline", deviceIds,
					Map.class);
			logger.info("result map:" + JSON.toJSONString(map));
			for (int i = 0; i < list.size(); i++) {
				Device devi = list.get(i);
				if (map.containsKey(devi.getDeviceId())) {
					LinkedHashMap result1 = map.get(devi.getDeviceId());
					devi.setIp((String) result1.get("ip"));
					devi.setPort((String) result1.get("port"));
					devi.setStatus((Boolean) result1.get("online"));
				}
			}
			result.setData(pages);
			return result;
		} catch (Exception e) {
			logger.error("device findByPage fail:", e);
		}
		result.setRespone(ParamCode.FAIL);
		return result;

	}

	/**
	 * 通过获取所有设备
	 */
	@GetMapping(path = "findByUserId.do")
	@ApiOperation(value = "通过用户id获取所有设备", tags = "")
	public Response<Iterable<Device>> findByUserId(HttpServletRequest req) {
		Response<Iterable<Device>> resp = new Response<Iterable<Device>>(true);

		try {
			UserModel userModel = redisService.getUserInfo(req);
			if (userModel == null || userModel.getId() <= 0) {
				resp = new Response<Iterable<Device>>(false);
				resp.setMsg("用户未登陆");
				resp.setRespone(ParamCode.NOLOGIN);
				return resp;
			}
			// System.out.println(userModel.getId());
			Iterable<Device> iterable = deviceService.findByUserId(userModel.getId().toString());
			resp.setData(iterable);
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		} catch (Exception e) {
			logger.error("device findByUserId fail:", e);
		}
		resp.setRespone(ParamCode.FAIL);
		return resp;
	}

	/**
	 * 根据设备id更改设备名称
	 */
	@GetMapping(path = "changeName.do")
	public Response<Device> changeName(@RequestParam("id") Long id, @RequestParam("deviceName") String deviceName) {
		Response<Device> resp = new Response<>();
		Device dev = deviceService.findById(id);

		if (dev == null) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("该设备不存在！");
			return resp;
		}
		dev.setDeviceName(deviceName);
		Device result = deviceService.save(dev);
		resp.setRespone(ParamCode.SUCSESS);
		resp.setData(result);
		return resp;
	}

	/**
	 * 用户绑定设备
	 * 
	 * @param userId
	 * @param deviceid
	 * @param createdate
	 * @param opCode
	 * @param opConn
	 * @return
	 */
	@GetMapping(path = "deviceBindById.do")
	public Response deviceBindById(@RequestParam("phoneNum") String phoneNum,
			@RequestParam("deviceId") String deviceId, @RequestParam("opCode") String opCode,
			@RequestParam("opConn") String opConn) {
		
		Response resp = new Response();
		Device dev = deviceService.findDeviceById(deviceId);
		UserModel user = userService.findByUserName(phoneNum);
		if (user == null) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("用户未登陆");
			return resp;
		} else {
			Iterable<UserModel> user1 = userService.findByDeviceId(deviceId);
			if (user1 != null) {
				for (UserModel item : user1) {
					item.setNowDeviceId("");
					userService.save(item);
				}
			}
			user.setNowDeviceId(deviceId);
			if (user.getInviteUserId() > 0) {
				UserModel u = userService.findById(user.getInviteUserId());
				u.setCalculatingForceValue(u.getCalculatingForceValue().add(new BigDecimal(100)));
				u.setUserLevel(CalculatingForceLeve.changLevel(u.getCalculatingForceValue()));
				userService.save(u);
				user.setInviteUserId(0);
			}
			userService.save(user);

		}
		if (dev == null) {
			Device device = new Device();
			device.setDeviceId(deviceId);
			device.setPhoneNum(phoneNum);
			device.setUserId(user.getId().toString());
			device.setDeviceName("小链音箱");
			device.setStatus(true);
			device.setVersion("v1.0");
			device.setCreateTime(new Date());
			Device de=deviceService.save(device);
			resp.setData(de.getId());
			resp.setMsg("添加成功");
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		} else {
			dev.setUserId(user.getId().toString());
			dev.setCreateTime(new Date());
			Device de=deviceService.save(dev);
			
			resp.setData(de.getId());
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		}
	}

	/**
	 * 通过设备id获取用户id和服务器信息
	 * 
	 * @param deviceid
	 * @param opCode
	 * @param opConn
	 * @return用户id,通信服务器信息
	 */
	@GetMapping(path = "findUserIdAndIP.do")
	@ResponseBody
	public Response<Map<String, String>> findUserIdAndIP(@RequestParam("deviceId") String deviceid,
			@RequestParam("opCode") String opCode, @RequestParam("opConn") String opConn) {
		Response<Map<String, String>> resp = new Response<Map<String, String>>();
		Device dev = deviceService.findDeviceById(deviceid);

		if (dev == null) {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("该设备不存在！");
			return resp;
		}
		Map<String, String> map = new HashMap<String, String>();
		Long date = new Date().getTime();
		String date1 = date.toString();
		map.put("deviceId", deviceid);
		map.put("userId", dev.getUserId());
		map.put("time", date1);
		map.put("tcpvrIP", deviceSettingService.findAll().iterator().next().getIp());
		resp.setData(map);
		resp.setRespone(ParamCode.SUCSESS);
		return resp;
	}

	@Transactional(rollbackFor = Exception.class)
	@PostMapping(path = "/unbind.do")
	@ApiOperation(value = "用户解绑设备-", tags = "")
	public Response unbind(HttpServletRequest req, String deviceId) {
		Response res = new Response();
		UserModel user = redisService.getUserInfo(req);
		// 验证登陆态
		if (user == null || user.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {
			if (deviceId.equals(user.getNowDeviceId())) {
				user.setBoundOrNot(false);
				user.setNowDeviceId("");
				userService.save(user);
				deviceService.unbind(deviceId);
				res.setRespone(ParamCode.SUCSESS);
				res.setMsg("用户解绑成功");
			} else {
				deviceService.unbind(deviceId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
			res.setMsg("用户解绑失败");
		}
		return res;
	}
}
