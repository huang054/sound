package com.sound.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name="currencyInfo") // 构建数据库表实体
@Table(name = "currency")
public class Currency implements Serializable{

	/**
	 * 用户听音频获取详细币种
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;//主键
	@Column(columnDefinition="bigint(20) default 0")
	private long audioId;//节目id
	@Column(columnDefinition="varchar(64) default ''")
	private String currencyName;//币种名字
	@Column(precision=12,scale=8)
	private BigDecimal currencyAccount;//币种总额
	@Column(columnDefinition="varchar(64) default ''")
	private String currencyPicUrl;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
   
	public long getAudioId() {
		return audioId;
	}
	public void setAudioId(long audioId) {
		this.audioId = audioId;
	}
	public String getCurrencyPicUrl() {
		return currencyPicUrl;
	}
	public void setCurrencyPicUrl(String currencyPicUrl) {
		this.currencyPicUrl = currencyPicUrl;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public BigDecimal getCurrencyAccount() {
		return currencyAccount;
	}
	public void setCurrencyAccount(BigDecimal currencyAccount) {
		this.currencyAccount = currencyAccount;
	}
	@Override
	public String toString() {
		return "Currency [id=" + id + ", audioId=" + audioId + ", currencyName=" + currencyName + ", currencyAccount="
				+ currencyAccount + ", currencyPicUrl=" + currencyPicUrl + "]";
	}
    
	
}
