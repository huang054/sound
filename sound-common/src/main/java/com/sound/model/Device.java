package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.servlet.ServletRequest;

import java.io.Serializable;
import java.util.Date;

@Entity(name="device")
// 构建数据库表实体
@Table(name = "device")
public class Device implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * 设备管理
     */
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64)
	private String ticket;

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间

	@Column(columnDefinition="varchar(64) default ''")
    private String deviceId;//设备ID，UUID

	@Column(columnDefinition="varchar(64) default ''")
    private String phoneNum;//用户手机号

	@Column(columnDefinition="varchar(64) default ''")
    private String deviceName;//设备名

	@Column(columnDefinition="varchar(64) default ''")
    private String version;//固件版本

	@Column(columnDefinition="tinyint(1) default 0")
    private Boolean status;//当前状态,"0"未连接;"1"App已连接

	@Column(columnDefinition="varchar(32) default ''")
    private String userId;//用户id

	@Column(columnDefinition="varchar(100) default ''")
	private String ip;

	@Column(columnDefinition="varchar(100) default ''")
	private String port;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

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

	@Override
	public String toString() {
		return "Device [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", deviceId=" + deviceId
				+ ", phoneNum=" + phoneNum + ", deviceName=" + deviceName + ", version=" + version + ", status="
				+ status + ", userId=" + userId + ", ip=" + ip + ", port=" + port + "]";
	}
	
}
