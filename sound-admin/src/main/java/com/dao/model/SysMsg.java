package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_msg")
public class SysMsg implements Serializable {
    @Id
    private Long id;

    /**
     * 插入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 图片名称
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 图片
     */
    private String imgurl;

    /**
     * 是否有效
     */
    private Boolean isable;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否显示1为显示 0为不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    /**
     * 消息内容
     */
    private String content;

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
     * 获取插入时间
     *
     * @return create_time - 插入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置插入时间
     *
     * @param createTime 插入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取图片名称
     *
     * @return img_name - 图片名称
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置图片名称
     *
     * @param imgName 图片名称
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * 获取图片
     *
     * @return imgurl - 图片
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 设置图片
     *
     * @param imgurl 图片
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 获取是否有效
     *
     * @return isable - 是否有效
     */
    public Boolean getIsable() {
        return isable;
    }

    /**
     * 设置是否有效
     *
     * @param isable 是否有效
     */
    public void setIsable(Boolean isable) {
        this.isable = isable;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
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

    /**
     * 获取消息内容
     *
     * @return content - 消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置消息内容
     *
     * @param content 消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", imgName=").append(imgName);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", isable=").append(isable);
        sb.append(", title=").append(title);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", content=").append(content);
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
        SysMsg other = (SysMsg) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getImgName() == null ? other.getImgName() == null : this.getImgName().equals(other.getImgName()))
                && (this.getImgurl() == null ? other.getImgurl() == null : this.getImgurl().equals(other.getImgurl()))
                && (this.getIsable() == null ? other.getIsable() == null : this.getIsable().equals(other.getIsable()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getImgName() == null) ? 0 : getImgName().hashCode());
        result = prime * result + ((getImgurl() == null) ? 0 : getImgurl().hashCode());
        result = prime * result + ((getIsable() == null) ? 0 : getIsable().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }
}