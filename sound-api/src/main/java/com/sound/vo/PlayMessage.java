package com.sound.vo;

import java.io.Serializable;

public class PlayMessage implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String Phone;
	
	private String bitName;

    

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getBitName() {
		return bitName;
	}

	public void setBitName(String bitName) {
		this.bitName = bitName;
	}

	@Override
	public String toString() {
		return "PlayMessage [Phone=" + Phone + ", bitName=" + bitName + "]";
	}


	

}
