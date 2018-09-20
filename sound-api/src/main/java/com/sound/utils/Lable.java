package com.sound.utils;



public enum Lable {
	SPACERESIDENT("太空居民"),//("太空居民"),
	MERCURYRESIDENT("水星居民"),//("水星居民"),
	VENUSRESIDENT("金星居民"),//("金星居民"),
	EARTHRESIDENT("地球居民"),//("地球居民"),
	SPARKRESIDENT("火星居民"),//("火星居民"),
	JUPEITERRESIDENT("木星居民"),//("木星居民"),
	SATURNRESIDENT("土星居民"),//("土星居民"),
	URANUSRESIDENT("天王星居民"),//("天王星居民"),
	NEPTUNERESIDENT("海王星居民"),//("海王星居民");
	SUNRESIDENT("太阳居民");//("太阳居民");
	
	private String msg;
	
	private Lable(String msg) {
		this.msg= msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		return msg;
	}
}
