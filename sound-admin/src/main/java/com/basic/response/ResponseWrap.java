package com.basic.response;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * 接口回复基础类
 *
 * @author ccq
 * @date 2016年7月23日
 */
@ApiModel(value = "标准回复包装类")
public class ResponseWrap<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码", example = "200", required = true)
    private int code;

    /**
     * 描述信息
     */
    @ApiModelProperty(value = "返回描述", example = "请求成功", required = true, position = 1)
    private String desc;
    /**
     * 数据域
     */
    @ApiModelProperty(value = "数据", position = 2)
    private T data;

    public ResponseWrap() {
        this.code = ResponseCode.CODE_200.getCode();
        this.desc = ResponseCode.CODE_200.getDesc();
    }

    public ResponseWrap(ResponseCode code) {
        this.code = code.getCode();
        this.desc = code.getDesc();
    }

    public ResponseWrap(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.desc = responseCode.getDesc();
        this.data = data;
    }


    public ResponseWrap(ResponseCode errorCode, T datas, String appendDesc) {
        this.code = errorCode.getCode();
        if (!StringUtil.isNullOrEmpty(appendDesc)) {
            appendDesc = ":" + appendDesc;
        }
        this.desc = errorCode.getDesc() + appendDesc;
        this.data = datas;
    }


    public int getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 更新code
     *
     * @param code
     */
    public void updateCode(ResponseCode code) {
        this.code = code.getCode();
        this.desc = code.getDesc();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return (T) data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrap{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
