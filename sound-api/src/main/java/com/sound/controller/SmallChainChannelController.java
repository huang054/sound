package com.sound.controller;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.Album;
import com.sound.model.AudioModel;
import com.sound.model.Banner;
import com.sound.model.Collection;
import com.sound.model.Currency;
import com.sound.model.ImgUrl;
import com.sound.model.RecentAlbumModel;
import com.sound.model.RecentAudioModel;
import com.sound.model.UserModel;
import com.sound.service.AlbumService;
import com.sound.service.AudioService;
import com.sound.service.CollectionService;
import com.sound.service.CurrencyService;
import com.sound.service.ImgUrlService;
import com.sound.service.RecentAlbumService;
import com.sound.service.RecentAudioService;
import com.sound.service.RedisService;

import io.netty.util.internal.SystemPropertyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
@RestController
@Api(description = "小链频道")
@RequestMapping(path = "/smallChainChannel")
public class SmallChainChannelController extends BaseController<AudioModel> {
	private static final Logger logger = LoggerFactory.getLogger(SmallChainChannelController.class);
	@Autowired
	private RecentAlbumService recentAlbumService;
	@Autowired
	private RecentAudioService recentAudioService;
	@Resource
	private RedisService redisService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private AudioService audioService;
	@Autowired
	private AlbumService albumService;
	@Autowired
	private CollectionService collectionService;
	@Autowired
	private ImgUrlService imgUrlService;
	@Autowired
	private CurrencyService currencyService;
	@Value("${netty.server.url}")
	private String nettyServerURL;

	@ApiOperation(value = "banner接口")
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
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Banner> banner = audioService.findBanner();
			logger.info("小链频道banner接口"+banner.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("banner查询成功");
			map.put("banner", banner);
			res.setData(map);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("小链频道banner接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("banner查询失败");
			res.setData("");
		}

		return res;
	}

