package com.sound.config;

import com.alibaba.fastjson.JSON;
import com.sound.common.ParamCode;
import com.sound.common.Response;
import com.sound.controller.BaseController;
import com.sound.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
  
/** 
 * Created by Administrator on 2018/4/28.
 */ 
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Response<Boolean> resp = new Response<>();
        String ss = BaseController.getCookieByName(request);
        if(!redisService.isLogin(request)){
            resp.setRespone(ParamCode.NOLOGIN);
            returnJson(response,JSON.toJSONString(resp));
            return false;
        }
        redisService.refresh(request);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {

        } finally {
            if (writer != null) {
                writer.close();
            }

        }
    }

}
