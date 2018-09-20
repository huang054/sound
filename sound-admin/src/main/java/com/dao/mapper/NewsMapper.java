package com.dao.mapper;

import com.dao.model.News;
import com.dao.util.MyMapper;
import com.vo.NewsInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author music
 */
@Repository
public interface NewsMapper extends MyMapper<News> {


    /**
     * 查询新闻资讯列表
     *
     * @return
     */
    List<NewsInfo> findNewsList();
}