package com.sound.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Album")
// 构建数据库表实体
@Table(name = "album")
public class Album implements Serializable{

	/**
	 * 专辑
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;//创建时间
	
	@Column(columnDefinition="varchar(255)  NOT null COMMENT '专辑名'")
	@NotBlank(message="专辑名不能为空")
    private String name;//专辑名

	//@ApiModelProperty(hidden = true)
	@Column(columnDefinition="varchar(255)  NOT null COMMENT '栏目图片'")
    private String imgMinUrl;//栏目图片

	//@ApiModelProperty(hidden = true)
	@Column(columnDefinition="varchar(255)  NOT null COMMENT '系统推荐图片'")
    private String imgMaxUrl;//系统推荐图片

	@Column(columnDefinition="text NOT null COMMENT '摘要'")
	@NotBlank(message="摘要不能为空")
	private String summary;//摘要

	@Column(columnDefinition="varchar(255)  NOT null COMMENT '专辑标题'")
	@NotBlank(message="专辑标题不能为空")
    private String title;//专辑标题

	@Column(columnDefinition="varchar(255)  NOT null COMMENT '推荐等级'")
    private String type;//推荐等级
    
	@Column(columnDefinition="tinyint(1) default 0 COMMENT '是否大V专辑'")
    private boolean isBigVAlbum;

	@Column(columnDefinition="bigint(20) DEFAULT 0 COMMENT '专辑作者'")
	private Long authorId;//专辑作者

	@NotBlank(message="专辑作者名不能为空")
	private String authorName;

	@Column(columnDefinition="varchar(255)  DEFAULT '' COMMENT '节目标签'")
	private String tag;//节目标签
	/*@Column(columnDefinition="bigint(20) DEFAULT 0")
	 private long categoryId;//节目id
*/	
	@Column(columnDefinition="bigint(20) DEFAULT 0 COMMENT '播放总数'")
	private Long playCount;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean showStatus;//是否显示1为显示 0为不显示
	
	

	public boolean isShowStatus() {
		return showStatus;
	}

	public void setShowStatus(boolean showStatus) {
		this.showStatus = showStatus;
	}
	public boolean isBigVAlbum() {
		return isBigVAlbum;
	}
	public void setBigVAlbum(boolean isBigVAlbum) {
		this.isBigVAlbum = isBigVAlbum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgMinUrl() {
		return imgMinUrl;
	}
	public void setImgMinUrl(String imgMinUrl) {
		this.imgMinUrl = imgMinUrl;
	}
	public String getImgMaxUrl() {
		return imgMaxUrl;
	}
	public void setImgMaxUrl(String imgMaxUrl) {
		this.imgMaxUrl = imgMaxUrl;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	/*public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}*/
	public Long getPlayCount() {
		return playCount;
	}
	public void setPlayCount(Long playCount) {
		this.playCount = playCount;
	}
	@Override
	public String toString() {
		return "Album [id=" + id + ", createTime=" + createTime + ", name=" + name + ", imgMinUrl=" + imgMinUrl
				+ ", imgMaxUrl=" + imgMaxUrl + ", summary=" + summary + ", title=" + title + ", type=" + type
				+ ", isBigVAlbum=" + isBigVAlbum + ", authorId=" + authorId + ", authorName=" + authorName + ", tag="
				+ tag + ", playCount=" + playCount + "]";
	}
	
	
}
