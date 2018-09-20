package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class News implements Serializable {
    @Id
    private Long id;

    /**
     * 作者ID
     */
    @Column(name = "author_id")
    private Long authorId;

    /**
     * 作者
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 栏目id
     */
    @Column(name = "clo_id")
    private Long cloId;

    /**
     * 栏目名
     */
    @Column(name = "column_name")
    private String columnName;

    /**
     * 评论数
     */
    @Column(name = "comment_count")
    private Long commentCount;

    /**
     * html内容Id
     */
    @Column(name = "content_id")
    private Long contentId;

    /**
     * 封面名称
     */
    @Column(name = "cover_name")
    private String coverName;

    /**
     * 封面图片URL
     */
    private String coverurl;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 点赞数
     */
    @Column(name = "praise_count")
    private Long praiseCount;

    /**
     * 阅读数
     */
    @Column(name = "read_count")
    private Long readCount;

    /**
     * 节目标签
     */
    private String tag;

    private String ticket;

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
     * 内容
     */
    private String content;

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
     * 获取作者ID
     *
     * @return author_id - 作者ID
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者ID
     *
     * @param authorId 作者ID
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取作者
     *
     * @return author_name - 作者
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置作者
     *
     * @param authorName 作者
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 获取栏目id
     *
     * @return clo_id - 栏目id
     */
    public Long getCloId() {
        return cloId;
    }

    /**
     * 设置栏目id
     *
     * @param cloId 栏目id
     */
    public void setCloId(Long cloId) {
        this.cloId = cloId;
    }

    /**
     * 获取栏目名
     *
     * @return column_name - 栏目名
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置栏目名
     *
     * @param columnName 栏目名
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * 获取评论数
     *
     * @return comment_count - 评论数
     */
    public Long getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论数
     *
     * @param commentCount 评论数
     */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取html内容Id
     *
     * @return content_id - html内容Id
     */
    public Long getContentId() {
        return contentId;
    }

    /**
     * 设置html内容Id
     *
     * @param contentId html内容Id
     */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /**
     * 获取封面名称
     *
     * @return cover_name - 封面名称
     */
    public String getCoverName() {
        return coverName;
    }

    /**
     * 设置封面名称
     *
     * @param coverName 封面名称
     */
    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    /**
     * 获取封面图片URL
     *
     * @return coverurl - 封面图片URL
     */
    public String getCoverurl() {
        return coverurl;
    }

    /**
     * 设置封面图片URL
     *
     * @param coverurl 封面图片URL
     */
    public void setCoverurl(String coverurl) {
        this.coverurl = coverurl;
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
     * 获取点赞数
     *
     * @return praise_count - 点赞数
     */
    public Long getPraiseCount() {
        return praiseCount;
    }

    /**
     * 设置点赞数
     *
     * @param praiseCount 点赞数
     */
    public void setPraiseCount(Long praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * 获取阅读数
     *
     * @return read_count - 阅读数
     */
    public Long getReadCount() {
        return readCount;
    }

    /**
     * 设置阅读数
     *
     * @param readCount 阅读数
     */
    public void setReadCount(Long readCount) {
        this.readCount = readCount;
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
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
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
        sb.append(", cloId=").append(cloId);
        sb.append(", columnName=").append(columnName);
        sb.append(", commentCount=").append(commentCount);
        sb.append(", contentId=").append(contentId);
        sb.append(", coverName=").append(coverName);
        sb.append(", coverurl=").append(coverurl);
        sb.append(", createTime=").append(createTime);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", readCount=").append(readCount);
        sb.append(", tag=").append(tag);
        sb.append(", ticket=").append(ticket);
        sb.append(", title=").append(title);
        sb.append(", showStatus=").append(showStatus);
        sb.append(", content=").append(content);
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
        News other = (News) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
                && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
                && (this.getCloId() == null ? other.getCloId() == null : this.getCloId().equals(other.getCloId()))
                && (this.getColumnName() == null ? other.getColumnName() == null : this.getColumnName().equals(other.getColumnName()))
                && (this.getCommentCount() == null ? other.getCommentCount() == null : this.getCommentCount().equals(other.getCommentCount()))
                && (this.getContentId() == null ? other.getContentId() == null : this.getContentId().equals(other.getContentId()))
                && (this.getCoverName() == null ? other.getCoverName() == null : this.getCoverName().equals(other.getCoverName()))
                && (this.getCoverurl() == null ? other.getCoverurl() == null : this.getCoverurl().equals(other.getCoverurl()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getPraiseCount() == null ? other.getPraiseCount() == null : this.getPraiseCount().equals(other.getPraiseCount()))
                && (this.getReadCount() == null ? other.getReadCount() == null : this.getReadCount().equals(other.getReadCount()))
                && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
                && (this.getTicket() == null ? other.getTicket() == null : this.getTicket().equals(other.getTicket()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getCloId() == null) ? 0 : getCloId().hashCode());
        result = prime * result + ((getColumnName() == null) ? 0 : getColumnName().hashCode());
        result = prime * result + ((getCommentCount() == null) ? 0 : getCommentCount().hashCode());
        result = prime * result + ((getContentId() == null) ? 0 : getContentId().hashCode());
        result = prime * result + ((getCoverName() == null) ? 0 : getCoverName().hashCode());
        result = prime * result + ((getCoverurl() == null) ? 0 : getCoverurl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getPraiseCount() == null) ? 0 : getPraiseCount().hashCode());
        result = prime * result + ((getReadCount() == null) ? 0 : getReadCount().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getTicket() == null) ? 0 : getTicket().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        return result;
    }
}