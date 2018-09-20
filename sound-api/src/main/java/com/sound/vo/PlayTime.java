package com.sound.vo;

import java.io.Serializable;

public class PlayTime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String audioId;
	
	private String time;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAudioId() {
		return audioId;
	}

	public void setAudioId(String audioId) {
		this.audioId = audioId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "PlayTime [userId=" + userId + ", audioId=" + audioId + ", time=" + time + "]";
	}
	
	
	
	

}
