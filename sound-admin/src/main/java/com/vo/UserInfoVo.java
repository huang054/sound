package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户信息")
public class UserInfoVo {

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "手机号码")
    private Long phoneNumber;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户资产 单位：分")
    private Integer userMoney;

    @ApiModelProperty(value = "星球排名")
    private Integer sort;

    @ApiModelProperty(value = "百度账号")
    private String baiduAccount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getBaiduAccount() {
        return baiduAccount;
    }

    public void setBaiduAccount(String baiduAccount) {
        this.baiduAccount = baiduAccount;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "userId=" + userId +
                ", phoneNumber=" + phoneNumber +
                ", userName='" + userName + '\'' +
                ", userMoney=" + userMoney +
                ", sort=" + sort +
                ", baiduAccount='" + baiduAccount + '\'' +
                '}';
    }
}
