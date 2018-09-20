package com.sound.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name = "dailyTask")
@Table(name = "daily_task")
public class DailyTask implements Serializable{
	
	/**
	 *日常任务 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="bigint(20) default 0")
	private long userId;
	@Column(columnDefinition="bigint(20) default 0")
	private long audioNumber;
	@Column(columnDefinition="bigint(20) default 0")
	private long audioTime;
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean isSharingAudio;//是否分享朋友圈
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean isBeyouVoice;//是否听了BEYOU VOICE 专属音频

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

	public long getAudioNumber() {
		return audioNumber;
	}

	public void setAudioNumber(long audioNumber) {
		this.audioNumber = audioNumber;
	}

	public long getAudioTime() {
		return audioTime;
	}

	public void setAudioTime(long audioTime) {
		this.audioTime = audioTime;
	}

	public boolean isSharingAudio() {
		return isSharingAudio;
	}

	public void setSharingAudio(boolean isSharingAudio) {
		this.isSharingAudio = isSharingAudio;
	}

	public boolean isBeyouVoice() {
		return isBeyouVoice;
	}

	public void setBeyouVoice(boolean isBeyouVoice) {
		this.isBeyouVoice = isBeyouVoice;
	}
	
	

}
