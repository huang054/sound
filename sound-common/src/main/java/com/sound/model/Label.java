package com.sound.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;


@Entity(name = "Label")
// 构建数据库表实体
@Table(name = "label")
public class Label implements Serializable{

	
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
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String labelName;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Label [id=" + id + ", userId=" + userId + ", labelName=" + labelName + "]";
	}
	
	
}
