package com.sound.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.ProgramReviews;
import com.sound.model.UserModel;
import com.sound.service.ProgramReviewsService;
import com.sound.service.RedisDiscussService;
import com.sound.service.RedisService;
import com.sound.vo.Discuss;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
   
@RestController
@Api(description= "节目评论")
@RequestMapping(path = "/programReviews")
public class ProgramReviewsController extends BaseController<ProgramReviews> {
  
	private static final Logger logger = LoggerFactory.getLogger(ProgramReviewsController.class);
	@Resource
	private RedisService redisService;

	@Resource
	private RedisDiscussService redisDiscussService;

	@Autowired
	private ProgramReviewsService programService;

	@ApiOperation(value = "发表评论")
	@GetMapping(path = "/save.do")
	public Response save(HttpServletRequest request, String context, String audioId) {
		Response res = new Response();
		try {
			UserModel userModel = redisService.getUserInfo(request);
			if (userModel == null || userModel.getId() <= 0) {
				res.setRespone(ParamCode.NOLOGIN);
				res.setMsg("用户请登录！");
				return res;
			}
			ProgramReviews program = new ProgramReviews();
			program.setContext(context);
			program.setAudioId(Long.parseLong(audioId));
			program.setUserId(userModel.getId());
			redisDiscussService.deleteDiscuss("discuss" + audioId);
			ProgramReviews p = programService.save(program);
			logger.info(userModel.getId()+"id的用户发表了评论"+p.toString());
			Map<String, Object> map = new HashMap<>();
			map.put("program", p);
			res.setRespone(ParamCode.SUCSESS);
			res.setData(map);
			res.setMsg("评论成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("用户发表了评论异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("评论失败");
		}

		return res;
	}

	@ApiOperation(value = "查找评论")
	@GetMapping(path = "/find.do")
	public Response find(String audioId) {
		Response res = new Response();
		Map<String, Object> map = new HashMap<>();
		try {
			List<Discuss> dis = redisDiscussService.getDiscuss("discuss" + audioId);

			if (null == dis || dis.size() == 0) {
				List<Discuss> diss = programService.findByaudioId(audioId);
				
				redisDiscussService.saveDiscuss("discuss" + audioId, diss);
				logger.info("查询节目评论成功"+diss);
				res.setRespone(ParamCode.SUCSESS);
				map.put("discuss", diss);
				res.setData(map);
				res.setMsg("查询节目评论成功");
				return res;
			}
			logger.info("查询节目评论成功"+dis);
			map.put("discuss", dis);
			res.setRespone(ParamCode.SUCSESS);
			res.setData(map);
			res.setMsg("查询节目评论成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询节目评论异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("查询节目评论失败");
		}

		return res;
	}

}
