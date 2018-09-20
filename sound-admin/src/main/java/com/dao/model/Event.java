package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Event implements Serializable {
    /**
     * 活动表ID
     */
    @Id
    private Long id;

    /**
     * 活动地址
     */
    private String address;

    /**
     * 活动的内容
     */
    private String context;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 活动时间
     */
    @Column(name = "event_time")
    private Date eventTime;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 作者名字
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 内容
     */
    @Column(name = "context_url")
    private String contextUrl;

    /**
     * 封面
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 是否推荐
     */
    @Column(name = "is_recommended")
    private Boolean isRecommended;

    /**
     * 内容插图
     */
    private String url;

    /**
     * 活动标题
     */
    private String title;

    /**
     * 是否显示1为显示 0为不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    private static final long serialVersionUID = 1L;

    /**
     * 获取活动表ID
     *
     * @return id - 活动表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置活动表ID
     *
     * @param id 活动表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取活动地址
     *
     * @return address - 活动地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置活动地址
     *
     * @param address 活动地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取活动的内容
     *
     * @return context - 活动的内容
     */
    public String getContext() {
        return context;
    }

    /**
     * 设置活动的内容
     *
     * @param context 活动的内容
     */
    public void setContext(String context) {
        this.context = context;
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
     * 获取活动时间
     *
     * @return event_time - 活动时间
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * 设置活动时间
     *
     * @param eventTime 活动时间
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * 获取联系人电话
     *
     * @return phone - 联系人电话
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系人电话
     *
     * @param phone 联系人电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取作者名字
     *
     * @return author_name - 作者名字
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置作者名字
     *
     * @param authorName 作者名字
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 获取内容
     *
     * @return context_url - 内容
     */
    public String getContextUrl() {
        return contextUrl;
    }

    /**
     * 设置内容
     *
     * @param contextUrl 内容
     */
    public void setContextUrl(String contextUrl) {
        this.contextUrl = contextUrl;
    }

    /**
     * 获取封面
     *
     * @return img_url - 封面
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置封面
     *
     * @param imgUrl 封面
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取是否推荐
     *
     * @return is_recommended - 是否推荐
     */
    public Boolean getIsRecommended() {
        return isRecommended;
    }

    /**
     * 设置是否推荐
     *
     * @param isRecommended 是否推荐
     */
    public void setIsRecommended(Boolean isRecommended) {
        this.isRecommended = isRecommended;
    }

    /**
     * 获取内容插图
     *
     * @return url - 内容插图
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置内容插图
     *
     * @param url 内容插图
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取活动标题
     *
     * @return title - 活动标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置活动标题
     *
     * @param title 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取是否显示1为显示 0为不显示
     *
     * @return show_status - 是否显示1为显示 0为不显示
     */
    public Integer getShowStatus() {
        return showStatus;
    }

    /**
     * 设置是否显示1为显示 0为不显示
     *
     * @param showStatus 是否显示1为显示 0为不显示
     */
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", address=").append(address);
        sb.append(", context=").append(context);
        sb.append(", createTime=").append(createTime);
        sb.append(", eventTime=").append(eventTime);
        sb.append(", phone=").append(phone);
        sb.append(", authorName=").append(authorName);
        sb.append(", contextUrl=").append(contextUrl);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", isRecommended=").append(isRecommended);
        sb.append(", url=").append(url);
        sb.append(", title=").append(title);
        sb.append(", showStatus=").append(showStatus);
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
        Event other = (Event) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEventTime() == null ? other.getEventTime() == null : this.getEventTime().equals(other.getEventTime()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
            && (this.getContextUrl() == null ? other.getContextUrl() == null : this.getContextUrl().equals(other.getContextUrl()))
            && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
            && (this.getIsRecommended() == null ? other.getIsRecommended() == null : this.getIsRecommended().equals(other.getIsRecommended()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEventTime() == null) ? 0 : getEventTime().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getContextUrl() == null) ? 0 : getContextUrl().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getIsRecommended() == null) ? 0 : getIsRecommended().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        return result;
    }
}