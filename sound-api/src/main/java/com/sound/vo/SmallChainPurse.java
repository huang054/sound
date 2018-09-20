package com.sound.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class SmallChainPurse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String logoUrl;
	
	private String symbol;
	
	private String balance;
	private String rate;
	private String total;
	
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
    
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Override
	public String toString() {
		return "SmallChainPurse [logoUrl=" + logoUrl + ", symbol=" + symbol + ", balance=" + balance + ", rate=" + rate
				+ ", total=" + total + "]";
	}

	
	
	
	

}
