package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Banner implements Serializable {
    /**
     * 表ID
     */
    @Id
    private Long id;

    /**
     * 图片地址
     */
    @Column(name = "banner_pic_url")
    private String bannerPicUrl;

    /**
     * 链接地址
     */
    @Column(name = "banner_url")
    private String bannerUrl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否显示：1为显示 0为不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    /**
     * 活动名称
     */
    @Column(name = "banner_name")
    private String bannerName;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 展示区域 1：小链频道 2：小链社区
     */
    @Column(name = "show_area")
    private Integer showArea;

    private static final long serialVersionUID = 1L;

    /**
     * 获取表ID
     *
     * @return id - 表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置表ID
     *
     * @param id 表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取图片地址
     *
     * @return banner_pic_url - 图片地址
     */
    public String getBannerPicUrl() {
        return bannerPicUrl;
    }

    /**
     * 设置图片地址
     *
     * @param bannerPicUrl 图片地址
     */
    public void setBannerPicUrl(String bannerPicUrl) {
        this.bannerPicUrl = bannerPicUrl;
    }

    /**
     * 获取链接地址
     *
     * @return banner_url - 链接地址
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * 设置链接地址
     *
     * @param bannerUrl 链接地址
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否显示：1为显示 0为不显示
     *
     * @return show_status - 是否显示：1为显示 0为不显示
     */
    public Integer getShowStatus() {
        return showStatus;
    }

    /**
     * 设置是否显示：1为显示 0为不显示
     *
     * @param showStatus 是否显示：1为显示 0为不显示
     */
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    /**
     * 获取活动名称
     *
     * @return banner_name - 活动名称
     */
    public String getBannerName() {
        return bannerName;
    }

    /**
     * 设置活动名称
     *
     * @param bannerName 活动名称
     */
    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    /**
     * 获取排序值
     *
     * @return sort - 排序值
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序值
     *
     * @param sort 排序值
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取展示区域 1：小链频道 2：小链社区
     *
     * @return show_area - 展示区域 1：小链频道 2：小链社区
     */
    public Integer getShowArea() {
        return showArea;
    }

    /**
     * 设置展示区域 1：小链频道 2：小链社区
     *
     * @param showArea 展示区域 1：小链频道 2：小链社区
     */
    public void setShowArea(Integer showArea) {
        this.showArea = showArea;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bannerPicUrl=").append(bannerPicUrl);
        sb.append(", bannerUrl=").append(bannerUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", bannerName=").append(bannerName);
        sb.append(", sort=").append(sort);
        sb.append(", showArea=").append(showArea);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Banner other = (Banner) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getBannerPicUrl() == null ? other.getBannerPicUrl() == null : this.getBannerPicUrl().equals(other.getBannerPicUrl()))
                && (this.getBannerUrl() == null ? other.getBannerUrl() == null : this.getBannerUrl().equals(other.getBannerUrl()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
                && (this.getBannerName() == null ? other.getBannerName() == null : this.getBannerName().equals(other.getBannerName()))
                && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
                && (this.getShowArea() == null ? other.getShowArea() == null : this.getShowArea().equals(other.getShowArea()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBannerPicUrl() == null) ? 0 : getBannerPicUrl().hashCode());
        result = prime * result + ((getBannerUrl() == null) ? 0 : getBannerUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getBannerName() == null) ? 0 : getBannerName().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getShowArea() == null) ? 0 : getShowArea().hashCode());
        return result;
    }
}