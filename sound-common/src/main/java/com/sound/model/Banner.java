package com.sound.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Banner")
@Table(name = "banner")
public class Banner implements Serializable {

    /**
     * banner表
     */

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "varchar(64)  DEFAULT ''")
    private String bannerPicUrl;
    @Column(columnDefinition = "varchar(64)  DEFAULT ''")
    private String bannerUrl;
    @ApiModelProperty(hidden = true)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;
    @Column(columnDefinition = "tinyint(1) default 1")
    private boolean showStatus;//是否显示1为显示 0为不显示

    @Column(columnDefinition = "int(10)  DEFAULT 0 COMMENT '排序值'")
    private int sort;

    @Column(columnDefinition = "varchar(64)  DEFAULT NULL COMMENT '活动名称'")
    private String bannerName;

    @Column(columnDefinition = "int(2)  DEFAULT NULL COMMENT '展示区域 1：小链频道 2：小链社区'")
    private int showArea;

    public int getShowArea() {
        return showArea;
    }

    public void setShowArea(int showArea) {
        this.showArea = showArea;
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

    public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", bannerPicUrl='" + bannerPicUrl + '\'' +
                ", bannerUrl='" + bannerUrl + '\'' +
                ", createTime=" + createTime +
                ", showStatus=" + showStatus +
                ", sort=" + sort +
                ", bannerName='" + bannerName + '\'' +
                '}';
    }
}
