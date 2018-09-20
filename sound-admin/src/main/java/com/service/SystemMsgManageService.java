package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.SystemMsgInfoVo;

/**
 * @author music
 */
public interface SystemMsgManageService {

    /**
     * 查询系统消息列表
     *
     * @param pageNum 当前页
     * @return
     */
    ResponseWrap<PageInfo<SystemMsgInfoVo>> findSystemMsgList(Integer pageNum);

    /**
     * 查询系统消息信息
     *
     * @param systemMsgId 系统消息ID
     * @return
     */
    ResponseWrap<SystemMsgInfoVo> findSystemMsgInfo(Long systemMsgId);

    /**
     * 修改系统消息信息
     *
     * @param systemMsgInfoVo
     * @return
     */
    ResponseWrap<Integer> updateSystemMsgInfo(SystemMsgInfoVo systemMsgInfoVo);

    /**
     * 插入系统消息信息
     *
     * @param systemMsgInfoVo
     * @return
     */
    ResponseWrap<Integer> insertSystemMsgInfo(SystemMsgInfoVo systemMsgInfoVo);

    /**
     * 删除系统消息信息
     *
     * @param systemMsgId 系统消息ID
     * @return
     */
    ResponseWrap<Integer> deleteSystemMsgInfo(Long systemMsgId);
}
