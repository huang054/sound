package  com.sound.config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.dao.DeviceDAO;
import com.sound.model.AudioModel;
import com.sound.model.CucrrencyRecord;
import com.sound.model.Currency;
import com.sound.model.CurrencyWater;
import com.sound.model.DailyTask;
import com.sound.model.PaymentsRecords;
import com.sound.model.PlayRecord;
import com.sound.model.UserModel;
import com.sound.model.UserTemporary;
import com.sound.service.AudioService;
import com.sound.service.CucrrencyRecordService;
import com.sound.service.CurrencyService;
import com.sound.service.CurrencyWaterService;
import com.sound.service.DailyTaskService;
import com.sound.service.PaymentsRecordsService;
import com.sound.service.PlayRecordService;
import com.sound.service.RedisService;
import com.sound.service.UserService;
import com.sound.vo.PlayMessage;
import com.sound.vo.PlayTime;
    
/**
 * 定义消费者
 * 
 * @author song
 *
 */
@Component 
public class Consumer {
	  
    @Autowired
    public DailyTaskService dailyTaskService;
	@Autowired
	private AudioService audioService;
	@Autowired
	private CurrencyService currencyService;
	@Autowired
	private PlayRecordService playService;
    @Resource
    private RedisService redisService;
	@Autowired
	private UserService userService;
	@Autowired
    private CucrrencyRecordService cucrrencyRecordService;
	@Autowired
	private PaymentsRecordsService payService;
	@Autowired
	private CurrencyWaterService currencyWaterService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DeviceDAO deviceDAO;
	@Value("${postUrl1}")
    private String postUrl;
	/*@JmsListener(destination = "sample.queue")
	public void receiveQueue(String playMessage) {

		System.out.println("消费者：来源于生产者的消息：" + playMessage);
		PlayMessage play = (PlayMessage) JSON.parseObject(playMessage, PlayMessage.class); 
		PaymentsRecords pay = new PaymentsRecords();
		pay.setPayments(new BigDecimal("10"));
		pay.setCurrencyName(play.getBitName());
		pay.setUserId(userService.findByUserName(play.getPhone()).getId());
		payService.save(pay);
		System.out.println(play.toString());
	}*/

