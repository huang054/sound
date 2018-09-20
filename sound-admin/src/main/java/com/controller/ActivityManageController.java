package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.ActivityManageService;
import com.vo.ActivityDetails;
import com.vo.ActivityInfoVo;
import com.vo.ActivityParticipateInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author music
 */
@Api(description = "活动管理")
@RestController
@RequestMapping(value = "/api/activity")
public class ActivityManageController {

    @Autowired
    private ActivityManageService activityManageService;


    @ApiOperation(value = "查询活动列表")
    @RequestMapping(value = "/findActivityList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<ActivityInfoVo>> findActivityList(@ApiParam(value = "当前页 默认第一页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return activityManageService.findActivityList(pageNum);
    }


    @ApiOperation(value = "查询活动信息")
    @RequestMapping(value = "/findActivityInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<ActivityDetails> findActivityInfo(@ApiParam("活动Id") @RequestParam(required = false) Long ActivityId) {

        return activityManageService.findActivityInfo(ActivityId);
    }

    @Secured(value = "ROLE_ACTIVITY_ADD_UPDATE")
    @ApiOperation(value = "修改活动信息 ")
    @RequestMapping(value = "/updateActivityInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateActivityInfo(@Valid @RequestBody ActivityDetails activityDetails) {

        return activityManageService.updateActivityInfo(activityDetails);
    }

    @Secured(value = "ROLE_ACTIVITY_ADD_UPDATE")
    @ApiOperation(value = "插入活动信息 ")
    @RequestMapping(value = "/insertActivityInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertActivityInfo(@Valid @RequestBody ActivityDetails activityDetails) {

        return activityManageService.insertActivityInfo(activityDetails);
    }

    @Secured(value = "ROLE_ACTIVITY_DELETE")
    @ApiOperation(value = "删除活动信息 ")
    @RequestMapping(value = "/deleteActivityInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteActivityInfo(@ApiParam("活动Id") @RequestParam(required = false) Long ActivityId) {
        if (ActivityId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }

        return activityManageService.deleteActivityInfo(ActivityId);
    }

    @ApiOperation(value = "查询活动参与状况")
    @RequestMapping(value = "/findActivitySituation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<ActivityParticipateInfo>> findActivitySituation(@ApiParam("活动Id") @RequestParam(required = false) Long activityId,
                                                                                 @ApiParam(value = "当前页 默认第一页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {
        if (activityId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }

        return activityManageService.findActivitySituation(activityId, pageNum);
    }

    @ApiOperation(value = "添加备注")
    @RequestMapping(value = "/addRemark", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> addRemark(@ApiParam("参与Id") @RequestParam(required = false) Long participateId,
                                           @ApiParam("备注") @RequestParam String remark) {
        if (participateId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }

        return activityManageService.addRemark(participateId, remark);
    }

    @ApiOperation(value = "活动参与状况结果导出", produces = "application/octet-stream")
    @ApiResponses({
            @ApiResponse(code = 200, message = "这里要注意一下，swagger对导出不是很友好，在swagger下载的文档会出现乱码，直接用url的方式去测试本接口")
    })
    @RequestMapping(value = "/ExcelExportSituation", method = RequestMethod.GET)
    public void ExcelExportSituation(@ApiParam("活动Id") @RequestParam Long activityId,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        activityManageService.ExcelExportSituation(activityId, request, response);
    }
}
