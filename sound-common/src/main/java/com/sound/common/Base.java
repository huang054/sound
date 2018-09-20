package com.sound.common;

import com.alibaba.fastjson.JSON;

public class Base {
	
	public String toSting() {return JSON.toJSONString(this);}
	
}
