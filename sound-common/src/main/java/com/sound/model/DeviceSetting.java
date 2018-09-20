package com.sound.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name="deviceSetting")
//构建数据库表实体
@Table(name = "device_setting")
public class DeviceSetting {
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String domain;    //域名
	
	@Column
	private String port;	  //端口
	
	@Column
	private String ip;		  //ip

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "DeviceSetting [id=" + id + ", domain=" + domain + ", port=" + port + ", ip=" + ip + "]";
	}
	
	

}
