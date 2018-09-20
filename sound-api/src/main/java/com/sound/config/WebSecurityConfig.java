package com.sound.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebSecurityConfig extends WebMvcConfigurationSupport {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
        registry.addResourceHandler("/assets/**")
        .addResourceLocations("classpath:/META-INF/spring-boot-admin-server-ui/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**")
				.addResourceLocations("classpath:/static/");

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //用户角色拦截器
        registry.addInterceptor(loginInterceptor).
                addPathPatterns("/**")
                .excludePathPatterns("/user/**",
                        "/swagger-ui.html","/webjars/**","/v2/**",
                        "/swagger-resources/**","/swagger-resources",
                        "/sysRecommend/**","/**");
    }


}