package com.sound.service;

import com.sound.dao.SysMsgDAO;
import com.sound.model.SystemMsgModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */
@Service 
public class SysMsgService extends BaseService<SystemMsgModel>{

    @Autowired
    private SysMsgDAO sysMsgDAO;
 
    public Iterable<SystemMsgModel> findAllByTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE,-7);
        return sysMsgDAO.findAllByTime(cal.getTime());
    }

}
