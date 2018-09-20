package com.dao.mapper;

import com.dao.model.Device;
import com.dao.util.MyMapper;
import com.vo.DeviceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMapper extends MyMapper<Device> {

    /**
     * 查询用户设备列表
     *
     * @param showStatus 当前状态, 0 未连接; 1 App已连接
     * @param deviceName 设备名称
     * @param bindUser   绑定用户
     * @return
     */
    List<DeviceInfo> findDevices(@Param(value = "showStatus") Integer showStatus,
                                 @Param(value = "deviceName") String deviceName,
                                 @Param(value = "bindingUser") String bindUser);
}