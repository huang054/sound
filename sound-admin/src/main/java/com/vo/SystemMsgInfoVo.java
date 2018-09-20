package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "系统消息信息")
public class SystemMsgInfoVo {


    @ApiModelProperty(value = "系统消息ID")
    private Long systemMsgId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "发布时间")
    private String releaseTime;

    @ApiModelProperty(value = "系统消息内容")
    private String systemMsgContent;

    public String getSystemMsgContent() {
        return systemMsgContent;
    }

    public void setSystemMsgContent(String systemMsgContent) {
        this.systemMsgContent = systemMsgContent;
    }

    public Long getSystemMsgId() {
        return systemMsgId;
    }

    public void setSystemMsgId(Long systemMsgId) {
        this.systemMsgId = systemMsgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "SystemMsgInfoVo{" +
                "systemMsgId=" + systemMsgId +
                ", title='" + title + '\'' +
                ", showStatus=" + showStatus +
                ", releaseTime='" + releaseTime + '\'' +
                '}';
    }
}
