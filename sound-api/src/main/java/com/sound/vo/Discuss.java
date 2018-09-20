package com.sound.vo;

import java.io.Serializable;

public class Discuss implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String url;
	
	private String name;
	
	private String contect;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContect() {
		return contect;
	}

	public void setContect(String contect) {
		this.contect = contect;
	}

	@Override
	public String toString() {
		return "Discuss [url=" + url + ", name=" + name + ", contect=" + contect + "]";
	}
	
	

}
