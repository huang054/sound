package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "活动参与信息")
public class ActivityParticipateInfo {

    @ApiModelProperty(value = "参与Id")
    private Long participateId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    private Long phoneNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getParticipateId() {
        return participateId;
    }

    public void setParticipateId(Long participateId) {
        this.participateId = participateId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