	@JmsListener(destination = "playTime.queue")
	public void receiveQueue1(String playMessage) {
	
		PlayTime playT = (PlayTime) JSON.parseObject(playMessage, PlayTime.class); 
		//Response res = new Response();
		String userPhoneNum=deviceDAO.findDeviceById(playT.getUserId()).getUserId();
		String audioId=playT.getAudioId();
		String playTime=String.valueOf(Integer.parseInt(playT.getTime())/1000);
		UserModel user = userService.findById(Long.parseLong(userPhoneNum));
	    AudioModel audio = audioService.findById(Long.parseLong(audioId));	
	  
	    PlayRecord playRecord=playService.find(user.getId(), Long.parseLong(audioId));
	    List<Currency> currency= currencyService.findCurrencyByAudioId(Long.parseLong(audioId));
	  
	    if(null==currency) {
	    	return;
	    }
	   
	    if(null==playRecord) {
	    	if(audio.getAccess()==1) {
	    	
	    	//System.out.println("-------------------------------------");
	    	for(Currency c :currency) {
	    		CucrrencyRecord cu = new CucrrencyRecord();
	    		cu.setAudioId(Long.parseLong(audioId));
	    		cu.setUserId(user.getId());
	    		cu.setCucrrencyName(c.getCurrencyName());
	    		cu.setAccees("听音频");
	    		cu.setCurrency(new BigDecimal(playTime).divide(new BigDecimal(audio.getDurantionSec()), 8, RoundingMode.HALF_UP).multiply(c.getCurrencyAccount()).setScale(8,BigDecimal.ROUND_HALF_UP));
	    		cucrrencyRecordService.save(cu);
	    		
	    		 MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
	    		 paramMap.add("phone", userPhoneNum);
	    		 paramMap.add("coinType", cu.getCucrrencyName());
	    		 paramMap.add("value", cu.getCurrency());
	    		 String body = restTemplate.postForObject(postUrl,
							paramMap,String.class );
	    		 JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
					//String code=JSON.parseObject(body).getJSONObject("code").toString();
					  String code=jsonObject.get("code").getAsString();
					  if(!code.equals("0")) {
						  CurrencyWater curr = new CurrencyWater();
						  curr.setPhone(userPhoneNum);
						  curr.setCucrrencyName(cu.getCucrrencyName());
						  curr.setCurrency(cu.getCurrency());
						  curr.setUserId(user.getId());
						  currencyWaterService.save(curr);
						}
	    		 
	    	}
	    	PlayRecord play = new PlayRecord();
	    	play.setAudioId(Long.parseLong(audioId));
	    	play.setAudioPlayTime(Long.parseLong(playTime));
	    	play.setUserId(user.getId());
	    	playService.save(play);
	    	}
	    	return;
	    /*	res.setData(currency);
	    	res.setMsg("获取币成功");
	    	res.setRespone(ParamCode.SUCSESS);*/
	    	//return res;
	    }
	   if(Long.parseLong(playTime)==audio.getDurantionSec()) {
	    	if(audio.getAccess()==1) {
	    
	    	for(Currency c :currency) {
	    		CucrrencyRecord cu = new CucrrencyRecord();
	    		cu.setAudioId(Long.parseLong(audioId));
	    		cu.setUserId(user.getId());
	    		cu.setCucrrencyName(c.getCurrencyName());
	    		cu.setAccees("听音频");
	    		cu.setCurrency(c.getCurrencyAccount());
	    		cucrrencyRecordService.save(cu);
	    		 MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
	    		 paramMap.add("phone", userPhoneNum);
	    		 paramMap.add("coinType", cu.getCucrrencyName());
	    		 paramMap.add("value", cu.getCurrency());
	    		 String body = restTemplate.postForObject(postUrl,
							paramMap,String.class );
	    		 JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
					//String code=JSON.parseObject(body).getJSONObject("code").toString();
					  String code=jsonObject.get("code").getAsString();
					  if(!code.equals("0")) {
						  CurrencyWater curr = new CurrencyWater();
						  curr.setPhone(userPhoneNum);
						  curr.setCucrrencyName(cu.getCucrrencyName());
						  curr.setCurrency(cu.getCurrency());
						  curr.setUserId(user.getId());
						  currencyWaterService.save(curr);
						}
	    	}
	    	playRecord.setAudioPlayTime(Long.parseLong(playTime));
	    	playService.save(playRecord);
	    	}
	    	//res.setData(currency);
	    	//res.setMsg("获取币成功");
	    	//res.setRespone(ParamCode.SUCSESS);
	    	//return res;
	    	return;
	    }
	    if(Long.parseLong(playTime)-playRecord.getAudioPlayTime()>0) {
	    	if(audio.getAccess()==1) {
	     	if(playRecord.getAudioPlayTime()<Long.parseLong(playTime)) {
	     		
	     		for(Currency c :currency) {
	     			CucrrencyRecord cu = new CucrrencyRecord();
		    		cu.setAudioId(Long.parseLong(audioId));
		    		cu.setUserId(user.getId());
		    		cu.setAccees("听音频");
		    		cu.setCucrrencyName(c.getCurrencyName());
		    		//c.setCurrencyAccount(new BigDecimal(playTime).subtract(new BigDecimal(playRecord.getAudioPlayTime()).divide(new BigDecimal(audio.getDurantionSec()), 8, RoundingMode.HALF_UP)).multiply(c.getCurrencyAccount()).setScale(8));
		     		cu.setCurrency((new BigDecimal(playTime).subtract(new BigDecimal(playRecord.getAudioPlayTime())).divide(new BigDecimal(audio.getDurantionSec()), 8, RoundingMode.HALF_UP)).multiply(c.getCurrencyAccount()).setScale(8,BigDecimal.ROUND_HALF_UP));
		     	
		    		cucrrencyRecordService.save(cu);
		    		MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
		    		 paramMap.add("phone", userPhoneNum);
		    		 paramMap.add("coinType", cu.getCucrrencyName());
		    		 paramMap.add("value", cu.getCurrency());
		    		 String body = restTemplate.postForObject(postUrl,
								paramMap,String.class );
		    		 JsonObject jsonObject = (JsonObject) new JsonParser().parse(body);
						//String code=JSON.parseObject(body).getJSONObject("code").toString();
						  String code=jsonObject.get("code").getAsString();
						  if(!code.equals("0")) {
							  CurrencyWater curr = new CurrencyWater();
							  curr.setPhone(userPhoneNum);
							  curr.setCucrrencyName(cu.getCucrrencyName());
							  curr.setCurrency(cu.getCurrency());
							  curr.setUserId(user.getId());
							  currencyWaterService.save(curr);
							}
	     			}
	     		playRecord.setAudioPlayTime(Long.parseLong(playTime));
	     		playService.save(playRecord);
	     /*		res.setData(currency);
		    	res.setMsg("获取币成功");
		    	res.setRespone(ParamCode.SUCSESS);*/
		    	//return res;
	     	}
	    
	    }
	    }
	    return;
		//return res;
	}
	
