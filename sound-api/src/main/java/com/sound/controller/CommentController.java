package com.sound.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sound.service.RedisService;
import com.sound.utils.Page;
import com.sound.vo.CommentVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.CommentModel;
import com.sound.model.NewsModel;
import com.sound.model.UserModel;
import com.sound.service.CommentService;
import com.sound.service.NewsService;

import org.springframework.web.bind.annotation.RestController;
  
@RestController
@Api(description ="资讯评论")
@RequestMapping(path = "/comment")
public class CommentController extends BaseController<CommentModel> {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Autowired
	private NewsService newsService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private CommentService commentService;
	
	@GetMapping(path = "/findById")
	@Override
	public Response<CommentModel> findById(Long id) {
		return super.findById(id);
	}
 
	@GetMapping(path = "/save")
	@ApiOperation(value = "发表评论")
	public Response<CommentModel> save(HttpServletRequest request, String content, Long newsId) {

		Response<CommentModel> resp = new Response<CommentModel>(false);
		try {
			CommentModel t = new CommentModel();
			UserModel userModel = redisService.getUserInfo(request);
			// 验证登陆态
			if (userModel == null || userModel.getId() <= 0) {
				resp.setRespone(ParamCode.NOLOGIN);
				resp.setMsg("用户请登录！");
				return resp;
			}

			NewsModel news = newsService.findById(newsId);
			if (news != null) {
				t.setNewsId(news.getId());
				news.setCommentCount(news.getCommentCount() + 1);
				newsService.save(news);
				t.setContent(content);
				t.setName(userModel.getName());
				t.setHeaderUrl(userModel.getHeaderUrl());
				t.setUserId(userModel.getId().toString());
				super.service.save(t);
				resp.setRespone(ParamCode.SUCSESS);
				return resp;
			}

		} catch (Exception e) {
			logger.error("save fail:", e);
		}
		return new Response<CommentModel>(false);
	}

	@GetMapping(path = "/findAll")
	@Override
	public Response<Iterable<CommentModel>> findAll(@ModelAttribute CommentModel commentModel) {
		return super.findAll(commentModel);
	}

	@GetMapping(path = "/deleteById")
	@Override
	public Response<Void> deleteById(Long id) {

		return super.deleteById(id);
	}

	@GetMapping(path = "/findByPage")
	@ApiOperation(value = "评论分页列表")
	public Response findByPage(Long newsId, int page, int size) {
		Response resp = new Response();
		Map<String,Object> map =new HashMap<>();
		try {
			int count =commentService.findCount(newsId);
			Page p =new Page(count,page,size);
			List<CommentVo> cos =commentService.findComments(newsId, p);
			map.put("page", p);
			map.put("comment", cos);
			resp.setData(map);
			resp.setRespone(ParamCode.SUCSESS);
			return resp;
		} catch (Exception e) {
			logger.error("findByPage fail:", e);
			resp.setRespone(ParamCode.FAIL);
		}
		return resp;
	}
}
