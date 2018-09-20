package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author music
 */
@ApiModel(value = "活动详情")
public class ActivityDetails {

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(value = "社区首页图")
    private String HomePageImgUrl;

    @ApiModelProperty(value = "内部图")
    private String internalImgUrl;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "联系方式")
    private String phoneNumber;

    private String activityAddress;

    @ApiModelProperty(value = "是否显示1为显示 0为不显示")
    private Integer showStatus;

    @ApiModelProperty(value = "活动内容")
    @NotNull(message = "必填字段")
    private String activityContent;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getHomePageImgUrl() {
        return HomePageImgUrl;
    }

    public void setHomePageImgUrl(String homePageImgUrl) {
        HomePageImgUrl = homePageImgUrl;
    }

    public String getInternalImgUrl() {
        return internalImgUrl;
    }

    public void setInternalImgUrl(String internalImgUrl) {
        this.internalImgUrl = internalImgUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivityAddress() {
        return activityAddress;
    }

    public void setActivityAddress(String activityAddress) {
        this.activityAddress = activityAddress;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getActivityContent() {
        return activityContent;
    }

    public void setActivityContent(String activityContent) {
        this.activityContent = activityContent;
    }

    @Override
    public String toString() {
        return "ActivityDetails{" +
                "activityId=" + activityId +
                ", activityTitle='" + activityTitle + '\'' +
                ", HomePageImgUrl='" + HomePageImgUrl + '\'' +
                ", internalImgUrl='" + internalImgUrl + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", activityAddress='" + activityAddress + '\'' +
                ", showStatus=" + showStatus +
                ", activityContent='" + activityContent + '\'' +
                '}';
    }
}
