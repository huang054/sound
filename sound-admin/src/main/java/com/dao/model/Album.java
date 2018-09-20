package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Album implements Serializable {
    @Id
    private Long id;

    /**
     * 专辑作者
     */
    @Column(name = "author_id")
    private Long authorId;

    /**
     * 专辑作者名称
     */
    @Column(name = "author_name")
    private String authorName;

    @Column(name = "category_id")
    private Long categoryId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 系统推荐图片
     */
    @Column(name = "img_max_url")
    private String imgMaxUrl;

    /**
     * 栏目图片
     */
    @Column(name = "img_min_url")
    private String imgMinUrl;

    /**
     * 专辑名
     */
    private String name;

    /**
     * 节目标签
     */
    private String tag;

    /**
     * 专辑标题
     */
    private String title;

    /**
     * 推荐等级
     */
    private String type;

    /**
     * 播放总数
     */
    @Column(name = "play_count")
    private Long playCount;

    @Column(name = "audio_count")
    private Long audioCount;

    private Boolean display;

    private Integer priority;

    private String ticket;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "is_bigvalbum")
    private Integer isBigvalbum;

    /**
     * 是否显示1为显示 0为不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    /**
     * 摘要
     */
    private String summary;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取专辑作者
     *
     * @return author_id - 专辑作者
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 设置专辑作者
     *
     * @param authorId 专辑作者
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取专辑作者名称
     *
     * @return author_name - 专辑作者名称
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置专辑作者名称
     *
     * @param authorName 专辑作者名称
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * @return category_id
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * 获取系统推荐图片
     *
     * @return img_max_url - 系统推荐图片
     */
    public String getImgMaxUrl() {
        return imgMaxUrl;
    }

    /**
     * 设置系统推荐图片
     *
     * @param imgMaxUrl 系统推荐图片
     */
    public void setImgMaxUrl(String imgMaxUrl) {
        this.imgMaxUrl = imgMaxUrl;
    }

    /**
     * 获取栏目图片
     *
     * @return img_min_url - 栏目图片
     */
    public String getImgMinUrl() {
        return imgMinUrl;
    }

    /**
     * 设置栏目图片
     *
     * @param imgMinUrl 栏目图片
     */
    public void setImgMinUrl(String imgMinUrl) {
        this.imgMinUrl = imgMinUrl;
    }

    /**
     * 获取专辑名
     *
     * @return name - 专辑名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置专辑名
     *
     * @param name 专辑名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取节目标签
     *
     * @return tag - 节目标签
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置节目标签
     *
     * @param tag 节目标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取专辑标题
     *
     * @return title - 专辑标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置专辑标题
     *
     * @param title 专辑标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取推荐等级
     *
     * @return type - 推荐等级
     */
    public String getType() {
        return type;
    }

    /**
     * 设置推荐等级
     *
     * @param type 推荐等级
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取播放总数
     *
     * @return play_count - 播放总数
     */
    public Long getPlayCount() {
        return playCount;
    }

    /**
     * 设置播放总数
     *
     * @param playCount 播放总数
     */
    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    /**
     * @return audio_count
     */
    public Long getAudioCount() {
        return audioCount;
    }

    /**
     * @param audioCount
     */
    public void setAudioCount(Long audioCount) {
        this.audioCount = audioCount;
    }

    /**
     * @return display
     */
    public Boolean getDisplay() {
        return display;
    }

    /**
     * @param display
     */
    public void setDisplay(Boolean display) {
        this.display = display;
    }

    /**
     * @return priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return type_id
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * @return is_bigvalbum
     */
    public Integer getIsBigvalbum() {
        return isBigvalbum;
    }

    /**
     * @param isBigvalbum
     */
    public void setIsBigvalbum(Integer isBigvalbum) {
        this.isBigvalbum = isBigvalbum;
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

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName=").append(authorName);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", createTime=").append(createTime);
        sb.append(", imgMaxUrl=").append(imgMaxUrl);
        sb.append(", imgMinUrl=").append(imgMinUrl);
        sb.append(", name=").append(name);
        sb.append(", tag=").append(tag);
        sb.append(", title=").append(title);
        sb.append(", type=").append(type);
        sb.append(", playCount=").append(playCount);
        sb.append(", audioCount=").append(audioCount);
        sb.append(", display=").append(display);
        sb.append(", priority=").append(priority);
        sb.append(", ticket=").append(ticket);
        sb.append(", typeId=").append(typeId);
        sb.append(", isBigvalbum=").append(isBigvalbum);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", summary=").append(summary);
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
        Album other = (Album) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
                && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
                && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getImgMaxUrl() == null ? other.getImgMaxUrl() == null : this.getImgMaxUrl().equals(other.getImgMaxUrl()))
                && (this.getImgMinUrl() == null ? other.getImgMinUrl() == null : this.getImgMinUrl().equals(other.getImgMinUrl()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getPlayCount() == null ? other.getPlayCount() == null : this.getPlayCount().equals(other.getPlayCount()))
                && (this.getAudioCount() == null ? other.getAudioCount() == null : this.getAudioCount().equals(other.getAudioCount()))
                && (this.getDisplay() == null ? other.getDisplay() == null : this.getDisplay().equals(other.getDisplay()))
                && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
                && (this.getTicket() == null ? other.getTicket() == null : this.getTicket().equals(other.getTicket()))
                && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
                && (this.getIsBigvalbum() == null ? other.getIsBigvalbum() == null : this.getIsBigvalbum().equals(other.getIsBigvalbum()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
                && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getImgMaxUrl() == null) ? 0 : getImgMaxUrl().hashCode());
        result = prime * result + ((getImgMinUrl() == null) ? 0 : getImgMinUrl().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPlayCount() == null) ? 0 : getPlayCount().hashCode());
        result = prime * result + ((getAudioCount() == null) ? 0 : getAudioCount().hashCode());
        result = prime * result + ((getDisplay() == null) ? 0 : getDisplay().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getTicket() == null) ? 0 : getTicket().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getIsBigvalbum() == null) ? 0 : getIsBigvalbum().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        return result;
    }
}