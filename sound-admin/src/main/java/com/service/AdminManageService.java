package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.AccountAuthVO;
import com.vo.AccountInfoVo;
import com.vo.CompetenceRes;
import com.vo.RoleInfoVo;

import java.util.List;

/**
 * @author music
 */
public interface AdminManageService {

    /**
     * 添加管理员
     *
     * @param accountInfoVo 信息参数
     * @return
     */
    ResponseWrap<Integer> addSysRole(AccountInfoVo accountInfoVo);

    /**
     * 根据登录账户查询用户信息
     *
     * @param userName 用户账户
     * @return
     */
    AccountAuthVO selectAuthByUsername(String userName);

    /**
     * 查询系统帐户列表
     *
     * @param pageNum 当前页
     * @return
     */
    ResponseWrap<PageInfo<AccountInfoVo>> findSystemUsers(Integer pageNum);

    /**
     * 查询系统帐户信息
     *
     * @param adminId 用户ID
     * @return
     */
    ResponseWrap<AccountInfoVo> findSystemUserInfo(Long adminId);

    /**
     * 删除系统帐户信息
     *
     * @param adminId 用户ID
     * @return
     */
    ResponseWrap<Integer> deleteSystemUser(Long adminId);

    /**
     * 修改系统帐户信息
     *
     * @param accountInfoVo
     * @return
     */
    ResponseWrap<String> updateSystemUser(AccountInfoVo accountInfoVo);

    /**
     * 添加角色信息
     *
     * @param roleName 角色名称
     * @return
     */
    ResponseWrap<Integer> addRole(String roleName);

    /**
     * 查询角色列表
     *
     * @return
     */
    ResponseWrap<List<RoleInfoVo>> findRoleInfo();

    /**
     * 更新角色权限
     *
     * @param roleInfoVos
     * @return
     */
    ResponseWrap<Integer> updateRoleInfos(List<RoleInfoVo> roleInfoVos);

    /**
     * 查询权限资源
     *
     * @return
     */
    ResponseWrap<List<CompetenceRes>> findCompetenceRec();

    /**
     * 根据名称查询用户Id
     *
     * @param userName
     * @return
     */
    Long findAccountIdFromUserName(String userName);

    /**
     * 修改用户信息
     *
     * @param passWord    密码
     * @param phoneNumber 手机号码
     * @param accountId   用户ID
     * @return
     */
    ResponseWrap<Integer> updateAdminUserInfo(String passWord, String phoneNumber, Long accountId);
}
