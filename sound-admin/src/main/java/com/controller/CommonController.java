package com.controller;

import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.sound.common.QiNiuConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author music
 */
@Api(description = "公共服务: 上传，")
@RestController
@RequestMapping(path = "/api/common")
public class CommonController {

    @Autowired
    private QiNiuConfig qiNiuConfig;


    @ApiOperation(value = "文件上传接口")
    @PostMapping(path = "/upload")
    public ResponseWrap<String> upLoad(@Param(value = "上传的文件") @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseWrap<>(ResponseCode.CODE_1104);
        }
        // 上传文件到七牛云
        File file1 = null;
        String key = null;
        try {
            file1 = File.createTempFile(file.getName(), ".tmp");
            file.transferTo(file1);
            key = qiNiuConfig.upLoadAudio(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(key)) {
            return new ResponseWrap<>(ResponseCode.CODE_1105);
        }
//        logger.info("file path:" + file.getOriginalFilename());
        return new ResponseWrap<>(ResponseCode.CODE_200, key);
    }
}
