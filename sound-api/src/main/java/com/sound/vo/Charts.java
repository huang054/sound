package com.sound.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Charts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userLevel;
	
	private String name;
	
	private BigDecimal payments;
   
	private String url;
	
	
    	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPayments() {
		return payments;
	}

	public void setPayments(BigDecimal payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Charts [userLevel=" + userLevel + ", name=" + name + ", payments=" + payments + "]";
	}
	
	

}
