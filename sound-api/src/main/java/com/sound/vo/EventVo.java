package com.sound.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;

public class EventVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private List<String> imgs;

	
	public List<String> getImgs() {
		return imgs;
	}

	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}

	@Override
	public String toString() {
		return "EventVo [imgs=" + imgs + "]";
	}


	
	
}
