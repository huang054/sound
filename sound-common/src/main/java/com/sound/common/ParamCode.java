package com.sound.common;

import com.alibaba.fastjson.JSON;

public enum ParamCode{
	
	 SUCSESS("000000","Operation succeeded"),
	
	 FAIL("000100","Operation failed"),
	 
	 PASSWORDFAIL("000300","Password failed"),
	
	 PARAMERROR("000200","Param error"),
	
	 NOLOGIN("000201","You have no login"),

	 CODE_IS_ABLE("000500","验证码有效期中，请10分钟后再发");
	 
	 private String code;
	
	 private String msg;
	 
	 private ParamCode(String code, String msg) {
		 this.code = code;
		 this.msg = msg;
	 }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
	 
}
