package com.sound.controller;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.Album;
import com.sound.model.RecentAlbumModel;
import com.sound.model.UserModel;
import com.sound.service.AlbumService;
import com.sound.service.RecentAlbumService;
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
import java.util.*;
  
/**
 * Created by Administrator on 2018/5/11.
 */
@RestController
@RequestMapping(path = "/recentAlbum")
@Api(description = "最近播放专辑")
public class RecentAlbumController extends BaseController<RecentAlbumModel> {

	private static final Logger logger = LoggerFactory.getLogger(RecentAlbumController.class);

	@Autowired
	private AlbumService albumService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private RecentAlbumService recentAlbumService;

	@GetMapping(path = "/save")
	public Response<RecentAlbumModel> save(HttpServletRequest request, Long AlbumId) {
		Response<RecentAlbumModel> resp =new Response<RecentAlbumModel>(true);
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户没有登陆，请登录");
			return resp;
		}
		String userId = redisService.getUserInfo(request).getId().toString();
		RecentAlbumModel recentAlbumModel = new RecentAlbumModel();
		recentAlbumModel.setAlbumId(AlbumId);
		recentAlbumModel.setUserId(userId);
		try {
			recentAlbumService.save(recentAlbumModel);
			return new Response<RecentAlbumModel>(true);
		} catch (Exception e) {
			logger.error("save fail:", e);
			return new Response<RecentAlbumModel>(false);
		}
	}

	@GetMapping(path = "/deleteAllById")
	public Response<Void> deleteAllById(String albumIds, HttpServletRequest request) {
		 Response<Void> resp=new Response<Void>(true);
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户没有登陆，请登录");
			return resp;
		}
		String[] sts = albumIds.split(",");
		List<RecentAlbumModel> modelList = new ArrayList<>();
		String userId = redisService.getUserInfo(request).getId().toString();
		RecentAlbumModel rec = new RecentAlbumModel();
		rec.setUserId(userId);
		try {
			Iterator<RecentAlbumModel> ites = recentAlbumService.findAll(Example.of(rec)).iterator();

			while (ites.hasNext()) {
				RecentAlbumModel mo = ites.next();
				for (String id : sts) {
					if (Long.valueOf(id).equals(mo.getAlbumId())) {
						modelList.add(mo);
					}
				}
			}
			recentAlbumService.deleteInBatch(modelList);
		} catch (Exception e) {
			logger.error("deleteAllById fail:", e);
		}
		return new Response<Void>(true);
	}

	@GetMapping(path = "/deleteById")
	@Override
	public Response<Void> deleteById(Long id) {
		return super.deleteById(id);
	}

	@GetMapping(path = "/findByPage")
	@ApiOperation(value = "最近播放专辑列表", tags = "")
	public Response<Iterable<Album>> findByPage(HttpServletRequest request, int page, int size) {
		Response<Iterable<Album>> resp = new Response<>();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户没有登陆，请登录");
			return resp;
		}
		RecentAlbumModel recentAlbumModel = new RecentAlbumModel();
		recentAlbumModel.setUserId(userModel.getId().toString());
		Example<RecentAlbumModel> example = Example.of(recentAlbumModel);
		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(page, size, sort);
		try {
			Page<RecentAlbumModel> page1 = recentAlbumService.findAll(example, pageable);

			List<RecentAlbumModel> lis = page1.getContent();
			Map<Long, Long> ids = new HashMap<>();
			for (RecentAlbumModel model : lis) {
				ids.put(model.getAlbumId(), model.getAlbumId());
			}
			List<Long> idList = new ArrayList<>(ids.values());

			resp.setData(albumService.findAllById(idList));
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		} catch (Exception e) {
			logger.error("findByPage fail:", e);
		}
		resp.setRespone(ParamCode.FAIL);
		return resp;

	}

}
