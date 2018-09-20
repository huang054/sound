package com.sound.vo;

import java.io.Serializable;
import java.util.Date;

public class AudioCollection implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long audioId;
	
	private String title;
	
	private String context;
	
	private String picUrl;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "AudioCollection [id=" + id + ", audioId=" + audioId + ", title=" + title + ", context=" + context
				+ ", picUrl=" + picUrl + ", time=" + time + "]";
	}
	
	

}
