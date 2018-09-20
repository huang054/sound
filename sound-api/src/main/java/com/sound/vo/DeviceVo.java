package com.sound.vo;

import com.sound.model.Device;
 
/** 
 * Created by Administrator on 2018/5/20.
 */
public class DeviceVo {


    private String ip;

    private String port;

    private Boolean online;

    private String deviceId;

    private Device device;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

	@Override
	public String toString() {
		return "DeviceVo [ip=" + ip + ", port=" + port + ", online=" + online + ", deviceId=" + deviceId + ", device="
				+ device + "]";
	}
    
}
