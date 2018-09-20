package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Device implements Serializable {
    @Id
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 设备ID，UUID
     */
    @Column(name = "device_id")
    private String deviceId;

    /**
     * 设备名
     */
    @Column(name = "device_name")
    private String deviceName;

    private String ip;

    /**
     * 用户手机号
     */
    @Column(name = "phone_num")
    private String phoneNum;

    private String port;

    /**
     * 当前状态,"0"未连接;"1"App已连接
     */
    private Boolean status;

    private String ticket;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 固件版本
     */
    private String version;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取设备ID，UUID
     *
     * @return device_id - 设备ID，UUID
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 设置设备ID，UUID
     *
     * @param deviceId 设备ID，UUID
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备名
     *
     * @return device_name - 设备名
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * 设置设备名
     *
     * @param deviceName 设备名
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取用户手机号
     *
     * @return phone_num - 用户手机号
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * 设置用户手机号
     *
     * @param phoneNum 用户手机号
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获取当前状态,"0"未连接;"1"App已连接
     *
     * @return status - 当前状态,"0"未连接;"1"App已连接
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置当前状态,"0"未连接;"1"App已连接
     *
     * @param status 当前状态,"0"未连接;"1"App已连接
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取固件版本
     *
     * @return version - 固件版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置固件版本
     *
     * @param version 固件版本
     */
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", ip=").append(ip);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", port=").append(port);
        sb.append(", status=").append(status);
        sb.append(", ticket=").append(ticket);
        sb.append(", userId=").append(userId);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Device other = (Device) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getDeviceName() == null ? other.getDeviceName() == null : this.getDeviceName().equals(other.getDeviceName()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getPhoneNum() == null ? other.getPhoneNum() == null : this.getPhoneNum().equals(other.getPhoneNum()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getTicket() == null ? other.getTicket() == null : this.getTicket().equals(other.getTicket()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getDeviceName() == null) ? 0 : getDeviceName().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getTicket() == null) ? 0 : getTicket().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}