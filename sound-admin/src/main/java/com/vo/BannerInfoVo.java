package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author music
 */
@ApiModel(value = "banner图列表")
public class BannerInfoVo {

    @ApiModelProperty(value = "表ID 唯一标识")
    private Long bannerId;

    @ApiModelProperty(value = "排序值")
    private int sort;

    @ApiModelProperty(value = "活动名称")
    @NotNull(message = "活动名称必填字段")
    private String bannerName;

    @ApiModelProperty(value = "图片地址")
    @NotNull(message = "图片地址必填字段")
    private String imgUrl;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "显示状态 是否显示：1为显示 0为不显示")
    private int showStatus;

    @ApiModelProperty(value = "展示区域 1：小链频道 2：小链社区")
    private int showArea;

    public int getShowArea() {
        return showArea;
    }

    public void setShowArea(int showArea) {
        this.showArea = showArea;
    }

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        return "BannerInfoVo{" +
                "bannerId=" + bannerId +
                ", sort=" + sort +
                ", bannerName='" + bannerName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", showStatus=" + showStatus +
                ", showArea=" + showArea +
                '}';
    }
}
