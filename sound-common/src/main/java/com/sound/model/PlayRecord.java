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

@Entity(name = "playRecord")
@Table(name = "play_record")
public class PlayRecord implements Serializable{
	
	/**
	 * 播放音频记录包括时长
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
	@Column(columnDefinition="bigint(20) default 0")
	private long audioPlayTime;
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

	public long getAudioId() {
		return audioId;
	}

	public void setAudioId(long audioId) {
		this.audioId = audioId;
	}

	public long getAudioPlayTime() {
		return audioPlayTime;
	}

	public void setAudioPlayTime(long audioPlayTime) {
		this.audioPlayTime = audioPlayTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "PlayRecord [id=" + id + ", userId=" + userId + ", audioId=" + audioId + ", audioPlayTime="
				+ audioPlayTime + ", createTime=" + createTime + "]";
	}
	
	

}
