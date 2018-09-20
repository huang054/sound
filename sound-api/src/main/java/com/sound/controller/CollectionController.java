package com.sound.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.AudioModel;
import com.sound.model.Collection;
import com.sound.model.UserModel;
import com.sound.service.AudioService;
import com.sound.service.CollectionService;
import com.sound.service.RedisService;
import com.sound.vo.AlbumCollection;
import com.sound.vo.AudioCollection;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
  
@RestController
@Api(description = "个人收藏")
@RequestMapping(path = "/collection")
public class CollectionController extends BaseController<Collection> {

	@Resource
	private RedisService redisService;

	@Autowired
	private CollectionService conllectionService;

	@Autowired
	private AudioService audioService;

	private static final Logger logger = LoggerFactory.getLogger(CollectionController.class);

	@ApiOperation(value = "查询收藏节目接口")
	@GetMapping(path = "/audio.do")
	public Response findAudio(HttpServletRequest request) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			logger.info("查询收藏节目接口请重新登陆");
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("请重新登陆");
			return res;
		}

		try {

			List<AudioCollection> audios = conllectionService.findAudioCollectionByUserId(userModel.getId());
			logger.info("查询收藏节目接口收藏的音频"+audios.toString());
			if (null == audios || audios.size() == 0) {
				res.setRespone(ParamCode.SUCSESS);
				res.setMsg("查询收藏得节目成功");
				res.setData(null);
				return res;
			}
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("查询收藏得节目成功");
			res.setData(audios);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询收藏节目接口收藏的音频异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("查询收藏得节目失败");
		}
		return res;
	}

	@ApiOperation(value = "查询收藏专辑接口")
	@GetMapping(path = "/album.do")
	public Response findAlbum(HttpServletRequest request) throws ParseException {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("请重新登陆");
			return res;
		}

		try {

			List<AlbumCollection> audios = conllectionService.findAlbumCollectionByUserId(userModel.getId());
			/*
			 * if(null==audios||audios.size()==0) { res.setRespone(ParamCode.SUCSESS);
			 * res.setMsg("查询收藏得节目成功"); res.setData(null); return res; }
			 */
			logger.info("查询收藏节目接口收藏的专辑"+audios.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("查询收藏得专辑成功");
			res.setData(audios);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("查询收藏节目接口收藏的专辑异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("查询收藏得专辑失败");
		}
		return res;
	}

	@ApiOperation(value = "删除专辑接口")
	@GetMapping(path = "/delete.do")
	public Response deleteById(HttpServletRequest request, String ids) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
        if(null==ids) {
        	logger.info("删除收藏失败因为传进来的ids为null");
        	res.setRespone(ParamCode.FAIL);
			res.setMsg("删除收藏失败");
			return res;
        }
		try {
			String[] id = ids.split(",");
			List<Long> i = new ArrayList<>();
			for (String s : id) {
				i.add(Long.parseLong(s));
			}

			conllectionService.deleteCollectionByAlbumId(userModel.getId(), i);
	    	logger.info("id为"+userModel.getId()+"删除收藏专辑专辑的id"+i);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("删除收藏成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("删除收藏专辑异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("删除收藏失败");
		}

		return res;
	}

	@ApiOperation(value = "删除节目接口")
	@GetMapping(path = "/deleteAudio.do")
	public Response deleteAudioById(HttpServletRequest request, String ids) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		 if(null==ids) {
			 logger.info("删除收藏失败因为传进来的ids为null");
	        	res.setRespone(ParamCode.FAIL);
				res.setMsg("删除收藏失败");
				return res;
	        }
		try {
			String[] id = ids.split(",");
			List<Long> i = new ArrayList<>();
			for (String s : id) {
				i.add(Long.parseLong(s));
			}
			conllectionService.deleteCollectionByAudioId(userModel.getId(), i);
			logger.info("id为"+userModel.getId()+"删除收藏节目专辑的id"+i);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("删除收藏成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("删除收藏节目异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("删除收藏失败");
		}

		return res;
	}

	/*
	 * @ApiOperation(value = "批量删除节目专辑接口")
	 * 
	 * @GetMapping(path="/deleteAll.do") public Response
	 * deleteAllById(HttpServletRequest request,String ids) { Response res = new
	 * Response(); try { String [] idss=ids.split(",");
	 * if(null==idss||idss.length==0) { res.setRespone(ParamCode.FAIL);
	 * res.setMsg("传入正确的参数"); return res; } for(String id: idss) {
	 * conllectionService.deleteById(Long.parseLong(id)); }
	 * res.setRespone(ParamCode.SUCSESS); res.setMsg("批量删除收藏成功"); } catch (Exception
	 * e) { // TODO: handle exception res.setRespone(ParamCode.FAIL);
	 * res.setMsg("批量删除收藏失败"); }
	 * 
	 * return res; }
	 */
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value = "收藏节目接口")
	@GetMapping(path = "/saveAudio.do")
	public Response saveAudio(HttpServletRequest request, String id) {
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(request);
		if (null == userModel) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("请重新登陆");
			return res;
		}
		try {
			Collection c = conllectionService.iSAudioCollection(userModel.getId(), Long.parseLong(id));
			if (null != c) {
				logger.info("节目已经被收藏");
				res.setRespone(ParamCode.FAIL);
				res.setMsg("节目已经被收藏");
				return res;
			}
			Collection collection = new Collection();
			collection.setAudioId(Long.parseLong(id));
			collection.setUserId(userModel.getId());
			Collection coll=conllectionService.save(collection);
			logger.info(coll+"节目已经被收藏");
			AudioModel audio = audioService.findById(Long.parseLong(id));
			audio.setPraiseCount(audio.getPraiseCount() + 1);
			AudioModel a=audioService.save(audio);
			logger.info("节目已经被收藏后节目的详情"+audio.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("收藏节目成功");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("收藏节目失败", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("收藏节目失败");
		}

		return res;
	}

}
