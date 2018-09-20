package com.sound.utils;

public enum Sign {
	ARIES("牧羊座"),
	TAURUS("金牛座"),
	GEMINI("双子座"),
	CANCER("巨蟹座"),
	LEO("狮子座"),
	VIRGO("处女座"),
	LIBRA("天平座"),
	SCORPIUS("天蝎座"),
	SAGITTARIUS("射手座"),
	CAPRICORN("摩羯座"),
	AQUARIUS("水瓶座"),
	PISCES("双鱼座");
	
	private String msg;
	
	private Sign(String msg) {
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
