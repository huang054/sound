package com.sound.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.UserMsgModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */
@Repository
public interface UserMsgDAO extends JpaRepository<UserMsgModel,Long> {
 

    @Query("select u from usermsg u where u.userId=:userId and u.createTime>=:dateTime")
    @Modifying
    public List<UserMsgModel> findAllByTime(@Param("dateTime") Date dateTime,@Param("userId")String userId);
    @Query("select u from usermsg u where u.userId=?1 and u.sysmsgId=?2")
    public UserMsgModel findByUserIdSysId(String userId,long SysId);
}
 