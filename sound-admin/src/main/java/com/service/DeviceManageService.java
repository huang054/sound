package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.DeviceInfo;

/**
 * @author music
 */
public interface DeviceManageService {

    /**
     * 查询用户设备列表
     *
     * @param showStatus 当前状态, 0 未连接; 1 App已连接
     * @param deviceName 设备名称
     * @param pageNum    当前页
     * @param bindUser   绑定用户
     * @return
     */
    ResponseWrap<PageInfo<DeviceInfo>> findDevices(Integer showStatus, String deviceName, Integer pageNum, String bindUser);

    /**
     * 解绑设备
     *
     * @param bindId 绑定ID
     * @return
     */
    ResponseWrap<Integer> untiedDevices(Long bindId);
}
