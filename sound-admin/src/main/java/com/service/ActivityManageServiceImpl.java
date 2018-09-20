package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.EnrollMapper;
import com.dao.mapper.EventMapper;
import com.dao.model.Enroll;
import com.dao.model.Event;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.utils.util.ExcelExportUtils;
import com.vo.ActivityDetails;
import com.vo.ActivityInfoVo;
import com.vo.ActivityParticipateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author music
 */
@Service
public class ActivityManageServiceImpl implements ActivityManageService {


    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private EnrollMapper enrollMapper;

    /**
     * 查询活动列表
     *
     * @param pageNum
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<ActivityInfoVo>> findActivityList(Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<ActivityInfoVo> activityInfoVoList = eventMapper.findActivityList();
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<ActivityInfoVo>) activityInfoVoList).toPageInfo());
    }

    /**
     * 查询活动信息
     *
     * @param activityId
     * @return
     */
    @Override
    public ResponseWrap<ActivityDetails> findActivityInfo(Long activityId) {
        if (activityId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        Event event = eventMapper.selectByPrimaryKey(activityId);
        if (event == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        ActivityDetails activityDetails = new ActivityDetails();
        activityDetails.setActivityId(event.getId());
        activityDetails.setActivityAddress(event.getAddress());
        activityDetails.setContact(event.getAuthorName());
        activityDetails.setShowStatus(event.getShowStatus());
        activityDetails.setPhoneNumber(event.getPhone());
        activityDetails.setActivityContent(event.getContext());
        activityDetails.setActivityTitle(event.getTitle());
        activityDetails.setHomePageImgUrl(event.getImgUrl());
        activityDetails.setInternalImgUrl(event.getUrl());
        return new ResponseWrap<>(ResponseCode.CODE_200, activityDetails);
    }

    /**
     * 修改活动信息
     *
     * @param activityDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateActivityInfo(ActivityDetails activityDetails) {
        if (activityDetails.getActivityId() == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        Event event = new Event();
        event.setId(activityDetails.getActivityId());
        event.setAddress(activityDetails.getActivityAddress());
        event.setAuthorName(activityDetails.getContact());
        event.setShowStatus(activityDetails.getShowStatus());
        event.setPhone(activityDetails.getPhoneNumber());
        event.setContext(activityDetails.getActivityContent());
        event.setTitle(activityDetails.getActivityTitle());
        event.setImgUrl(activityDetails.getHomePageImgUrl());
        event.setUrl(activityDetails.getInternalImgUrl());
        return new ResponseWrap<>(ResponseCode.CODE_200, eventMapper.updateByPrimaryKeySelective(event));
    }

    /**
     * 增加活动信息
     *
     * @param activityDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertActivityInfo(ActivityDetails activityDetails) {
        Event event = new Event();
        event.setAddress(activityDetails.getActivityAddress());
        event.setAuthorName(activityDetails.getContact());
        event.setShowStatus(activityDetails.getShowStatus());
        event.setPhone(activityDetails.getPhoneNumber());
        event.setContext(activityDetails.getActivityContent());
        event.setTitle(activityDetails.getActivityTitle());
        event.setImgUrl(activityDetails.getHomePageImgUrl());
        event.setUrl(activityDetails.getInternalImgUrl());
        event.setCreateTime(new Date());
        event.setEventTime(new Date());
        return new ResponseWrap<>(ResponseCode.CODE_200, eventMapper.insert(event));
    }

    /**
     * 删除活动信息
     *
     * @param activityId
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteActivityInfo(Long activityId) {
        if (activityId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, eventMapper.deleteByPrimaryKey(activityId));
    }

    /**
     * 查询活动参与状况
     *
     * @param activityId 活动ID
     * @param pageNum    当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<ActivityParticipateInfo>> findActivitySituation(Long activityId, Integer pageNum) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<ActivityParticipateInfo> activityParticipateInfos = enrollMapper.findActivitySituation(activityId);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<ActivityParticipateInfo>) activityParticipateInfos).toPageInfo());
    }

    /**
     * 添加备注
     *
     * @param participateId 参与Id
     * @param remark        备注
     * @return
     */
    @Override
    public ResponseWrap<Integer> addRemark(Long participateId, String remark) {
        Enroll enroll = new Enroll();
        enroll.setId(participateId);
        enroll.setRemark(remark);
        return new ResponseWrap<>(ResponseCode.CODE_200, enrollMapper.updateByPrimaryKeySelective(enroll));
    }


    /**
     * 活动参与状况结果导出
     *
     * @param activityId
     * @return
     */
    @Override
    public void ExcelExportSituation(Long activityId, HttpServletRequest request, HttpServletResponse response) {
        List<ActivityParticipateInfo> activityParticipateInfos = enrollMapper.findActivitySituation(activityId);
        Event event = eventMapper.selectByPrimaryKey(activityId);

        Map<String, String> headMap = new LinkedHashMap<>(4);
        headMap.put("participateId", "序号");
        headMap.put("userName", "用户名");
        headMap.put("phoneNumber", "手机号");
        headMap.put("remark", "备注");
        try {
            ExcelExportUtils.export(event.getTitle() + "-活动参与状况", headMap, activityParticipateInfos, request, response, ExcelExportUtils.DATE_FORMAT_YYYYMMDD_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
