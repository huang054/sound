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
@Entity(name="PaymentsRecordsInfo") // 构建数据库表实体
@Table(name = "payments_records")
public class PaymentsRecords implements Serializable{

	/**
	 * 用户获取币种的记录
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="bigint(20) default 0")
	private long userId;
	@Column(columnDefinition="varchar(64) default ''")
	private String currencyName;//币种名字
	 @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;
	@Column(precision=12,scale=8)
	private BigDecimal payments;//获取的数量
	
	

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

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getPayments() {
		return payments;
	}

	public void setPayments(BigDecimal payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "PaymentsRecords [id=" + id + ", userId=" + userId + ", currencyName=" + currencyName + ", createTime="
				+ createTime + ", payments=" + payments + "]";
	}
	
	

}
