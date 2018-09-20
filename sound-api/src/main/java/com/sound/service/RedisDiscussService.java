package com.sound.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.sound.vo.Discuss;
@Component
public class RedisDiscussService {
	
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    
    @Resource
    private RedisTemplate<String, List<Discuss>> redisTemplate;
    
    public void deleteDiscuss(String token) {
    	redisTemplate.delete(token);
    }
    
    public List<Discuss> getDiscuss(String token){
    	return redisTemplate.opsForList().rightPopAndLeftPush(token, token);
    	//return redisTemplate.opsForList().leftPop(token);
    }
    
    public void saveDiscuss(String token,List<Discuss> dis) {
    	redisTemplate.opsForList().leftPush(token, dis);
    	stringRedisTemplate.expire(token,10*60 , TimeUnit.SECONDS);
    	
    }
}
