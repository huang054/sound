package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.NewsDetails;
import com.vo.NewsInfo;

/**
 * @author music
 */
public interface NewsManageService {

    /**
     * 查询新闻资讯列表
     *
     * @param pageNum
     * @return
     */
    ResponseWrap<PageInfo<NewsInfo>> findNewsList(Integer pageNum);


    /**
     * 查询新闻资讯信息
     *
     * @param newsId
     * @return
     */
    ResponseWrap<NewsDetails> findNewsInfo(Long newsId);

    /**
     * 修改新闻资讯信息
     *
     * @param newsDetails
     * @return
     */
    ResponseWrap<Integer> updateNewsInfo(NewsDetails newsDetails);

    /**
     * 添加新闻资讯信息
     *
     * @param newsDetails
     * @return
     */
    ResponseWrap<Integer> insertNewsInfo(NewsDetails newsDetails);

    /**
     * 删除新闻资讯信息
     *
     * @param newsId
     * @return
     */
    ResponseWrap<Integer> deleteNewsInfo(Long newsId);
}
