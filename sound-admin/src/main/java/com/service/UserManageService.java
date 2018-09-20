package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.UserDetails;
import com.vo.UserInfoVo;

/**
 * @author music
 */
public interface UserManageService {

    /**
     * 查询用户列表
     *
     * @param userName    用户名
     * @param phoneNumber 手机号码
     * @param pageNum     当前页
     * @return
     */
    ResponseWrap<PageInfo<UserInfoVo>> findUsers(String userName, String phoneNumber, Integer pageNum);

    /**
     * 查询用户详情
     *
     * @param userId 用户ID
     * @return
     */
    ResponseWrap<UserDetails> findUserInfo(Long userId);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @return
     */
    ResponseWrap<Integer> deleteUser(Long userId);
}
