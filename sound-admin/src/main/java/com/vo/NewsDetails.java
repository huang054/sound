package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.scene.chart.ValueAxis;

import javax.validation.constraints.NotNull;

/**
 * @author music
 */
@ApiModel(value = "新闻资讯详情")
public class NewsDetails {

    @ApiModelProperty(value = "新闻ID")
    private Long newsId;


    @ApiModelProperty(value = "新闻标题")
    @NotNull
    private String newsTitle;

    @ApiModelProperty(value = "图片")
    @NotNull
    private String imgUrl;

    @ApiModelProperty(value = "发布人")
    private String publisher;

    @ApiModelProperty(value = "显示状态")
    @NotNull
    private Integer showStatus;

    @ApiModelProperty(value = "文章内容")
    @NotNull(message = "必填字段")
    private String newsContent;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    @Override
    public String toString() {
        return "NewsDetails{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", publisher='" + publisher + '\'' +
                ", showStatus=" + showStatus +
                ", newsContent='" + newsContent + '\'' +
                '}';
    }
}
