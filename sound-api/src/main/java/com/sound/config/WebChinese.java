package com.sound.config;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Administrator on 2018/5/1.
 */
@Component
public class WebChinese {

    @Value("${dxd.username}")
    private String username;//用户名

    @Value("${dxd.secKey}")
    private String secKey;//安全密钥

//    public void main(String[] avgs){
//        sendMsg("13612932070","8888");
//    }
    /**
     * 发送手机验证码
     * @param iphone
     * @param code
     * @return
     */
    public boolean sendMsg(String iphone,String code){

        String content = "验证码："+code+" ,用于小链FM用户登录。如非本人操作，请忽略本短信。有效期：10分钟";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", username),new NameValuePair("Key", secKey),new NameValuePair("smsMob",iphone),new NameValuePair("smsText",content)};
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode+"msg:"+ getErrorMsg(statusCode));
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result); //打印返回消息状态

        post.releaseConnection();
        if(statusCode==200){
            return true;
        }else{
            return false;
        }

    }

    public boolean sendMsg1(String iphone,String pwd){

        String content = "欢迎您来到链星球！交易所上线后，您可用注册手机号和初始密码"+pwd+"登录我们的交易所将听音频的币进行交易，请妥善保管您的初始密码";
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
        NameValuePair[] data ={ new NameValuePair("Uid", username),new NameValuePair("Key", secKey),new NameValuePair("smsMob",iphone),new NameValuePair("smsText",content)};
        post.setRequestBody(data);

        try {
            client.executeMethod(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Header[] headers = post.getResponseHeaders();
        int statusCode = post.getStatusCode();
        System.out.println("statusCode:"+statusCode+"msg:"+ getErrorMsg(statusCode));
        for(Header h : headers)
        {
            System.out.println(h.toString());
        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("gbk"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result); //打印返回消息状态

        post.releaseConnection();
        if(statusCode==200){
            return true;
        }else{
            return false;
        }

    }
    public String getErrorMsg(int errorCode){
        if(errorCode==-1){
            return "没有该用户账户";
        }else if(errorCode==-2){
            return "接口密钥不正确";
        }else if(errorCode==-3){
            return "短信数量不足";
        }else if(errorCode==-4){
            return "手机号格式不正确";
        }else if(errorCode==-21){
            return "MD5接口密钥加密不正确";
        }else if(errorCode==-11){
            return "该用户被禁用";
        }else if(errorCode==-14){
            return "短信内容出现非法字符";
        }else if(errorCode==-41){
            return "手机号码为空";
        }else if(errorCode==-42){
            return "短信内容为空";
        }else if(errorCode==-51){
            return "短信签名格式不正确";
        }else if(errorCode==-6){
            return "IP限制";
        }else{
            return "未知错误码:"+errorCode;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }
}
