package com.sound.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.SystemMsgModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */ 
@Repository
public interface SysMsgDAO extends JpaRepository<SystemMsgModel,Long> {

    @Query("select u from sysmsg u where u.isable=1 and u.createTime>=:dateTime")
    @Modifying
    public Iterable<SystemMsgModel> findAllByTime(@Param("dateTime") Date dateTime);
}
 