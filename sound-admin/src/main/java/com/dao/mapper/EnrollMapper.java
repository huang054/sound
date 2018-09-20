package com.dao.mapper;

import com.dao.model.Enroll;
import com.dao.util.MyMapper;
import com.vo.ActivityParticipateInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollMapper extends MyMapper<Enroll> {

    /**
     * 查询活动参与状况
     *
     * @param activityId 活动ID
     * @return
     */
    List<ActivityParticipateInfo> findActivitySituation(Long activityId);
}