package com.sound.common;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/4/25.
 * 上传文件到七牛云
 */
@Component
public class UpfileHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpfileHandler.class);

    @Value("${file.local.cache}")
    private String locatPath;

    public String getLocatPath() {
        return locatPath;
    }

    public void setLocatPath(String locatPath) {
        this.locatPath = locatPath;
    }

    public static String getLoadownFilePath(String path){
        String locatpath = new UpfileHandler().getLocatPath();
        return locatpath+File.pathSeparator+path;
    }
    public static String saveFileLocal(File file){
        String path = new UpfileHandler().getLocatPath();
        if(file==null||!file.exists()){
            return "";
        }
        File filepath = new File(path);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        String md5 = "";
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            md5 = DigestUtils.md5DigestAsHex(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("get file md5 failed:",e);
            return "";
        }finally {
            if(in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        File savefile = new File(path+File.pathSeparator+md5);
        return md5;

    }

    /**基本配置-从七牛管理后台拿到*/
    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "AK";
    String SECRET_KEY = "SK";
    //要上传的空间名--
    String bucketname = "空间名";



    /**指定保存到七牛的文件名--同名上传会报错  {"error":"file exists"}*/
    /** {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"} 正常返回 key为七牛空间地址 http:/xxxx.com/aa1.jpg */
    //上传到七牛后保存的文件名    访问为：http://oswj11a86.bkt.clouddn.com/daimo6.png
    String key = "daimo6.png";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager =new UploadManager(new Configuration());


    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }

    public String upload(File file) throws IOException {
        if(file==null||!file.exists()){
            return null;
        }
        try {
            //调用put方法上传
//            String FilePath = file.getAbsoluteFile();
            Response res =uploadManager.put(file,key, getUpToken());
//            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            return res.bodyString();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }
}
