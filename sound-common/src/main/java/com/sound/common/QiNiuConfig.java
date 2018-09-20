package com.sound.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by Administrator on 2018/5/1.
 */
@Component
public class QiNiuConfig {

    private static final Logger logger = LoggerFactory.getLogger(QiNiuConfig.class);

    public final static String BUCKET_SOUND="sound";//音频存储

    public final static String BUCKET_IMG="imgs";//图片存储


    @Value("${qiniu.accesskey}")
    private  String accessKey;

    @Value("${qiniu.secretkey}")
    private  String secretKey;

    @Value("${qiniu.sound.download}")
    private  String soundDownload;

    @Value("${qiniu.imgs.download}")
    private  String imgsDsownload;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }



    /**
     * 获取下载地址
     * @param key 文件对应的key
     * @param filename 文件名
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getDownload(String key,String filename,String buket){
        String download = "";
        try {
            if(BUCKET_SOUND.equals(buket))
                download = soundDownload;
            if(BUCKET_IMG.equals(buket))
                download = soundDownload;
            return String.format(download+'/'+key+"?attname=%s", URLEncoder.encode(filename, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String openDownloadUrl(String key,String buket){
        String download ="";
        if(BUCKET_SOUND.equals(buket))
            download = soundDownload;
        if(BUCKET_IMG.equals(buket))
            download = soundDownload;
        return download+'/'+key+'?';
    }

    public String upload(String localFilePath){

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(BUCKET_SOUND);
        try {
            com.qiniu.http.Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            key = putRet.key;
            System.out.println(putRet.key+"hash:"+putRet.hash);
        } catch (QiniuException ex) {
            com.qiniu.http.Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {

            }
        }
        return key;
    }

    public String upLoadImg(File file){
        return upload(file,BUCKET_IMG);
    }
    public String upLoadAudio(File file){
        return upload(file,BUCKET_SOUND);
    }

    public String upload(File file,String bucket){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                com.qiniu.http.Response response = uploadManager.put(file, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
               
                key = putRet.key;
                logger.info(putRet.key+"hash:"+putRet.hash);

                return key;
            } catch (QiniuException ex) {
                com.qiniu.http.Response r = ex.response;
                logger.error("upload file to qiniu fail:",ex);
            }
        } catch (Exception ex) {
            //ignore
        }
        return key;
    }

    public String upload(byte[] uploadBytes){

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        try {
            ByteArrayInputStream byteInputStream=new ByteArrayInputStream(uploadBytes);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(BUCKET_SOUND);
            try {
                com.qiniu.http.Response response = uploadManager.put(byteInputStream,key,upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                key = putRet.key;
                System.out.println(putRet.key+"hash:"+putRet.hash);
            } catch (QiniuException ex) {
                com.qiniu.http.Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return key;
    }
    public static void main(String[] avgs){
        String key ="Fu77Wjo9cCqTjz9qpoEAjIo2l20I";
//        download = "http://p81hvnvrf.bkt.clouddn.com";
//        accessKey = "Gv7pF-g-LNhZqyFUcJhQzO2AJxuk2cZ7CsAksDgd";
//        secretKey = "sE5MLPbJK1KgXiM30q-OzHTqpcWLiwPK1gTV0CVQ";
//        bucket ="sound";
        File fil = new File("C:\\Users\\Administrator\\Desktop\\jars\\test.java");
//        upLoadImg(fil);
    }
}
