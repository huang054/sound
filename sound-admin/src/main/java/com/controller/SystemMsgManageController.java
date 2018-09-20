package com.controller;


import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.SystemMsgManageService;
import com.vo.SystemMsgInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author music
 */
@Api(description = "系统消息管理")
@RestController
@RequestMapping(value = "/api/systemMsg")
public class SystemMsgManageController {

    @Autowired
    private SystemMsgManageService systemMsgManageService;


    @ApiOperation(value = "查询系统消息列表")
    @RequestMapping(value = "/findSystemMsgList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<SystemMsgInfoVo>> findSystemMsgList(@ApiParam(value = "当前页 默认第一页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return systemMsgManageService.findSystemMsgList(pageNum);
    }


    @ApiOperation(value = "查询系统消息信息")
    @RequestMapping(value = "/findSystemMsgInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<SystemMsgInfoVo> findSystemMsgInfo(@ApiParam("系统消息Id") @RequestParam(required = false) Long SystemMsgId) {

        return systemMsgManageService.findSystemMsgInfo(SystemMsgId);
    }

    @Secured(value = "ROLE_SYSTEM_MSG_ADD_UPDATE")
    @ApiOperation(value = "修改系统消息信息 ")
    @RequestMapping(value = "/updateSystemMsgInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateSystemMsgInfo(@Valid @RequestBody SystemMsgInfoVo systemMsgInfoVo) {

        return systemMsgManageService.updateSystemMsgInfo(systemMsgInfoVo);
    }

    @Secured(value = "ROLE_SYSTEM_MSG_ADD_UPDATE")
    @ApiOperation(value = "插入系统消息信息 ")
    @RequestMapping(value = "/insertSystemMsgInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertSystemMsgInfo(@Valid @RequestBody SystemMsgInfoVo systemMsgInfoVo) {

        return systemMsgManageService.insertSystemMsgInfo(systemMsgInfoVo);
    }

    @Secured(value = "ROLE_SYSTEM_MSG_DELETE")
    @ApiOperation(value = "删除系统消息信息 ")
    @RequestMapping(value = "/deleteSystemMsgInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteSystemMsgInfo(@ApiParam("系统消息Id") @RequestParam(required = false) Long SystemMsgId) {
        if (SystemMsgId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }

        return systemMsgManageService.deleteSystemMsgInfo(SystemMsgId);
    }


}
