package com.sound.utils;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sound.config.WebChinese;
import com.sound.controller.UserController;
import com.sound.model.CurrencyWater;
import com.sound.model.UserTemporary;
import com.sound.service.CurrencyWaterService;
import com.sound.service.DailyTaskService;
import com.sound.service.UserService;
import com.sound.service.UserTemporaryService;
 
@Configuration
@EnableScheduling
public class SignInTask {
	private static final Logger logger = LoggerFactory.getLogger(SignInTask.class);
	/** 
	 * 定时器 每天晚上12点更改签到状态
	 */
	@Autowired
	private UserService userService;
	@Autowired
	public DailyTaskService dailyTaskService;
	@Autowired
    private UserTemporaryService userTemporaryService;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${postUrl}")
    private String postUrl;
	@Value("${postUrl1}")
    private String postUrl1;
	@Autowired
	private CurrencyWaterService currencyWaterService;
	@Autowired
	private WebChinese webChinese;
	@Scheduled(cron = "0 0 0 * * ?")
	public void signIn() {
		logger.info("0点的定时任务");
		userService.updateYestodaySignIn();
		userService.updateSignIn();
		dailyTaskService.updateDailyTask(false);
	}

	@Scheduled(cron = "0 0/15 * * * ?")
	public void user() {
        Iterable<UserTemporary> users= userTemporaryService.findAll();
        Iterator iter = users.iterator();
        while(iter.hasNext()) {
        	 MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        	UserTemporary user=(UserTemporary) iter.next();
        	paramMap.add("mobileNumber", user.getPhone());
			paramMap.add("loginPword", user.getPassword());
        	String body = restTemplate.postForObject(postUrl,
					paramMap,String.class );
        	 JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
				//String code=JSON.parseObject(body).getJSONObject("code").toString();
				  String code=jsonObject.get("code").getAsString();
				
				  if(code.equals("0")) {
					  webChinese.sendMsg1(user.getPhone(),user.getPassword());
					  userTemporaryService.delete(user);
				  }
        }
	}
	
	@Scheduled(cron = "0 0/20 * * * ?")
	public void currency() {
        Iterable<CurrencyWater>  currencyWaters= currencyWaterService.findAll();
        Iterator iter = currencyWaters.iterator();
        while(iter.hasNext()) {
        	 MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        	 CurrencyWater currencyWater=(CurrencyWater) iter.next();
       	 paramMap.add("phone", currencyWater.getPhone());
		 paramMap.add("coinType", currencyWater.getCucrrencyName());
		 paramMap.add("value", currencyWater.getCurrency());
        	String body = restTemplate.postForObject(postUrl1,
					paramMap,String.class );
        	 JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
				//String code=JSON.parseObject(body).getJSONObject("code").toString();
				  String code=jsonObject.get("code").getAsString();
				
				  if(code.equals("0")) {
					 
					  currencyWaterService.delete(currencyWater);
				  }
        }
	}

}
