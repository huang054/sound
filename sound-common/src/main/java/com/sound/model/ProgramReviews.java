package com.sound.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name ="programReviews")
@Table(name = "program_reviews")
public class ProgramReviews implements Serializable{

	/**
	 * 节目评论
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private long audioId;
	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private long userId;
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String context;
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date time;
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
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "ProgramReviews [id=" + id + ", audioId=" + audioId + ", userId=" + userId + ", context=" + context
				+ ", time=" + time + "]";
	}

	
	
}
