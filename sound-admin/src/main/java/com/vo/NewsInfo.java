package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "新闻资讯信息")
public class NewsInfo {

    @ApiModelProperty(value = "新闻ID")
    private Long newsId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "发布人")
    private String publisher;

    @ApiModelProperty(value = "文章状态")
    private String showStatus;

    @ApiModelProperty(value = "发布时间")
    private String releaseTime;

    @ApiModelProperty(value = "点赞人数")
    private Long likeCount;


    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", showStatus='" + showStatus + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }
}
