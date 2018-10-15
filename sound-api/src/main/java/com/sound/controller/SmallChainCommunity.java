package com.sound.controller;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.Album;
import com.sound.model.AudioModel;
import com.sound.model.Banner;
import com.sound.model.BigVAlbum;
import com.sound.model.CommunityBanner;
import com.sound.model.CucrrencyRecord;
import com.sound.model.Enroll;
import com.sound.model.Event;
import com.sound.model.UserModel;
import com.sound.service.AlbumService;
import com.sound.service.AudioService;
import com.sound.service.BigVAlbumService;
import com.sound.service.CommunityBannerService;
import com.sound.service.EnrollService;
import com.sound.service.EventService;
import com.sound.service.RedisService;
import com.sound.vo.EventVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@RestController
@Api(description= "小链社区")
@RequestMapping(path = "/SmallChainCommunity")
public class SmallChainCommunity extends BaseController<CommunityBanner> {
 	
	private static final Logger logger = LoggerFactory.getLogger(SmallChainCommunity.class);
	/*@Autowired
	private CommunityBannerService bannerService;*/
	@Autowired
	private AudioService bannerService;

	@Autowired
	private EventService eventService;

	@Autowired
	private AudioService audioService;

	@Autowired
	private AlbumService albumService;
	@Autowired
	private BigVAlbumService bigAlbumService;
 
	@Autowired
	private EnrollService enrollService;
	@Resource
	private RedisService redisService;
 
	@ApiOperation(value = "社区banner接口")
	@GetMapping(path = "findBanner.do")
	public Response findBanner(HttpServletRequest request) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		} 
		try {
		/*	Banner b = new Banner();
			b.setShowStatus(true);
			b.setShowArea(2);
			ExampleMatcher exampleMatcher = ExampleMatcher.matching()
					.withMatcher("showStatus", ExampleMatcher.GenericPropertyMatchers.endsWith()).withIgnorePaths("showArea")
					.withIgnorePaths("id");
			Example<Banner> e = Example.of(b, exampleMatcher);*/
			List<Banner> banners = bannerService.findBanner1();
			logger.info("社区banner接口"+banners.toString());
			res.setData(banners);
			res.setMsg("小链社区的banner成功");
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("社区banner接口异常", e);
			res.setMsg("小链社区的banner失败");
			res.setRespone(ParamCode.FAIL);
		}
  
		return res;
	}
 
	@ApiOperation(value = "社区推荐活动接口")
	@GetMapping(path = "findEvent.do")
	public Response findEvent(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {

			List<Event> events = eventService.findEvents(true);
			logger.info("社区推荐活动接口"+events.toString());
			res.setData(events);
			res.setMsg("小链社区的推荐活动成功");
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("社区推荐活动接口异常", e);
			res.setMsg("小链社区的推荐活动r失败");
			res.setRespone(ParamCode.FAIL);
		}

		return res;
	}

	@ApiOperation(value = "社区推荐大V专辑接口")
	@GetMapping(path = "findBigVAlbum.do")
	public Response findBigVAlbum(HttpServletRequest request) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {

			//Iterable<BigVAlbum> events=bigAlbumService.findEvents(true);
			List<Album> albums = albumService.findBigAlbum(true);
			logger.info("社区推荐大V专辑接口"+albums.toString());
			res.setData(albums);
			res.setMsg("小链社区的推荐大V专辑成功");
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("社区推荐大V专辑接口异常", e);
			res.setMsg("小链社区的推荐大V专辑失败");
			res.setRespone(ParamCode.FAIL);
		}

		return res;
	}

	@ApiOperation(value = "社区活动详情接口")
	@GetMapping(path = "findEventById.do")
	public Response findEventById(HttpServletRequest request, HttpServletResponse response, long id, String token)
			throws ParseException {
		response.setHeader("Access-Control-Allow-Origin", "*");

	
		Response res = new Response();

		Map<String, Object> map = new HashMap<>();
		UserModel userModel = redisService.getUser(token);
		if (null == userModel) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户没有登陆，请登录");
			return res;
		}
		try {
			Event ev = eventService.findById(id);
			EventVo event = eventService.findEventById(id);
            int count=eventService.findCount(id);
			boolean isSignUp;
		

			Enroll enrolls = enrollService.findEnroll(userModel.getId(), id);

			if (null == enrolls) {
				isSignUp = false;
			} else {
				isSignUp = true;
			}

			logger.info("用户是否报名"+isSignUp);
			map.put("isSignUp", isSignUp);
			map.put("event", ev);
			map.put("img", event);
			map.put("count", count);
			res.setData(map);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("小链社区活动详情成功");

		} catch (Exception e) {
			logger.error("社区活动详情接口异常", e);
			// TODO: handle exception
			res.setRespone(ParamCode.FAIL);
			res.setMsg("小链社区活动详情失败");

		}

		return res;
	}

	@ApiOperation(value = "社区活动报名接口")
	@GetMapping(path = "signUp.do")
	public Response signUp(HttpServletRequest request, HttpServletResponse response, long id, String token) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		Response res = new Response();
		UserModel userModel = redisService.getUser(token);
		if (null == userModel) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户没有登陆，请登录");
	 		return res;
		}
		try {

			Enroll e = new Enroll();
			e.setEventId(id);
			e.setUserId(userModel.getId());
			Enroll enroll = enrollService.findEnroll(userModel.getId(), id);
			if (null != enroll && enroll.getId() > 0) {
				logger.error("你已经报名");
				res.setRespone(ParamCode.FAIL);
				res.setMsg("你已经报名");
				return res;
			}
			enrollService.save(e);
			logger.info("社区活动报名接口"+e.toString());
			res.setData(userModel.getHeaderUrl());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("小链社区活动报名成功");

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("社区活动报名接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("小链社区活动报名失败");

	} 
		return res;
	}

	@ApiOperation(value = "社区大V专辑详情接口")
	@GetMapping(path = "findBigVAlbumById.do")
	public Response findBigVAlbumById(HttpServletRequest request, long id) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		Map<String, Object> map = new HashMap<>();
		try {
			Album album = albumService.findById(id);
			List<AudioModel> audios = audioService.findAudioByBigAlbumId(id);
			//Collections.sort(audios, Comparator.comparing(AudioModel::getId));
			map.put("album", album);
			map.put("audio", audios);
			logger.info("社区大V专辑详情接口频道"+album.toString());
			logger.info("社区大V专辑详情接口节目"+audios.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("大v专辑查询成功");
			res.setData(map);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("社区大V专辑详情接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("大v专辑查询失败");
		}
		return res;
	}
}
