package com.sound.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class AjPushPojo {
	private String platform = "all";
	private String audience = "all";
	private String notification = "{}";
	private String message = "{\"msg_content\":\"sadsa\"}";
	private String options = "{\"time_to_live\": 60,\"apns_production\": true}";
 
	public String getPlatform() {
		return platform;
	}
 
	public void setPlatform(String... platform) {
		if (platform.length == 1) {
			this.platform = platform[0];
		} else {
			this.platform = JSON.toJSONString(platform);
		}
	}
 
	public String getAudience() {
		return audience;
	}
 
	public void setAlias(String... audience) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alias", (audience));
		this.audience =  JSON.toJSONString(map);
	}
 
	public void setRegistrationId(String... registrationId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("registrationId", (registrationId));
		this.audience =  JSON.toJSONString(map);
	}
	
	public void setAudience(String audience) {
		this.audience = audience;
	}
 
	public String getNotification() {
		return notification;
	}
 
	public void setNotification(String alert, Map<String, Object> extras) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map1.put("extras", extras);
		map1.put("alert", alert);
		map2.put("extras", extras);
		map2.put("alert", alert);
		map2.put("sound", "default");
		map2.put("content-available", true);
		map.put("android", map1);
		map.put("ios", (map2));
		this.notification =  JSON.toJSONString(map);
	}
 
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message, Map<String, Object> extras) {
		this.message = "{}";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("extras", extras);
		map.put("msg_content", message);
		map.put("content_type", "text");
		this.message =  JSON.toJSONString(map);
	}
 
	public String getOptions() {
		return options;
	}
 
	public void setOptions(String options) {
		this.options = options;
	}
 
}

