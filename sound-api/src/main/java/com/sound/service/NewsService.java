package com.sound.service;

import com.sound.dao.NewsDAO;
import com.sound.model.NewsModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NewsService extends BaseService<NewsModel>{
  
    @Autowired
    private NewsDAO newsDAO;

    public Page<NewsModel> findByPage(Pageable pageable){
        return newsDAO.findByPage(pageable);
    }

    public Page<NewsModel> search(String search,Pageable pageable){

        return newsDAO.search(search, pageable);
    }
}
