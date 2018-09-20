package com.dao.mapper;

import com.dao.model.Event;
import com.dao.util.MyMapper;
import com.vo.ActivityInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventMapper extends MyMapper<Event> {

    /**
     * 查询活动信息
     *
     * @return
     */
    List<ActivityInfoVo> findActivityList();
}