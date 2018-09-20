package com.security;

import java.lang.annotation.*;

/**
 * Created with music IntelliJ IDEA
 * User:班纳
 * Date:2018/5/2 16:29
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WhetherOperateLog {

    boolean validate() default true;
}
