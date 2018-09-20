package com.controller;

import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.AdminManageService;

import com.vo.AccountInfoVo;
import com.vo.CompetenceRes;
import com.vo.RoleInfoVo;
import com.vo.UpdateRecVo;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/5/5
 */
@RestController
@RequestMapping(path = "/api/admin")
@Api(description = "帐户管理")
public class AdminManageController {

    @Autowired
    private AdminManageService adminManageService;

    @Secured(value = "ROLE_ACCOUNT_MANAGE")
    @ApiOperation(value = "添加系统账户")
    @RequestMapping(value = "/addSystemUser", method = RequestMethod.POST)
    public ResponseWrap<Integer> addSystemUser(@Valid @RequestBody AccountInfoVo accountInfoVo) {
        return adminManageService.addSysRole(accountInfoVo);
    }


    @Secured(value = "ROLE_ACCOUNT_MANAGE")
    @ApiOperation(value = "查询系统帐户列表")
    @RequestMapping(value = "/findSystemUsers", method = RequestMethod.GET)
    public ResponseWrap<PageInfo<AccountInfoVo>> findSystemUsers(@ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {
        return adminManageService.findSystemUsers(pageNum);
    }

    @Secured(value = "ROLE_ACCOUNT_MANAGE")
    @ApiOperation(value = "查询系统帐户信息")
    @RequestMapping(value = "/findSystemUserInfo", method = RequestMethod.GET)
    public ResponseWrap<AccountInfoVo> findSystemUserInfo(@ApiParam(value = "用户ID") @NotNull(message = "用户ID不能为空") @RequestParam Long adminId) {
        return adminManageService.findSystemUserInfo(adminId);
    }

    @Secured(value = "ROLE_ACCOUNT_MANAGE")
    @ApiOperation(value = "修改系统帐户信息")
    @RequestMapping(value = "/updateSystemUser", method = RequestMethod.POST)
    public ResponseWrap<String> updateSystemUser(@Valid @RequestBody AccountInfoVo accountInfoVo) {
        return adminManageService.updateSystemUser(accountInfoVo);
    }

    @Secured(value = "ROLE_ACCOUNT_MANAGE")
    @ApiOperation(value = "删除系统帐户信息")
    @RequestMapping(value = "/deleteSystemUser", method = RequestMethod.DELETE)
    public ResponseWrap<Integer> deleteSystemUser(@ApiParam(value = "用户ID") @NotNull(message = "用户ID不能为空") @RequestParam Long adminId) {
        return adminManageService.deleteSystemUser(adminId);
    }

    @Secured(value = "ROLE_MANAGE")
    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public ResponseWrap<Integer> addRole(@ApiParam(value = "角色名称") @NotNull(message = "角色名称不能为空") @RequestParam String roleName) {
        return adminManageService.addRole(roleName);
    }

    @Secured(value = "ROLE_MANAGE")
    @ApiOperation(value = "查询角色列表")
    @RequestMapping(value = "/findRoleInfo", method = RequestMethod.GET)
    public ResponseWrap<List<RoleInfoVo>> findRoleInfo() {
        return adminManageService.findRoleInfo();
    }

    @Secured(value = "ROLE_COMPETENCE")
    @ApiOperation(value = "查询权限资源列表")
    @RequestMapping(value = "/findCompetenceRec", method = RequestMethod.GET)
    public ResponseWrap<List<CompetenceRes>> findCompetenceRec() {
        return adminManageService.findCompetenceRec();
    }


    @Secured(value = "ROLE_MANAGE")
    @ApiOperation(value = "更新角色属性")
    @RequestMapping(value = "/updateRoleInfos", method = RequestMethod.POST)
    public ResponseWrap<Integer> updateRoleInfos(@Valid @RequestBody UpdateRecVo roleInfoVos) {
        if (roleInfoVos == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return adminManageService.updateRoleInfos(roleInfoVos.getRoleInfoVos());
    }

    @ApiOperation(value = "修改个人信息")
    @RequestMapping(value = "/updateAdminUserInfo", method = RequestMethod.POST)
    public ResponseWrap<Integer> updateAdminUserInfo(Principal principal,
                                                     @ApiParam(value = "密码") @RequestParam(required = false) String passWord,
                                                     @ApiParam(value = "手机号码") @RequestParam(required = false) String phoneNumber) {
        if (principal == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1004);
        }
        String userName = principal.getName();
        if (StringUtil.isNullOrEmpty(userName)) {
            return new ResponseWrap<>(ResponseCode.CODE_1002);
        }
        Long accountId = adminManageService.findAccountIdFromUserName(userName);
        if (accountId == null || accountId == 0) {
            return new ResponseWrap<>(ResponseCode.CODE_1101);
        }
        return adminManageService.updateAdminUserInfo(passWord, phoneNumber, accountId);
    }


}
