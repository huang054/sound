package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity(name="usermsg")
//用户消息表
@Table(name = "user_msg",
		uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "sysmsgId"})})
public class UserMsgModel {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(64) default ''")
	private String ticket;

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//插入时间

	@Column(columnDefinition="varchar(64) DEFAULT ''")
    private String userId;

	@Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long sysmsgId;

	@Column(columnDefinition="int(11) DEFAULT 0")
    private Integer status;//消息状态,"0"未读;"1"已读

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getSysmsgId() {
		return sysmsgId;
	}

	public void setSysmsgId(Long sysmsgId) {
		this.sysmsgId = sysmsgId;
	}

	@Override
	public String toString() {
		return "UserMsgModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", userId=" + userId
				+ ", sysmsgId=" + sysmsgId + ", status=" + status + "]";
	}
	
}
