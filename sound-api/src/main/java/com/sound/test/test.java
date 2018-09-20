package com.sound.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/6/7.
 */
public class test {

    public static  void main(String[] vags){

        System.out.println(""+checkName("你好啊滚"));

    }

    public static boolean checkInput(String input){
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]{6,20}$");
        Matcher m = pattern.matcher(input);
        if( !m.matches() ){ //匹配不到,說明輸入的不符合條件
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
}
