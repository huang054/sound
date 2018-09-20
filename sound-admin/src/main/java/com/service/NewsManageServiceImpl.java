package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.NewsMapper;
import com.dao.model.News;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vo.NewsDetails;
import com.vo.NewsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author music
 */
@Service
public class NewsManageServiceImpl implements NewsManageService {


    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询新闻资讯列表
     *
     * @param pageNum 当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<NewsInfo>> findNewsList(Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<NewsInfo> newsInfos = newsMapper.findNewsList();
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<NewsInfo>) newsInfos).toPageInfo());
    }

    /**
     * 查询新闻资讯信息
     *
     * @param newsId 新闻资讯ID
     * @return
     */
    @Override
    public ResponseWrap<NewsDetails> findNewsInfo(Long newsId) {
        if (newsId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        News news = newsMapper.selectByPrimaryKey(newsId);
        if (news == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1102);
        }
        NewsDetails newsDetails = new NewsDetails();
        newsDetails.setNewsId(news.getId());
        newsDetails.setImgUrl(news.getCoverurl());
        newsDetails.setNewsContent(news.getContent());
        newsDetails.setNewsTitle(news.getTitle());
        newsDetails.setPublisher(news.getAuthorName());
        newsDetails.setShowStatus(news.getShowStatus());
        return new ResponseWrap<>(ResponseCode.CODE_200, newsDetails);
    }

    /**
     * 修改新闻资讯信息
     *
     * @param newsDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateNewsInfo(NewsDetails newsDetails) {

        if (newsDetails.getNewsId() == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        News news = new News();
        news.setId(newsDetails.getNewsId());
        news.setAuthorName(newsDetails.getPublisher());
        news.setContent(newsDetails.getNewsContent());
        news.setTitle(newsDetails.getNewsTitle());
        news.setCoverurl(newsDetails.getImgUrl());
        news.setShowStatus(newsDetails.getShowStatus());
        return new ResponseWrap<>(ResponseCode.CODE_200, newsMapper.updateByPrimaryKeySelective(news));
    }

    /**
     * 添加新闻资讯信息
     *
     * @param newsDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertNewsInfo(NewsDetails newsDetails) {
        News news = new News();
        news.setAuthorName(newsDetails.getPublisher());
        news.setContent(newsDetails.getNewsContent());
        news.setTitle(newsDetails.getNewsTitle());
        news.setCoverurl(newsDetails.getImgUrl());
        news.setShowStatus(newsDetails.getShowStatus());
        news.setCreateTime(new Date());
        news.setPraiseCount(0L);
        news.setCommentCount(0L);
        news.setReadCount(0L);
        news.setColumnName("");
        news.setCoverName("");
        news.setSummary("");
        return new ResponseWrap<>(ResponseCode.CODE_200, newsMapper.insert(news));
    }

    /**
     * 删除新闻资讯信息
     *
     * @param newsId
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteNewsInfo(Long newsId) {
        if (newsId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, newsMapper.deleteByPrimaryKey(newsId));
    }
}
