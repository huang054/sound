package com.sound.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/6/7.
 */
public class CheckUtils {

    public static boolean checkPwd(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,18}$");
        Matcher m = pattern.matcher(input);
        if( !m.matches() ){
            return false;
        }
        return true;
    }

    public static boolean checkName(String input){

        Pattern pattern = Pattern.compile("^[a-zA-Z\u4E00-\u9FA5]{2,10}$");
        Matcher m = pattern.matcher(input);
        if( !m.matches() ){ //匹配不到,說明輸入的不符合條件
            return false;
        }
        return true;
    }

    public static boolean checkCellphone(String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0,1,2,5-9])|(177))\\d{8}$";
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(cellphone);
        return matcher.matches();
    }
    
    public static boolean checkString(String s) {
    	if(null==s||s.trim().equals(""))
    		return false;
    	return true;
    }
}
