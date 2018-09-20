package com.sound.controller;

import ch.qos.logback.core.util.SystemInfo;
import com.alibaba.fastjson.JSON;
import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.SystemMsgModel;
import com.sound.model.UserModel;
import com.sound.model.UserMsgModel;
import com.sound.service.RedisService;
import com.sound.service.SysMsgService;
import com.sound.service.UserMsgService;
import com.sound.vo.SystemMsgVo;
import com.sound.vo.UserSysMsgVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2018/4/29.
 */
 
/** 
 * 系统消息
 */
@RestController
@RequestMapping(path = "/sysInfo")
@Api(description = "系统消息")
public class SysInfoController extends BaseController<SystemMsgModel> {

	private static final Logger logger = LoggerFactory.getLogger(SysInfoController.class);

	@Resource
	private RedisService redisService;

	@Autowired
	private SysMsgService sysMsgService;

	@Autowired
	private UserMsgService userMsgService;

	/**
	 * 用户系统消息列表
	 * 
	 * @return
	 */
	@GetMapping(path = "/syslist.do")
	@ApiOperation(value = "用户系统消息列表", tags = "")
	public Response<Map<String, Object>> getList(HttpServletRequest request) {

		UserModel userModel = redisService.getUserInfo(request);
		Response<Map<String, Object>> resp = new Response<>(true);
		Map<String, Object> map = new HashMap<>();
		if (userModel == null) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setData(map);
			return resp;
		}
		logger.info("用户"+userModel.toString());
		try {
			Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
			PageRequest pageable = PageRequest.of(0, 50, sort);
			SystemMsgModel s =new SystemMsgModel();
			s.setShowStatus(true);
			Example<SystemMsgModel> ex = Example.of(s);
			
			Iterable<SystemMsgModel> list = sysMsgService.findAll(ex,pageable);// 系统消息

			List<UserMsgModel> userList = userMsgService.findAllByTime(userModel.getId().toString());// 用户读取消息数

			Map<Long, String> sysMap = new HashMap<>();
			for (UserMsgModel model : userList) {
				sysMap.put(model.getSysmsgId(), "");
			}
			List<SystemMsgVo> msgList = new ArrayList<>();
			Iterator<SystemMsgModel> ites = list.iterator();
			int hasRead = 0;
			while (ites.hasNext()) {
				SystemMsgModel smsg = ites.next();
				SystemMsgVo vo = new SystemMsgVo();
				vo.setImgName(smsg.getImgName());
				vo.setContent(smsg.getContent());
				vo.setCreateTime(smsg.getCreateTime());
				vo.setId(smsg.getId());
				vo.setImgurl(smsg.getImgurl());
				vo.setTitle(smsg.getTitle());
				if (!sysMap.containsKey(smsg.getId())) {
					vo.setIsread(false);
				} else {
					vo.setIsread(true);
					hasRead++;
				}
				msgList.add(vo);
			}
			UserSysMsgVo result = new UserSysMsgVo();
			result.setHasReadNum(hasRead);
			result.setMsgList(msgList);
			result.setNoReadNum(msgList.size() - hasRead);
			logger.info("用户系统消息"+result.toString());
			map.put("result", result);
			map.put("imghost", imgsDsownload);
			map.put("audiohost", soundDownload);
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(map);
			return resp;
		} catch (Exception e) {
			logger.error("get sysInfo fail:", e);
		}
		resp.setRespone(ParamCode.FAIL);
		return resp;

	}

	/**
	 * 修改消息状态
	 * 
	 * @return
	 */
	@GetMapping(path = "/setStatus")
	@ApiOperation(value = "用户阅读系统消息后更改消息状态", tags = "")
	public Response setStatus(HttpServletRequest request, Long sysMsgId) {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (userModel == null) {
			return new Response(true);
		}
		UserMsgModel msg1=userMsgService.findByUserIdSysId(userModel.getId().toString(), sysMsgId);
		if(null!=msg1&&msg1.getId()>=1) {
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(true);
			return resp;
		}
		UserMsgModel msg = new UserMsgModel();
		msg.setStatus(1);
		msg.setUserId(userModel.getId().toString());
		msg.setSysmsgId(sysMsgId);
		UserMsgModel msgmodel = userMsgService.save(msg);
		logger.info("用户阅读系统消息后更改消息状态"+msgmodel.toString());
		if (msgmodel == null || msgmodel.getStatus() == 1) {
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(true);

		} else {
			resp.setRespone(ParamCode.FAIL);
			logger.error("用户阅读系统消息后更改消息状态异常");

		}
		return resp;
	}

	@GetMapping(path = "/findSysMsg")
	@ApiOperation(value = "用户阅读系统消息详情", tags = "")
	public Response findSysMsg(HttpServletRequest request, Long sysMsgId) {
		Response resp = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		/*
		 * UserModel userModel = redisService.getUserInfo(request); if(userModel==null){
		 * return new Response(true); }
		 */
		/*
		 * UserMsgModel msg =new UserMsgModel(); msg.setStatus(1);
		 * msg.setUserId(userModel.getId().toString()); msg.setSysmsgId(sysMsgId);
		 * UserMsgModel msgmodel = userMsgService.save(msg);
		 * if(msgmodel==null||msgmodel.getStatus()==1){
		 * resp.setRespone(ParamCode.SUCSESS); resp.setData(true);
		 * 
		 * }else{ resp.setRespone(ParamCode.FAIL);
		 * 
		 * }
		 */
		try {
			SystemMsgModel sysMsg = sysMsgService.findById(sysMsgId);
			logger.info("用户阅读系统消息详情"+sysMsg.toString());
			resp.setData(sysMsg);
			resp.setMsg("系统消息查询成功");
			resp.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户阅读系统消息详情异常", e);
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("系统消息查询失败");
		}

		return resp;
	}
}
