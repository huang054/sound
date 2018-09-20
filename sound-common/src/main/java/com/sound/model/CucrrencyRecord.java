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
@Entity(name ="CurrencyRecord")
@Table(name = "currency_record")
public class CucrrencyRecord implements Serializable{

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
	@Column(columnDefinition="bigint(20) default 0")
	private long audioId;
	@Column(columnDefinition="varchar(255)  DEFAULT ''")
	private String cucrrencyName;
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;
	@Column(precision=12,scale=8)
	private BigDecimal currency;
	@Column(columnDefinition="varchar(255)  DEFAULT ''")
	private String accees;
	
	
	
	public String getAccees() {
		return accees;
	}
	public void setAccees(String accees) {
		this.accees = accees;
	}
	public BigDecimal getCurrency() {
		return currency;
	}
	public void setCurrency(BigDecimal currency) {
		this.currency = currency;
	}
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
	public long getAudioId() {
		return audioId;
	}
	public void setAudioId(long audioId) {
		this.audioId = audioId;
	}
	public String getCucrrencyName() {
		return cucrrencyName;
	}
	public void setCucrrencyName(String cucrrencyName) {
		this.cucrrencyName = cucrrencyName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CucrrencyRecord [id=" + id + ", userId=" + userId + ", audioId=" + audioId + ", cucrrencyName="
				+ cucrrencyName + ", createTime=" + createTime + ", currency=" + currency + ", accees=" + accees + "]";
	}

	
}
