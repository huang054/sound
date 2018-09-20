package com.controller;


import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.UserManageService;
import com.vo.DeviceInfo;
import com.vo.UserDetails;
import com.vo.UserInfoVo;
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
@Api(description = "用户管理")
@RestController
@RequestMapping(value = "/api/user")
public class UserManageController {


    @Autowired
    private UserManageService userManageService;

    @ApiOperation(value = "查询用户列表")
    @RequestMapping(value = "/findUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<UserInfoVo>> findUsers(@ApiParam("用户名") @RequestParam(required = false) String userName,
                                                        @ApiParam("手机号码") @RequestParam(required = false) String phoneNumber,
                                                        @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return userManageService.findUsers(userName, phoneNumber, pageNum);
    }

    @Secured(value = "ROLE_USER_FIND")
    @ApiOperation(value = "查询用户详情")
    @RequestMapping(value = "/findUserInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<UserDetails> findUserInfo(@ApiParam("用户Id") @RequestParam(required = false) Long userId) {

        if (userId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return userManageService.findUserInfo(userId);
    }

    @Secured(value = "ROLE_USER_DELETE")
    @ApiOperation(value = "删除用户")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteUser(@ApiParam("用户Id") @RequestParam(required = false) Long userId) {

        if (userId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return userManageService.deleteUser(userId);
    }

}
