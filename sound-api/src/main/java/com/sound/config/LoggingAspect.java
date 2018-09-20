package com.sound.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Arrays;

/**
 * 服务日志
 *
 * @author music
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Pointcut("within(com.sound.service.*) || within(com.sound.controller.*)")
    public void loggingPointcut() {
    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = \'{}\' and exception = \'{}\'", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL", e.getMessage(), e);
    }

    @Around("loggingPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            //计算时长
            long start = System.currentTimeMillis();

            Object result = joinPoint.proceed();
            //计算时长
            long last = System.currentTimeMillis() - start;
            //打印日志
            printLog(joinPoint, result, last);
            return result;
        } catch (Throwable t) {
            log.error("异常捕获: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            log.error("AOP异常捕获", t);
            throw t;
        }

    }

    private void printLog(ProceedingJoinPoint pjp, Object returnObject, long lastTime) {
        StringBuffer params = new StringBuffer();
        for (Object o : pjp.getArgs()) {
            if (o instanceof Principal) {
                params.append(((Principal) o).getName());
            } else {
                params.append(o + ", ");
            }
        }
        log.info("aop拦截方法: [类名]={}  [方法]={}  [持续时间]={} [参数]={} [返回]={}", pjp.getTarget().getClass().toString(), pjp.getSignature().getName(), lastTime, params.toString(), returnObject);
    }

}
