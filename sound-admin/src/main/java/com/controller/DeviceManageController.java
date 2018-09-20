package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.DeviceManageService;
import com.vo.AlbumInfoVo;
import com.vo.DeviceInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author music
 */
@Api(description = "设备管理")
@RestController
@RequestMapping(value = "/api/device")
public class DeviceManageController {

    @Autowired
    private DeviceManageService deviceManageService;


    @Secured(value = "ROLE_DEVICES_FIND")
    @ApiOperation(value = "查询用户设备列表")
    @RequestMapping(value = "/findDevices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<DeviceInfo>> findDevices(@ApiParam("当前状态, 0 未连接; 1 App已连接") @RequestParam(required = false) Integer showStatus,
                                                          @ApiParam("设备名称") @RequestParam(required = false) String deviceName,
                                                          @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                                          @ApiParam(value = "绑定用户") @RequestParam(required = false) String bindUser) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return deviceManageService.findDevices(showStatus, deviceName, pageNum, bindUser);
    }

    @Secured(value = "ROLE_DEVICES_UNTIED")
    @ApiOperation(value = "解绑设备")
    @RequestMapping(value = "/untiedDevices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> untiedDevices(@ApiParam("绑定ID") @RequestParam(required = false) Long bindId) {
        if (bindId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return deviceManageService.untiedDevices(bindId);
    }
}
