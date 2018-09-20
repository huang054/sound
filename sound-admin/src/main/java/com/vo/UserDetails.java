package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "用户详情")
public class UserDetails {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "推荐人")
    private String referrer;

    @ApiModelProperty(value = "绑定设备")
    private List<String> bindingDevice;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public List<String> getBindingDevice() {
        return bindingDevice;
    }

    public void setBindingDevice(List<String> bindingDevice) {
        this.bindingDevice = bindingDevice;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userName='" + userName + '\'' +
                ", sex=" + sex +
                ", phoneNumber=" + phoneNumber +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", referrer='" + referrer + '\'' +
                ", bindingDevice=" + bindingDevice +
                '}';
    }
}
