package com.service;


import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.ActivityDetails;
import com.vo.ActivityInfoVo;
import com.vo.ActivityParticipateInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author music
 */
public interface ActivityManageService {

    /**
     * 查询活动列表
     *
     * @param pageNum
     * @return
     */
    ResponseWrap<PageInfo<ActivityInfoVo>> findActivityList(Integer pageNum);

    /**
     * 查询活动信息
     *
     * @param activityId
     * @return
     */
    ResponseWrap<ActivityDetails> findActivityInfo(Long activityId);

    /**
     * 修改活动信息
     *
     * @param activityDetails
     * @return
     */
    ResponseWrap<Integer> updateActivityInfo(ActivityDetails activityDetails);

    /**
     * 增加活动信息
     *
     * @param activityDetails
     * @return
     */
    ResponseWrap<Integer> insertActivityInfo(ActivityDetails activityDetails);

    /**
     * 删除活动信息
     *
     * @param activityId
     * @return
     */
    ResponseWrap<Integer> deleteActivityInfo(Long activityId);

    /**
     * 查询活动参与状况
     *
     * @param activityId 活动ID
     * @param pageNum    当前页
     * @return
     */
    ResponseWrap<PageInfo<ActivityParticipateInfo>> findActivitySituation(Long activityId, Integer pageNum);

    /**
     * 添加备注
     *
     * @param participateId 参与Id
     * @param remark        备注
     * @return
     */
    ResponseWrap<Integer> addRemark(Long participateId, String remark);

    /**
     * 活动参与状况结果导出
     *
     * @param activityId
     * @return
     */
    void ExcelExportSituation(Long activityId, HttpServletRequest request, HttpServletResponse response);
}
