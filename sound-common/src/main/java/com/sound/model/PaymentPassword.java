package com.sound.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "PaymentPassword")
// 构建数据库表实体
@Table(name = "payment_password")
public class PaymentPassword implements Serializable {

	/**
	 * 支付密碼
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(columnDefinition = "bigint(20) default 0")
	private long userId;

	@Column(columnDefinition = "varchar(64) default ''")
	private String paymentPassword;

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

	public String getPaymentPassword() {
		return paymentPassword;
	}

	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}

	@Override
	public String toString() {
		return "PaymentPassword [id=" + id + ", userId=" + userId + ", paymentPassword=" + paymentPassword + "]";
	}
	
	

}
