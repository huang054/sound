package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.DeviceMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vo.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author music
 */
@Service
public class DeviceManageServiceImpl implements DeviceManageService {


    @Autowired
    private DeviceMapper deviceMapper;

    /**
     * 查询用户设备列表
     *
     * @param showStatus 当前状态, 0 未连接; 1 App已连接
     * @param deviceName 设备名称
     * @param pageNum    当前页
     * @param bindUser   绑定用户
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<DeviceInfo>> findDevices(Integer showStatus, String deviceName, Integer pageNum, String bindUser) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<DeviceInfo> deviceInfos = deviceMapper.findDevices(showStatus, deviceName, bindUser);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<DeviceInfo>) deviceInfos).toPageInfo());
    }

    /**
     * 解绑设备
     *
     * @param bindId 绑定ID
     * @return
     */
    @Override
    public ResponseWrap<Integer> untiedDevices(Long bindId) {
        return new ResponseWrap<>(ResponseCode.CODE_200, deviceMapper.deleteByPrimaryKey(bindId));
    }
}
