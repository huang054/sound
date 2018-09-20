package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity(name = "news")
@Table(name = "news")
public class NewsModel {

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="varchar(64) default ''")
	private String ticket;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间
    
	@Column(columnDefinition="varchar(255)  NOT null")
	@NotBlank(message="栏目名不能为空")
    private String columnName;//栏目名

	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private Long cloId;//栏目id

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="varchar(255)  NOT null")
    private String coverURL;//封面图片URL
    
    @Column(columnDefinition="varchar(255)  NOT null")
    @NotBlank(message="标题不能为空")
    private String title;//标题
    
    @Column(columnDefinition="varchar(255)  NOT null")
    @NotBlank(message="作者不能为空")
    private String authorName;//作者

	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private Long authorId;

	@Column(columnDefinition="text NOT null")
	@NotBlank(message="摘要不能为空")
    private String summary;//摘要

	@Column(columnDefinition="text NOT null")
	@NotBlank(message="资讯内容不能为空")
    private String content;//内容

	@Column(columnDefinition="varchar(255) NOT null")
	private String coverName;

    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long contentId;//html内容Id
    
    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long readCount;//阅读数
    
    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long praiseCount;//点赞数
    
    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long commentCount;//评论数

	@Column(columnDefinition="varchar(255) default ''")
	private String tag;//节目标签
    
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean showStatus;//是否显示1为显示 0为不显示
	
	

	public boolean isShowStatus() {
		return showStatus;
	}

	public void setShowStatus(boolean showStatus) {
		this.showStatus = showStatus;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public String getCoverName() {
		return coverName;
	}

	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}

	public Long getReadCount() {
		return readCount;
	}

	public void setReadCount(Long readCount) {
		this.readCount = readCount;
	}

	public Long getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(Long praiseCount) {
		this.praiseCount = praiseCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public Long getCloId() {
		return cloId;
	}

	public void setCloId(Long cloId) {
		this.cloId = cloId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "NewsModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", columnName="
				+ columnName + ", cloId=" + cloId + ", coverURL=" + coverURL + ", title=" + title + ", authorName="
				+ authorName + ", authorId=" + authorId + ", summary=" + summary + ", content=" + content
				+ ", coverName=" + coverName + ", contentId=" + contentId + ", readCount=" + readCount
				+ ", praiseCount=" + praiseCount + ", commentCount=" + commentCount + ", tag=" + tag + "]";
	}
	
}
