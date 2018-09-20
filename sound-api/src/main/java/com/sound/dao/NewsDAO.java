package com.sound.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.NewsModel;
 
@Repository
public interface NewsDAO extends JpaRepository<NewsModel,Long>{

    @Query("select u from news u where u.showStatus=1")
    public Page<NewsModel> findByPage(Pageable pageable);

    @Query("select u from news u where u.title like %?1% or u.authorName like %?1% and u.showStatus=1")
    public Page<NewsModel> search(String search, Pageable pageable);
}
 