	@GetMapping(path = "/findAllByAlbumId")
	@ApiOperation(value = "根据albumId下所有音频", tags = "", response = AudioModel.class)
	public Response findAll(HttpServletRequest req, Long albumId) {
		// UserModel user = redisService.getUserInfo(req);
		Response res = new Response();
		UserModel user = redisService.getUserInfo(req);
		// 验证登陆态
		if (user == null || user.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		AudioModel audioModel = new AudioModel();
		Map<String, Object> map = new HashMap<>();
		Collection coll = collectionService.iSCollection(user.getId(), albumId);
		boolean b;
		if (null == coll) {
			b = false;
		} else {
			b = true;
		}
		audioModel.setAlbumId(albumId);
		// Iterable<AudioModel>=super.findAll(audioModel);
		List<AudioModel> audios = audioService.findAudioByAlbumId(albumId);
		//Collections.sort(audios, Comparator.comparing(AudioModel::getId));
		logger.info("小链频道专辑查找所有音频接口"+audios.toString());
		logger.info("专辑是否收藏"+b);
		map.put("isCollection", b);
		map.put("audios", audios);
		res.setData(map);
		return res;
	}

	@GetMapping(path = "/isCollectionByAudioId")
	@ApiOperation(value = "根据audioId查看音频是否收藏")
	public Response findAudio(HttpServletRequest req, Long audioId) {
		// UserModel user = redisService.getUserInfo(req);
		Response res = new Response();
		UserModel userModel = redisService.getUserInfo(req);
		// 验证登陆态
		if (userModel == null || userModel.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		Collection coll = collectionService.iSAudioCollection(userModel.getId(), audioId);
		logger.info("小链频道音频是否收藏根据音频的null否判断"+(null != coll));
		res.setData(null != coll);
		return res;
	}

	@GetMapping(path = "/saveAlbum")
	@ApiOperation(value = "收藏专辑接口")
	public Response saveAlbum(HttpServletRequest req, Long albumId) {
		// UserModel user = redisService.getUserInfo(req);
		Response res = new Response();
		UserModel user = redisService.getUserInfo(req);
		// 验证登陆态
		if (user == null || user.getId() <= 0) {
			res.setRespone(ParamCode.NOLOGIN);
			res.setMsg("用户请登录！");
			return res;
		}
		try {
			Collection c = collectionService.iSCollection(user.getId(), albumId);
			if (null != c) {
				res.setMsg("已经被收藏");
				res.setRespone(ParamCode.FAIL);
				return res;
			}
			Collection coll = new Collection();
			coll.setUserId(user.getId());
			coll.setAlbumId(albumId);
			collectionService.save(coll);
			logger.info("收藏专辑接口"+coll.toString()+"用户id"+user.getId()+"频道的ID"+albumId);
			res.setMsg("收藏成功");
			res.setRespone(ParamCode.SUCSESS);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("收藏专辑接口失败", e);
			res.setMsg("收藏失败");
			res.setRespone(ParamCode.FAIL);
		}
		return res;
	}

	@GetMapping(path = "/findByPage")
	@ApiOperation(value = "分页查找音频", tags = "", response = AudioModel.class)
	public Response<Page<AudioModel>> findByPage(int page,
			@RequestParam(required = true, defaultValue = "10") int size) {
		Response<Page<AudioModel>> resp = new Response<Page<AudioModel>>(true);

		Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(page, size, sort);
		try {
			AudioModel a=new AudioModel();
			a.setShowStatus(true);
			 Example<AudioModel> t = Example.of(a);
			Page<AudioModel> p = audioService.findAll(t,pageable);
			logger.info("分页查找音频"+p.toString());
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(p);
			return resp;
		} catch (Exception e) {
			logger.error("audio findByPage fail", e);
			resp.setRespone(ParamCode.FAIL);
			return resp;
		}

	}

	@ApiOperation(value = "推荐的节目接口")
	@GetMapping(path = "findAudioByPriority.do")
	public Response findAudioByPriority(int page, @RequestParam(required = true, defaultValue = "6") int size) {
		Response resp = new Response();
		Sort sort = Sort.by(Sort.Direction.DESC, "roadShow", "readCount", "praiseCount", "createTime");
        if(page==0) {
        	resp.setRespone(ParamCode.FAIL);
			resp.setMsg("page不能为0");
			return resp;
        }
		try {

			AudioModel a = new AudioModel();
			a.setIsRoadShow(1);
			a.setShowStatus(true);
			Example<AudioModel> t = Example.of(a);
			List<AudioModel> p = audioService.findAll(t, sort);
			if (p.size() < 6) {
				resp.setRespone(ParamCode.SUCSESS);
				resp.setMsg("数量不足六个");
			
				resp.setData(p);
				return resp;
			}
			List<AudioModel> audios = new LinkedList<>();
			/**
			 * 换一换算法
			 */
			if (page * size <= p.size()) {
	 			for (int i = (page - 1) * size; i < (page * size); i++) {
					audios.add(p.get(i));
				}
			} else {
				int j = page * size % p.size();
			    if (j >= 6) {
					for (int i = j - 6; i < j; i++) {

						audios.add(p.get(i));
					}
				} else {

					for (int i = 0; i < j; i++) {
						audios.add(p.get(i));
					}
					for (int i = p.size() - (6 - j); i < p.size(); i++) {
						audios.add(p.get(i));
					}
				}
			}

			/*
			 * for(AudioModel c:p) { if(c.getPlayCount()>10000) {
			 * 
			 * } }
			 */
            logger.info("推荐的节目接口换一换"+audios.toString());
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(audios);
			return resp;
		} catch (Exception e) {
			logger.error("推荐的节目接口换一换fail", e);
			resp.setRespone(ParamCode.FAIL);
			return resp;
		}
	}

	@ApiOperation(value = "轻路演的节目分页接口")
	@GetMapping(path = "findAudioByRoadShow.do")
	public Response<Page<AudioModel>> findAudioByRoadShow(int page,
			@RequestParam(required = true, defaultValue = "10") int size) {
		Response<Page<AudioModel>> resp = new Response<Page<AudioModel>>(true);
		Sort sort = Sort.by(Sort.Direction.DESC, "roadShow", "readCount", "praiseCount", "createTime");
		// Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
		PageRequest pageable = PageRequest.of(page, size, sort);

		try {

			AudioModel a = new AudioModel();
			a.setIsRoadShow(2);
			a.setShowStatus(true);
			Example<AudioModel> t = Example.of(a);
			Page<AudioModel> p = audioService.findAll(t, pageable);
			logger.info("轻路演的节目分页接口"+p.toString());
			resp.setRespone(ParamCode.SUCSESS);
			resp.setData(p);
			return resp;
		} catch (Exception e) {
			logger.error("轻路演的节目分页接口 fail", e);
			resp.setRespone(ParamCode.FAIL);
			return resp;
		}
	}

	@ApiOperation(value = "顶级推荐的节目接口")
	@GetMapping(path = "findAudioPriority.do")
	public Response findAudioPriority() {
		Response res = new Response();
		try {
			List<AudioModel> audio = audioService.findAudioPriority();
			logger.info("顶级推荐的节目接口"+audio.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("顶级推荐的节目查询成功");
			res.setData(audio);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("顶级推荐的节目接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("顶级推荐的节目查询失败");
			res.setData("");
		}
		return res;
	}

	@ApiOperation(value = "顶级轻路演的节目接口")
	@GetMapping(path = "findAudioRoadShow.do")
	public Response findAudioRoadShow() {
		Response res = new Response();
		try {
			List<AudioModel> audio = audioService.findAudioRoadShow();
			logger.info("顶级推荐的节目接口"+audio.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("顶级轻路演的节目查询成功");
			res.setData(audio);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("顶级轻路演的节目接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("顶级轻路演的节目查询失败");
			res.setData("");
		}
		return res;
	}

	@ApiOperation(value = "特色频道的节目接口")
	@GetMapping(path = "findAlbumByType.do")
	public Response findAlbumByType(String type) {// type2为特色频道1为推荐频道
		Response res = new Response();
		try {
			List<Album> albums = albumService.findAlbumByType(type);
			logger.info("特色频道的节目接口"+albums.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("特色频道查询成功");
			res.setData(albums);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("特色频道的节目接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("特色频道查询失败");
			res.setData("");
		}

		return res;
	}

	@ApiOperation(value = "显示4条的节目搜索接口")
	@GetMapping(path = "findAudioLimit.do")
	public Response findAudioLimit(String search) {// type2为特色频道
		Response res = new Response();
		try {

			List<AudioModel> audios = audioService.searchLimit(search);
			logger.info("显示4条的节目搜索接口"+audios.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("节目搜索成功");
			res.setData(audios);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("显示4条的节目搜索接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("节目搜索失败");
			res.setData("");
		}

		return res;
	}

	@ApiOperation(value = "所有节目搜索接口")
	@GetMapping(path = "findAudio.do")
	public Response findAudio(String search) {// type2为特色频道
		Response res = new Response();
		try {
			List<AudioModel> audios = audioService.search(search);
			logger.info("所有节目搜索接口"+audios.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("节目搜索成功");
			res.setData(audios);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("所有节目搜索接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("节目搜索失败");
			res.setData("");
		}

		return res;
	}

	@ApiOperation(value = "显示4条的专辑搜索接口")
	@GetMapping(path = "findAlbumLimit.do")
	public Response findAlbumLimit(String search) {// type2为特色频道
		Response res = new Response();
		try {
			List<Album> albums = albumService.searchLimit(search);
			logger.info("显示4条的专辑搜索接口"+albums.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("专辑搜索成功");
			res.setData(albums);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("显示4条的专辑搜索接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("专辑搜索失败");
			res.setData("");
		}

		return res;
	}

	@ApiOperation(value = "所有专辑搜索接口")
	@GetMapping(path = "findAlbum.do")
	public Response findAlbum(String search) {// type2为特色频道
		Response res = new Response();
		try {
			List<Album> albums = albumService.search(search);
			logger.info("所有专辑搜索接口"+albums.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("专辑搜索成功");
			res.setData(albums);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("所有专辑搜索接口异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("专辑搜索失败");
			res.setData("");
		}

		return res;
	}

	@PostMapping(path = "/play.do")
	@ApiOperation(value = "专辑播放列表")
	public Response<String> play(String audioIds, @RequestParam(value = "deviceId") String deviceId,
			@RequestParam(value = "opCpde") String opCode,  @RequestParam(value = "index",required =true, defaultValue = "1")int index,HttpServletRequest req) {
		Response<String> resp = new Response<String>(true);
		String[] sts = audioIds.split(",");
		List<Long> ids = new ArrayList<>();
		for (String id : sts) {
			ids.add(Long.valueOf(id));
		}

		

		// UserModel user = redisService.getUserInfo(req);
		UserModel user = redisService.getUserInfo(req);
		// 验证登陆态
		if (user == null || user.getId() <= 0) {
			resp.setRespone(ParamCode.NOLOGIN);
			resp.setMsg("用户请登录！");
			return resp;
		}
		try {
			for (int i = 0; i < ids.size(); i++) {
				AudioModel audioModel = audioService.findById(ids.get(i));
				if (audioModel == null) {
					continue;
				}
				if(audioModel.getIsRoadShow()!=2) {
					albumService.play(audioModel.getAlbumId());
				}
				
				audioModel.setPlayCount(audioModel.getPlayCount() + 1);
				RecentAudioModel model1 = new RecentAudioModel();
				model1.setUserId(String.valueOf(user.getId()));
				model1.setAudioId(ids.get(i));
				if(i+1==index)
				audioService.save(audioModel);
			/*	List<RecentAudioModel> list = recentAudioService.findAll(model1);// 只有一条
				if(null==list||list.size()==0) {
					model1.setPlaydate(new Date());
					recentAudioService.save(model1);
				}
				for (RecentAudioModel each : list) {
					each.setPlaydate(new Date());
					recentAudioService.save(each);
				}
*/
			}

			Iterable<AudioModel> audioModels = this.service.findAllById(ids);
			
			String body = restTemplate.postForObject(nettyServerURL + "/remote/play/{userId}/{deviceId}/{opCode}/{index}",
					audioModels, String.class, user.getPhoneNum(), deviceId, opCode,index);
			// 入库最近专辑和最近音频
			if (ids.size() > 0) {
				/*AudioModel audioModel = audioService.findById(ids.get(0));
				if (audioModel != null) {
					RecentAlbumModel recentAlbumModel = new RecentAlbumModel();
					recentAlbumModel.setUserId(user.getPhoneNum());
					recentAlbumModel.setAlbumId(audioModel.getAlbumId());
					//recentAlbumService.save(recentAlbumModel);
				}*/

			}
			if (!"000".equals(body)) {
				resp.setCode("00100");
				resp.setMsg(body);
			}
			resp.setData(body);
		} catch (Exception e) {
			logger.error("audio play fail", e);
			resp.setRespone(ParamCode.FAIL);
		}
		return resp;

	}

	@PostMapping(path = "/img.do")
	@ApiOperation(value = "小链频道图片", tags = "")
	public Response getImgUrl() {
		Response res = new Response();

		try {
			ImgUrl img = imgUrlService.findAll().iterator().next();
			logger.info("小链频道图片"+img.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("小链频道图片查找成功");
			res.setData(img);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("小链频道图片异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("小链频道图片查找失败");

		}
		return res;
	}

	@PostMapping(path = "/findCurrency.do")
	@ApiOperation(value = "节目播放获取币种种类", tags = "")
	public Response findCurrency(long id) {
		Response res = new Response();

		try {
			List<Currency> currencys = currencyService.findCurrencyByAudioId(id);
			logger.info("节目播放获取币种种类"+currencys.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("币种查找成功");
			res.setData(currencys);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("节目播放获取币种种类异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("币种查找失败");

		}
		return res;
	}

	@PostMapping(path = "/findAudioById.do")
	@ApiOperation(value = "获取节目的详细信息", tags = "")
	public Response findAudioById(long id) {
		Response res = new Response();
		Map<String, Object> map = new HashMap<>();
		try {
			AudioModel audio = audioService.findById(id);
			List<Currency> currencys = currencyService.findCurrencyByAudioId(id);
			logger.info("节目播放获取币种种类"+currencys.toString());
			logger.info("节目播放详细信息"+audio.toString());
			map.put("audio", audio);
			map.put("currencys", currencys);
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("音频信息查询成功");
			res.setData(map);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("节目播放详细信息异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("音频信息查询失败");
		}
		return res;
	}
	
	@PostMapping(path = "/findAlbumByPage.do")
	@ApiOperation(value = "所有频道的分页", tags = "")
	public Response findAlbumByPage(int page,int size) {
		Response res = new Response();
		try {
			Sort sort = Sort.by(Sort.Direction.DESC,"type", "createTime");
			PageRequest pageable = PageRequest.of(page, size,sort);
			Album a =new Album();
			a.setShowStatus(true);
			Example<Album> t =Example.of(a);
			Page<Album> albums=albumService.findAll(t,pageable);
			logger.info("findAlbumByPage.do:page"+page+"size"+size);
			logger.info(albums.toString());
			res.setRespone(ParamCode.SUCSESS);
			res.setMsg("所有频道的分页查询");
			res.setData(albums);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("所有频道的分页信息异常", e);
			res.setRespone(ParamCode.FAIL);
			res.setMsg("所有频道的分页查询异常");
		}
	
		return res;
	}
}