	@JmsListener(destination = "play.queue")
	public void receiveQueue2(String playMessage) {
		System.out.println( "play.queue");
		PlayTime playT = (PlayTime) JSON.parseObject(playMessage, PlayTime.class); 
		Response res = new Response();
		String userPhoneNum=deviceDAO.findDeviceById(playT.getUserId()).getUserId();
		String audioId=playT.getAudioId();
		String playTime=String.valueOf(Integer.parseInt(playT.getTime())/1000);
		UserModel user = userService.findById(Long.parseLong(userPhoneNum));
		 DailyTask daily = dailyTaskService.findDailyTaskByUserId(user.getId());
		 AudioModel audio = audioService.findById(Long.parseLong(audioId));
		if(null!=audioId&&!audioId.trim().equals("")) {
	   
	    //AudioModel audio = audioService.findById(Long.parseLong(audioId));	
	   
	    if(daily.isBeyouVoice()==false) {
	    	if(audio.getIsRoadShow()!=2) {
	    	daily.setBeyouVoice(true);
	    	user.setCalculatingForceValue(user.getCalculatingForceValue().add(new BigDecimal(10)));
	    	userService.save(user);
	    	dailyTaskService.save(daily);
	    	redisService.deleteCode(user.getUserToken());
	    	redisService.saveUser(user.getUserToken(),user);
	    	}
	    }
	    if(daily.getAudioNumber()<2) {
	    	daily.setAudioNumber(daily.getAudioNumber()+1);
	    	if(daily.getAudioNumber()>=2) {
	    		user.setCalculatingForceValue(user.getCalculatingForceValue().add(new BigDecimal(5)));
		    	userService.save(user);
		    	dailyTaskService.save(daily);
		      	redisService.deleteCode(user.getUserToken());
		    	redisService.saveUser(user.getUserToken(),user);
	    	}else {
	    		dailyTaskService.save(daily);
	    	}
	    }
	    
		}
		if(daily.getAudioTime()<45*60) {
			
			daily.setAudioTime(daily.getAudioTime()+Long.parseLong(playTime));
			if(daily.getAudioTime()>=45*60) {
				user.setCalculatingForceValue(user.getCalculatingForceValue().add(new BigDecimal(10)));
		    	userService.save(user);
		    	dailyTaskService.save(daily);
		      	redisService.deleteCode(user.getUserToken());
		    	redisService.saveUser(user.getUserToken(),user);
			}else {
	    		dailyTaskService.save(daily);
	    	}
		}
		//return res;
	}
/*	@JmsListener(destination = "sample.topic",containerFactory="topicListenerContainer")
	public void receiveSub1(String text) {
		System.out.println("消费者：Consumer1=" + text);
	}

	@JmsListener(destination = "sample.topic",containerFactory="topicListenerContainer")
	public void receiveSub2(final TextMessage text, Session session) throws JMSException {
		
		try {
			System.out.println("消费者：Consumer2=" + text.getText());
			//text.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//session.recover();// 此不可省略 重发信息使用
		}
	}*/
	

}