package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity // 栏目表
@Table(name = "comment")
public class CommentModel {
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 64)
	private String ticket;

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间
	
	@Column(columnDefinition="text not null")
	private String content;//评论内容
	
	@Column(columnDefinition="varchar(32) default ''")
	private String userId;//

	@Column(columnDefinition="varchar(32) default ''")
	private String name;//用户昵称

	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private Long newsId;//资讯id
	
	@Column(columnDefinition="varchar(255) default ''")
	private String headerUrl;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

	@Override
	public String toString() {
		return "CommentModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", content=" + content
				+ ", userId=" + userId + ", name=" + name + ", newsId=" + newsId + ", headerUrl=" + headerUrl + "]";
	}
	
	
    
}
