package com.sound.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name ="CurrencyWater")
@Table(name = "Currency_water")
public class CurrencyWater implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="bigint(20) default 0")
	private long userId;
	@Column(columnDefinition="varchar(255)  DEFAULT ''")
	private String phone;
	@Column(columnDefinition="varchar(255)  DEFAULT ''")
	private String cucrrencyName;
	@Column(precision=12,scale=8)
	private BigDecimal currency;
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCucrrencyName() {
		return cucrrencyName;
	}
	public void setCucrrencyName(String cucrrencyName) {
		this.cucrrencyName = cucrrencyName;
	}
	public BigDecimal getCurrency() {
		return currency;
	}
	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CurrencyWater [id=" + id + ", userId=" + userId + ", phone=" + phone + ", cucrrencyName="
				+ cucrrencyName + ", currency=" + currency + ", createTime=" + createTime + "]";
	}

	
}
