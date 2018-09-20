package com.sound.service;

import com.sound.dao.UserMsgDAO;
import com.sound.model.UserMsgModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */ 
@Service
public class UserMsgService extends BaseService<UserMsgModel>{


    @Autowired
    private UserMsgDAO userMsgDAO;
 
    public List<UserMsgModel> findAllByTime(String userId){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE,-7);
        return userMsgDAO.findAllByTime(cal.getTime(),userId);
    }
    
    public UserMsgModel findByUserIdSysId(String userId,long SysId) {
    	return userMsgDAO.findByUserIdSysId(userId,SysId);
    }

}
