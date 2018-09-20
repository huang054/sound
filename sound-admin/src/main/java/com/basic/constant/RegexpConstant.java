package com.basic.constant;

/**
 * Created by Administrator on 2017/5/24.
 */
public class RegexpConstant {

    /**
     * 手机号码
     */
    public static final String MOBILE_NUM = "^1\\d{10}$";

    /**
     * 数字4-6位
     */
    public static final String NUM_4_6 = "^\\d{4,6}$";

    /**
     * 数字或者字母，长度至少为1
     */
    public static final String NUM_CHAR_ATLEAST_ONCE = "^[A-Za-z0-9]+$";
}
