package com.sound.controller;

import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.model.NewsModel;
import com.sound.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
  
/**
 * Created by Administrator on 2018/4/28.
 */
 
/**
 * 资讯
 */
@Controller
@Api(description = "资讯")
@RequestMapping(path = "/news")
public class NewsController extends BaseController<NewsModel> {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	// 1.资讯列表
	// 资讯详情查看
	@Autowired
	private NewsService newsService;

	/**
	 * 咨询列表 分页，时间排序
	 * 
	 * @param page
	 * @return
	 */
	@GetMapping("/newsList")
	public @ResponseBody Response<Page<NewsModel>> findAll(@RequestParam("page") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		Response<Page<NewsModel>> resp = new Response<>(true);
		PageRequest pageable = PageRequest.of(page, size);
		NewsModel news = new NewsModel();
		news.setShowStatus(true);
		Example<NewsModel> ex =Example.of(news);
		try {
			Page<NewsModel> newsPage = newsService.findAll(ex, pageable);
			if (newsPage != null)
				resp.setData(newsPage);
		} catch (Exception e) {
			logger.error("newsList fail:", e);
			resp.setRespone(ParamCode.FAIL);
		}
		return resp;
	}

	/**
	 * 查询单条咨询记录
	 */
	@GetMapping("/findById")
	public String findById(@RequestParam(required = true, defaultValue = "-1") Long id, final ModelMap model) {
		Response<NewsModel> resp;
		if (id == -1) {
			resp = new Response<NewsModel>(false);
			resp.setMsg("参数不合法");
		}
		resp = new Response<NewsModel>(true);
		try {
			NewsModel news = newsService.findById(id);
			news.setReadCount(news.getReadCount() + 1);
			newsService.save(news);
			resp.setData(news);
		} catch (Exception e) {
			logger.error("news findById fail!", e);
		}
		model.put("resp", resp);

		// model.addAttribute("resp", resp);
		return "newsDetail";

	}

	@GetMapping(path = "/search")
	@ApiOperation(value = "资讯搜索", tags = "")
	public @ResponseBody Response search(String search, int page, int size) {

		Response resp = new Response(true);
		resp.setRespone(ParamCode.SUCSESS);
		Sort sort = Sort.by(Sort.Direction.DESC, "readCount");

		resp.setData(newsService.search(search, PageRequest.of(page, size, sort)));
		return resp;
	}

	@GetMapping(path = "/praise")
	@ApiOperation(value = "添加点赞数", tags = "")
	public @ResponseBody Response praise(Long newsId) {
		Response resp = new Response(true);
		NewsModel newsModel = newsService.findById(newsId);
		if (newsModel != null) {
			newsModel.setPraiseCount(newsModel.getPraiseCount() + 1);
			newsService.save(newsModel);
			resp.setRespone(ParamCode.SUCSESS);
		} else {
			resp.setRespone(ParamCode.FAIL);
			resp.setMsg("新闻不存在");
		}

		return resp;
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/findInfoById")
	@ResponseBody
	public Response<NewsModel> findInfoById(@RequestParam(required = true, defaultValue = "-1") Long id) {
		Response<NewsModel> resp;
		if (id == -1) {
			resp = new Response<NewsModel>(false);
			resp.setMsg("参数不合法");
		}
		resp = new Response<NewsModel>(true);
		try {
			NewsModel news = newsService.findById(id);

			resp.setData(news);
		} catch (Exception e) {
			logger.error("news findById fail!", e);
		}

		return resp;

	}
}
