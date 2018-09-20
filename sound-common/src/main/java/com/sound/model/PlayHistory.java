package com.sound.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name="PlayHistoryInfo") // 构建数据库表实体
@Table(name = "play_history")
public class PlayHistory implements Serializable{

	/**
	 * 用户的播放历史
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
	@Column(columnDefinition="varchar(64) default ''")
	private String audioName;
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
	public String getAudioName() {
		return audioName;
	}
	public void setAudioName(String audioName) {
		this.audioName = audioName;
	}
	@Override
	public String toString() {
		return "PlayHistory [id=" + id + ", userId=" + userId + ", audioId=" + audioId + ", audioName=" + audioName
				+ "]";
	}
    
	
}
