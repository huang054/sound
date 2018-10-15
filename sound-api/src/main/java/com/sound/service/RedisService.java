package com.sound.service;

import com.alibaba.fastjson.JSON;
import com.sound.controller.BaseController;
import com.sound.model.UserModel;
import com.sound.utils.SerializeUtil;
import com.sound.vo.Discuss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/5/12.
 */
@Component
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

  /*  @Resource
   private RedisTemplate<String, List<Discuss>> redisTemplate;*/
    
    @Resource
    private RedisTemplate<String,String> redisTemplate;

   

    public final static String CODE = "_code";

    /**
     * redis中查询用户是否登陆
     * @param request
     * @return
     */
    public boolean isLogin(HttpServletRequest request){
        String key = BaseController.getCookieByName(request);
       if(null==key) {
    	   return false;
       }
        UserModel userModel = (UserModel) JSON.parseObject(stringRedisTemplate.opsForValue().get(key), UserModel.class);  
       // UserModel userModel = (UserModel) SerializeUtil.unserialize(redisTemplate.opsForValue().get(key));

        if(userModel == null){
            return false;
        }else{
            if(userModel.getId()<=0){
                return false;
            }else{
                return true;
            }
        }
    }

    /**
     * redis中获取用户
     * @param request
     * @return
     */
    public UserModel getUserInfo(HttpServletRequest request){
        String key = BaseController.getCookieByName(request);
       // System.out.println("keykeykeykeykeykeykeykeykeykeykeykeykeykeykeykeykeykeykeykeykey"+key);
       /* System.out.println(key);
        System.out.println(stringRedisTemplate.opsForValue().get(key));*/
        return (UserModel) JSON.parseObject(stringRedisTemplate.opsForValue().get(key), UserModel.class);
    }
    public UserModel getUser(String token){
        //String key = BaseController.getCookieByName(request);
       /* System.out.println(key);
        System.out.println(stringRedisTemplate.opsForValue().get(key));*/
        return (UserModel) JSON.parseObject(stringRedisTemplate.opsForValue().get(token), UserModel.class);
    }
    /**
     * 存储用户信息
     * @param ssoToken
     * @param user
     */
    
    public void saveUser(String ssoToken,UserModel user){
    	//System.out.println("------------------"+user.toString());
    	  // System.out.println(ssoToken);
    	//System.out.println(user.getPhoneNum());
    	//final byte[] vbytes = SerializeUtil.serialize(user);
    	String s=JSON.toJSONString(user);
    	//System.out.println(s);
    	//System.out.println(vbytes.toString());
        if(stringRedisTemplate.hasKey(user.getPhoneNum())){
            String reSsoToken = stringRedisTemplate.opsForValue().get(user.getPhoneNum());
           
           // System.out.println("token"+ssoToken);
           // System.out.println(reSsoToken);
           // if(!ssoToken.equals(reSsoToken)){
            	   //System.out.println(reSsoToken);
//                redisTemplate.opsForValue().set(ssoToken,user,1, TimeUnit.DAYS);
//                stringRedisTemplate.opsForValue().set(user.getUserId(),ssoToken,1, TimeUnit.DAYS);
            	stringRedisTemplate.opsForValue().set(ssoToken,s);
                stringRedisTemplate.opsForValue().set(user.getPhoneNum(),ssoToken);
           // }

        }else{
//            redisTemplate.opsForValue().set(ssoToken,user,1, TimeUnit.DAYS);
//            stringRedisTemplate.opsForValue().set(user.getUserId(),ssoToken,1, TimeUnit.DAYS);
        	stringRedisTemplate.opsForValue().set(ssoToken,s);
            stringRedisTemplate.opsForValue().set(user.getPhoneNum(),ssoToken);
        }
     // System.out.println(stringRedisTemplate.opsForValue().get(ssoToken));
    }

    public void refresh(HttpServletRequest request){
        String ssoToken = BaseController.getCookieByName(request);
        UserModel user =(UserModel) JSON.parseObject(stringRedisTemplate.opsForValue().get(ssoToken), UserModel.class);
        stringRedisTemplate.opsForValue().set(ssoToken,stringRedisTemplate.opsForValue().get(ssoToken));
        stringRedisTemplate.opsForValue().set(user.getPhoneNum(),ssoToken);
    }
    /**
     *移除用户信息
     */
    public void removeUser(String phone){
    	String ssoToken =stringRedisTemplate.opsForValue().get(phone);
    	//System.out.println("phone"+phone);
    	//System.out.println("token"+ssoToken);
    	if(stringRedisTemplate.hasKey(phone))
    	stringRedisTemplate.delete(phone);
    	if(null!=ssoToken) {
    		if(stringRedisTemplate.hasKey(ssoToken))
    	    	stringRedisTemplate.delete(ssoToken);
    	} 
    	
    }
    
/*    public void deleteDiscuss(String token) {
    	redisDiscussTemplate.delete(token);
    } 
    
    public List<Discuss> getDiscuss(String token){
    	return redisDiscussTemplate.opsForList().leftPop(token);
    }
    
    public void saveDiscuss(String token,List<Discuss> dis) {
    	redisDiscussTemplate.opsForList().leftPush(token, dis);
    	//stringRedisTemplate.expire(token,10*60 , TimeUnit.SECONDS);
    	
    }*/
    	
    public void saveCode(String code,String userId){
        //redisTemplate.opsForValue().set(code,null,10*60,TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(userId+CODE,code,10, TimeUnit.MINUTES);
    }

    public String getCode(String userId){
        //redisTemplate.opsForValue().set(code,null,10*60,TimeUnit.SECONDS);
    	String code=stringRedisTemplate.opsForValue().get(userId+CODE);
    	
        return code;
    }
    
    public void deleteCode(String userId){
        //redisTemplate.opsForValue().set(code,null,10*60,TimeUnit.SECONDS);
    	if(stringRedisTemplate.hasKey(userId))
    	stringRedisTemplate.delete(userId);
    	
       
    }

    public void loginout(String userId){
        stringRedisTemplate.delete(userId+CODE);
        String ssotoken = stringRedisTemplate.opsForValue().get(userId);
        stringRedisTemplate.delete(userId);
        stringRedisTemplate.delete(ssotoken);
    }
}
