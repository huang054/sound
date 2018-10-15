package com.sound.dao;


import com.sound.model.SystemRecommend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Repository
public interface SysRecommendDAO extends JpaRepository<SystemRecommend,Long> {

    //获取系统列表
    @Query("select u from sysRecommend  u where u.isable =1")
    public Page<SystemRecommend> findByPage(Pageable pageable);
}
