package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.NewsManageService;
import com.vo.BannerInfoVo;
import com.vo.NewsDetails;
import com.vo.NewsInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author music
 */
@Api(description = "新闻资讯管理")
@RestController
@RequestMapping(value = "/api/news")
public class NewsManageController {

    @Autowired
    private NewsManageService newsManageService;


    @ApiOperation(value = "查询新闻资讯列表")
    @RequestMapping(value = "/findNewsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<NewsInfo>> findNewsList(@ApiParam(value = "当前页 默认第一页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return newsManageService.findNewsList(pageNum);
    }


    @ApiOperation(value = "查询新闻资讯信息")
    @RequestMapping(value = "/findNewsInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<NewsDetails> findNewsInfo(@ApiParam("新闻资讯Id") @RequestParam(required = false) Long newsId) {

        return newsManageService.findNewsInfo(newsId);
    }

    @Secured(value = "ROLE_NEWS_ADD_UPDATE")
    @ApiOperation(value = "修改新闻资讯信息 ")
    @RequestMapping(value = "/updateNewsInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateNewsInfo(@Valid @RequestBody NewsDetails newsDetails) {

        return newsManageService.updateNewsInfo(newsDetails);
    }

    @Secured(value = "ROLE_NEWS_ADD_UPDATE")
    @ApiOperation(value = "插入新闻资讯信息 ")
    @RequestMapping(value = "/insertNewsInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertNewsInfo(@Valid @RequestBody NewsDetails newsDetails) {

        return newsManageService.insertNewsInfo(newsDetails);
    }

    @Secured(value = "ROLE_NEWS_DELETE")
    @ApiOperation(value = "删除新闻资讯信息 ")
    @RequestMapping(value = "/deleteNewsInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteNewsInfo(@ApiParam("新闻资讯Id") @RequestParam(required = false) Long newsId) {
        if (newsId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }

        return newsManageService.deleteNewsInfo(newsId);
    }


}
