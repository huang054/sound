package com.sound.vo;

import java.io.Serializable;
import java.util.Date;

public class AlbumCollection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private long albumId;
	
	private String title;
	
	private String context;
	
	private String picUrl;
	
	private Date time;

	private int count;
	
	private boolean isBigVAlbum;
	
	
	


	public boolean isBigVAlbum() {
		return isBigVAlbum;
	}

	public void setBigVAlbum(boolean isBigVAlbum) {
		this.isBigVAlbum = isBigVAlbum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
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
		return "AlbumCollection [id=" + id + ", albumId=" + albumId + ", title=" + title + ", context=" + context
				+ ", picUrl=" + picUrl + ", time=" + time + ", count=" + count + ", isBigVAlbum=" + isBigVAlbum + "]";
	}
	
	

}
