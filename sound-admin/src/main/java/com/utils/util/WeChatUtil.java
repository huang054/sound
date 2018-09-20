package com.utils.util;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 微信公众号
 * Created by 弗拉基米尔 on 2017/8/29.
 */
public class WeChatUtil {

    //小程序唯一标识   (在微信小程序管理后台获取)
    public static final String appid = "wx3a0aff9873a2fee4";

    public static final String mch_id = "1487025612";

    //授权（必填）
    public static final String grant_type = "authorization_code";

    //小程序的 app secret (在微信小程序管理后台获取)
    public static final String appSecret = "f54977e432474ad198fe492c8b167e4b";

    private WeChatUtil() {
    }

    /**
     * 获取opneID
     *
     * @param code
     * @return
     */
    public static JSONObject getOpenId(String code) {
        JSONObject json = new JSONObject();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            json.put("result", 1);
            return json;
        }

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 openid ////////////////
        //请求参数
        String params = "appid=" + appid + "&secret=" + appSecret + "&code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        //解析相应内容（转换成json对象）
        JSONObject result = JSONObject.parseObject(sr);
        result.put("result", 0);//成功获取微信ID
        return result;
    }

    /**
     * 获取用户基本信息
     *
     * @param json
     */
    public static JSONObject getUserInfo(JSONObject json) {
        JSONObject result = null;
        try {
            //请求参数
            String params = "access_token=" + json.get("access_token") + "&openid=" + json.get("opneid") + "&lang=zh_CN ";
            //发送请求
            String sr = sendGet("https://api.weixin.qq.com/sns/userinfo", params);
            //解析相应内容（转换成json对象）
            result = JSONObject.parseObject(sr);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            // Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            // for (String key : map.keySet()) {
            //System.out.println(key + "--->" + map.get(key));
            // }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
