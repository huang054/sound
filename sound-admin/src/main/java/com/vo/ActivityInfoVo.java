package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "活动信息")
public class ActivityInfoVo {

    @ApiModelProperty(value = "活动ID")
    private Long activityId;

    @ApiModelProperty(value = "活动标题")
    private String title;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "联系方式")
    private Long phoneNumber;

    @ApiModelProperty(value = "是否显示1为显示 0为不显示")
    private Integer showStatus;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "ActivityInfoVo{" +
                "activityId=" + activityId +
                ", title='" + title + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", showStatus=" + showStatus +
                '}';
    }
}
