package com.sound.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "audio")
@Table(name = "audio")
public class AudioModel {

    /**
     * 音频表
     */
    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(64)  DEFAULT '' COMMENT ''")
    private String ticket;

    @ApiModelProperty(hidden = true)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'")
    private Date createTime;


    @Column(columnDefinition = "bigint(20) default 0 COMMENT '大v专辑ID'")
    private Long bigAlbumId;

    @Column(columnDefinition = "bigint(20) default 0 COMMENT '专辑ID'")
    private Long albumId;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '专辑名称'")
    private String albumName;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '播放时长，以秒为单位'")
    private Long durantionSec;

    /**
     * 音频名
     */
    @NotBlank(message = "音频名不能为空")
    private String title;

    /**
     * 作者名
     */
    @NotBlank(message = "请填写作者")
    private String authorName;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '作者ID'")
    private Long authorId;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '阅读数'")
    private Long readCount;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '收藏数'")
    private Long praiseCount;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '文件名称'")
    private String fileName;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '文件路径'")
    private String filepath;

    @Column(columnDefinition = "tinyint(1) default 0 COMMENT '文件是否存储在本地'")
    private Boolean isLocal;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '标签'")
    private String label;

    @Column(columnDefinition = "varchar(255) COMMENT '备注'")
    private String remark;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '文件大小'")
    private Long fileSize;

    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '播放次数'")
    private Long playCount;

    @Column(columnDefinition = "varchar(255)  DEFAULT ''")
    private String md5;

    @Column(columnDefinition = "int(11) DEFAULT 0 COMMENT '推荐等级 顶级为4 一级3 二级为2 三级为1 无 0'")
    private Integer roadShow;

    @Column(columnDefinition = "int(11) DEFAULT 0 COMMENT '推荐 1 轻路演 2'")
    private Integer isRoadShow;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '节目得图片路径'")
    private String picUrl;

    @Column(columnDefinition = "varchar(255)  DEFAULT '' COMMENT '封面节目得图片路径'")
    private String imgUrl;

    @Column(columnDefinition = "tinyint(1) default 1 COMMENT '是否显示1为显示 0为不显示'")
    private boolean showStatus;

    @NotNull(message = "节目标签不能为空")
    @Column(columnDefinition = "bigint(20) DEFAULT 0 COMMENT '节目标签'")
    private Long tagId;
    
    @Column(columnDefinition = "int(11) DEFAULT 0 COMMENT '根据播放时长获取 1 根据用户算力获取 2'")
    private Integer access;

    
    public Integer getAccess() {
		return access;
	}

	public void setAccess(Integer access) {
		this.access = access;
	}

	public boolean isShowStatus() {
        return showStatus;
    }

    public void setShowStatus(boolean showStatus) {
        this.showStatus = showStatus;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getRoadShow() {
        return roadShow;
    }


    public void setRoadShow(Integer roadShow) {
        this.roadShow = roadShow;
    }

    public Boolean getIsLocal() {
        return isLocal;
    }

    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
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

    public Long getDurantionSec() {
        return durantionSec;
    }

    public void setDurantionSec(Long durantionSec) {
        this.durantionSec = durantionSec;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    public Boolean isLocal() {
        return isLocal;
    }

    public void setLocal(Boolean local) {
        isLocal = local;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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

    public Boolean getLocal() {
        return isLocal;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getIsRoadShow() {
        return isRoadShow;
    }

    public void setIsRoadShow(Integer isRoadShow) {
        this.isRoadShow = isRoadShow;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Long getBigAlbumId() {
        return bigAlbumId;
    }

    public void setBigAlbumId(Long bigAlbumId) {
        this.bigAlbumId = bigAlbumId;
    }


    @Override
    public String toString() {
        return "AudioModel{" +
                "id=" + id +
                ", ticket='" + ticket + '\'' +
                ", createTime=" + createTime +
                ", bigAlbumId=" + bigAlbumId +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", durantionSec=" + durantionSec +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorId=" + authorId +
                ", readCount=" + readCount +
                ", praiseCount=" + praiseCount +
                ", fileName='" + fileName + '\'' +
                ", filepath='" + filepath + '\'' +
                ", isLocal=" + isLocal +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                ", fileSize=" + fileSize +
                ", playCount=" + playCount +
                ", md5='" + md5 + '\'' +
                ", roadShow=" + roadShow +
                ", isRoadShow=" + isRoadShow +
                ", picUrl='" + picUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", showStatus=" + showStatus +
                ", tagId=" + tagId +
                '}';
    }
}
