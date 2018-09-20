package com.security;

import java.lang.annotation.*;

/**
 * Created with music IntelliJ IDEA
 * User:班纳
 * Date:2018/5/2 13:56
 */

/**
 * 添加此注解必须要带token
 *
 * @author music
 */
@Documented
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperateLog {

    boolean validate() default true;

    /**
     * 一级模块
     *
     * @return
     */
    String module();

    /**
     * 二级模块
     *
     * @return
     */
    String secondModule();

    /**
     * 内容
     *
     * @return
     */
    String content();
}
