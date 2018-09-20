package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "设备信息")
public class DeviceInfo {

    @ApiModelProperty(value = "绑定ID")
    private Long bindId;

    @ApiModelProperty(value = "设备ID")
    private Long deviceId;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "绑定用户")
    private Long bindUser;

    @ApiModelProperty(value = "在线状态")
    private Integer showStatus;

    @ApiModelProperty(value = "版本号")
    private String versionNumber;

    public Long getBindId() {
        return bindId;
    }

    public void setBindId(Long bindId) {
        this.bindId = bindId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getBindUser() {
        return bindUser;
    }

    public void setBindUser(Long bindUser) {
        this.bindUser = bindUser;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "bindId=" + bindId +
                ", deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", bindUser=" + bindUser +
                ", showStatus=" + showStatus +
                ", versionNumber='" + versionNumber + '\'' +
                '}';
    }
}
