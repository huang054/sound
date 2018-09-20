package com.sound.controller;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.AudioModel;
import com.sound.model.RecentAudioModel;
import com.sound.model.UserModel;
import com.sound.service.AudioService;
import com.sound.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
  
/**
 * Created by Administrator on 2018/5/11.
 */
@RestController
@RequestMapping(path = "/recentAudio")
@Api(description= "最近播放音频")
public class RecentAudioController extends BaseController<RecentAudioModel> {

	private static final Logger logger = LoggerFactory.getLogger(RecentAudioController.class);

	@Autowired
	private AudioService audioService;

	@Autowired
	private RedisService redisService;

	@GetMapping(path = "save")
	@Override
	public Response<RecentAudioModel> save(RecentAudioModel recentAudioModel, BindingResult result) {
		return super.save(recentAudioModel, result);
	}

	@GetMapping(path = "/deleteAllById")
	public Response<Void> deleteAllById(String albumIds, HttpServletRequest request) {
		 Response<Void>  resp=new Response<Void>(true);
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户没有登陆，请登录");
			return resp;
		}
		String[] sts = albumIds.split(",");
		String userId = redisService.getUserInfo(request).getId().toString();
		List<RecentAudioModel> modelList = new ArrayList<>();
		RecentAudioModel rec = new RecentAudioModel();
		rec.setUserId(userId);
		try {
			Iterator<RecentAudioModel> ites = super.service.findAll(Example.of(rec)).iterator();
			while (ites.hasNext()) {
				RecentAudioModel mo = ites.next();
				for (String id : sts) {
					if (Long.valueOf(id).equals(mo.getAudioId())) {
						modelList.add(mo);
					}
				}
			}
			super.service.deleteInBatch(modelList);
			return new Response<Void>(true);
		} catch (Exception e) {
			logger.error("deleteAllById fail:", e);
		}
		return new Response<Void>(false);
	}

	@GetMapping(path = "/deleteById")
	@Override
	public Response<Void> deleteById(Long id) {
		return super.deleteById(id);
	}

	@GetMapping(path = "/findByPage")
	@ApiOperation(value = "最近播放音频列表", tags = "")
	public Response<Iterable<AudioModel>> findByPage(HttpServletRequest request, int page, int size) {
		Response<Iterable<AudioModel>> resp = new Response<>();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户没有登陆，请登录");
			return resp;
		}
		UserModel model = redisService.getUserInfo(request);
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(page, size, sort);

		RecentAudioModel model1 = new RecentAudioModel();
		model1.setUserId(model.getId().toString());
		Example<RecentAudioModel> ex = Example.of(model1);
		try {
			Page<RecentAudioModel> page1 = super.service.findAll(ex, pageable);
			if (page1.getContent() == null) {
				resp.setRespone(ParamCode.SUCSESS);
				return resp;
			} else {
				List<RecentAudioModel> list = page1.getContent();
				List<Long> ids = new ArrayList<>();
				for (RecentAudioModel recent : list) {
					ids.add(recent.getAudioId());
				}
				Iterable<AudioModel> ite = audioService.findAllById(ids);
				resp.setData(ite);
			}
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		} catch (Exception e) {
			logger.error("findByPage fail:", e);
		}
		resp.setRespone(ParamCode.FAIL);
		return resp;
	}

}
