package com.sound.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

 
public class AJpushUtils {
	
	static Logger logger = LoggerFactory.getLogger(AJpushUtils.class);
	private static String MASTER_SECRET = "b7a0872d4dae4f15a1e6bd8a";
	private static String APP_KEY 	   = "f0bf058baf8f422f7c672dec";
	public  static JPushClient jpushClient  = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
	public  static Map<String,Boolean>isIOS = new ConcurrentHashMap<String,Boolean>();
	
	public static void push(String alias,String key,String JsonStringValue) {
		Boolean res = null;
		if(isIOS.containsKey(alias)) {
			if(isIOS.get(alias)) {
				res = pushIOSMsg(alias,key,JsonStringValue);
				if(res==Boolean.FALSE) {
					res = pushAndroidMsg(alias,key,JsonStringValue);
					if(res==Boolean.TRUE) {
						isIOS.put(alias, Boolean.FALSE);
					}
				}
				
			}else {
				res = pushAndroidMsg(alias,key,JsonStringValue);
				if(res==Boolean.FALSE) {
					res = pushIOSMsg(alias,key,JsonStringValue);
					if(res==Boolean.TRUE) {
						isIOS.put(alias, Boolean.TRUE);
					}
				}
			}
			
		}else {
			res = pushIOSMsg(alias,key,JsonStringValue);
			if(res==Boolean.TRUE) {
				isIOS.put(alias, Boolean.FALSE);
				
			}else {
				res = pushAndroidMsg(alias,key,JsonStringValue);
				if(res==Boolean.TRUE) {
					isIOS.put(alias, Boolean.FALSE);
				}
			}
			
		}
		
	}
	
	public static Boolean pushAndroidMsg(String alias,String key,String JsonStringValue) {
		logger.info(alias+"-pushAndroidMsg:", JsonStringValue);
		PushPayload pushPayload = PushPayload.newBuilder()
									.setPlatform(Platform.android_ios())
									.setAudience(Audience.alias(alias))
									.setMessage(Message.newBuilder()
									.setMsgContent("")
									.addExtra(key, JsonStringValue)
									.build())
								.build();
			try {
				jpushClient.sendPush(pushPayload);
				return Boolean.TRUE;
			} catch (APIConnectionException e) {
				// TODO Auto-generated catch block
				logger.error(" e: {}", e);
			} catch (APIRequestException e) {
				// TODO Auto-generated catch block
				logger.error(" e: {}", e);
			}
			return Boolean.FALSE;
	}
	
	public static Boolean pushIOSMsg(String alias,String key,String JsonStringValue) {
		logger.info(alias+"-pushIOSMsg:", JsonStringValue);
		PushPayload pushPayload =  PushPayload.newBuilder()
		        .setPlatform(Platform.all())
		        .setAudience(Audience.alias(alias)).setNotification(
		            (Notification.newBuilder()
		                    .addPlatformNotification(IosNotification.newBuilder()
		                           .setMutableContent(true)
		                           .setContentAvailable(true)
		                           .disableBadge()
		                           .disableSound()
		                    		   .setAlert("")
		                    		   .addExtra(key, JsonStringValue)
		                           .build())
		                    .build())
		         )
		        .build();
				try {
					jpushClient.sendPush(pushPayload);
					return Boolean.TRUE;
				} catch (APIConnectionException e) {
					// TODO Auto-generated catch block
					logger.error(" e: {}", e);
				} catch (APIRequestException e) {
					// TODO Auto-generated catch block
					logger.error(" e: {}", e);
				}
				return Boolean.FALSE;
	}
	
}
