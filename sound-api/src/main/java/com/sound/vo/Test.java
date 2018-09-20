package com.sound.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Test implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Test [code=" + code + ", data=" + data + "]";
	}
	
	

}
