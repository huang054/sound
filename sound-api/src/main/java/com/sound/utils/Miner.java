package com.sound.utils;

public enum Miner {
   PRIMARY("初级矿工"),
   MIDDEL("中级矿工"),
   HIGHT("高级矿工"),
   SUPERFINE("特级矿工");
	
	private String msg;
	
	private Miner(String msg) {
		this.msg=msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
