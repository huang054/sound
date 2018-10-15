package com.sound.service;

import com.sound.dao.SysRecommendDAO;
import com.sound.model.SystemRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Service
public class SysRecommendService extends BaseService<SystemRecommend>{

    @Autowired
    private SysRecommendDAO sysRecommendDAO;
    //根据优先级排序
    public Page<SystemRecommend> findByPage(Pageable pageable){
        return sysRecommendDAO.findByPage(pageable);
    }
}
