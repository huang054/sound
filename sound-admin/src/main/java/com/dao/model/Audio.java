package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Audio implements Serializable {
    /**
     * 音频表ID
     */
    @Id
    private Long id;

    /**
     * 专辑名称
     */
    @Column(name = "album_name")
    private String albumName;

    /**
     * 作者ID
     */
    @Column(name = "author_id")
    private Long authorId;

    /**
     * 作者名
     */
    @Column(name = "author_name")
    private String authorName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 播放时长，以秒为单位
     */
    @Column(name = "durantion_sec")
    private Long durantionSec;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Long fileSize;

    /**
     * 文件路径
     */
    private String filepath;

    /**
     * 文件是否存储在本地
     */
    @Column(name = "is_local")
    private Boolean isLocal;

    /**
     * 推荐 1 轻路演 2
     */
    @Column(name = "is_road_show")
    private Integer isRoadShow;

    /**
     * 标签
     */
    private String label;

    /**
     * 音频MD5
     */
    private String md5;

    /**
     * 播放次数
     */
    @Column(name = "play_count")
    private Long playCount;

    /**
     * 收藏数
     */
    @Column(name = "praise_count")
    private Long praiseCount;

    /**
     * 阅读数
     */
    @Column(name = "read_count")
    private Long readCount;

    /**
     * 备注
     */
    private String remark;

    /**
     * 轻路演等级 顶级为4 一级3 二级为2 三级为1 无 0
     */
    @Column(name = "road_show")
    private Integer roadShow;

    /**
     * 节目标签
     */
    @Column(name = "tag_id")
    private Long tagId;

    private String ticket;

    /**
     * 音频名
     */
    private String title;

    /**
     * 节目得图片路径
     */
    @Column(name = "pic_url")
    private String picUrl;

    private String author;

    /**
     * 专辑ID
     */
    @Column(name = "album_id")
    private Long albumId;

    /**
     * 大v专辑ID
     */
    @Column(name = "big_album_id")
    private Long bigAlbumId;

    /**
     * 封面节目得图片路径
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 是否显示1为显示 0为不显示
     */
    @Column(name = "show_status")
    private Integer showStatus;

    private static final long serialVersionUID = 1L;

    /**
     * 获取音频表ID
     *
     * @return id - 音频表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置音频表ID
     *
     * @param id 音频表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取专辑名称
     *
     * @return album_name - 专辑名称
     */
    public String getAlbumName() {
        return albumName;
    }

    /**
     * 设置专辑名称
     *
     * @param albumName 专辑名称
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
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
     * 获取作者名
     *
     * @return author_name - 作者名
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 设置作者名
     *
     * @param authorName 作者名
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
     * 获取播放时长，以秒为单位
     *
     * @return durantion_sec - 播放时长，以秒为单位
     */
    public Long getDurantionSec() {
        return durantionSec;
    }

    /**
     * 设置播放时长，以秒为单位
     *
     * @param durantionSec 播放时长，以秒为单位
     */
    public void setDurantionSec(Long durantionSec) {
        this.durantionSec = durantionSec;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取文件路径
     *
     * @return filepath - 文件路径
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * 设置文件路径
     *
     * @param filepath 文件路径
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * 获取文件是否存储在本地
     *
     * @return is_local - 文件是否存储在本地
     */
    public Boolean getIsLocal() {
        return isLocal;
    }

    /**
     * 设置文件是否存储在本地
     *
     * @param isLocal 文件是否存储在本地
     */
    public void setIsLocal(Boolean isLocal) {
        this.isLocal = isLocal;
    }

    /**
     * 获取推荐 1 轻路演 2
     *
     * @return is_road_show - 推荐 1 轻路演 2
     */
    public Integer getIsRoadShow() {
        return isRoadShow;
    }

    /**
     * 设置推荐 1 轻路演 2
     *
     * @param isRoadShow 推荐 1 轻路演 2
     */
    public void setIsRoadShow(Integer isRoadShow) {
        this.isRoadShow = isRoadShow;
    }

    /**
     * 获取标签
     *
     * @return label - 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     *
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取音频MD5
     *
     * @return md5 - 音频MD5
     */
    public String getMd5() {
        return md5;
    }

    /**
     * 设置音频MD5
     *
     * @param md5 音频MD5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取播放次数
     *
     * @return play_count - 播放次数
     */
    public Long getPlayCount() {
        return playCount;
    }

    /**
     * 设置播放次数
     *
     * @param playCount 播放次数
     */
    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    /**
     * 获取收藏数
     *
     * @return praise_count - 收藏数
     */
    public Long getPraiseCount() {
        return praiseCount;
    }

    /**
     * 设置收藏数
     *
     * @param praiseCount 收藏数
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取轻路演等级 顶级为4 一级3 二级为2 三级为1 无 0
     *
     * @return road_show - 轻路演等级 顶级为4 一级3 二级为2 三级为1 无 0
     */
    public Integer getRoadShow() {
        return roadShow;
    }

    /**
     * 设置轻路演等级 顶级为4 一级3 二级为2 三级为1 无 0
     *
     * @param roadShow 轻路演等级 顶级为4 一级3 二级为2 三级为1 无 0
     */
    public void setRoadShow(Integer roadShow) {
        this.roadShow = roadShow;
    }

    /**
     * 获取节目标签
     *
     * @return tag_id - 节目标签
     */
    public Long getTagId() {
        return tagId;
    }

    /**
     * 设置节目标签
     *
     * @param tagId 节目标签
     */
    public void setTagId(Long tagId) {
        this.tagId = tagId;
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
     * 获取音频名
     *
     * @return title - 音频名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置音频名
     *
     * @param title 音频名
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取节目得图片路径
     *
     * @return pic_url - 节目得图片路径
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设置节目得图片路径
     *
     * @param picUrl 节目得图片路径
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取专辑ID
     *
     * @return album_id - 专辑ID
     */
    public Long getAlbumId() {
        return albumId;
    }

    /**
     * 设置专辑ID
     *
     * @param albumId 专辑ID
     */
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    /**
     * 获取大v专辑ID
     *
     * @return big_album_id - 大v专辑ID
     */
    public Long getBigAlbumId() {
        return bigAlbumId;
    }

    /**
     * 设置大v专辑ID
     *
     * @param bigAlbumId 大v专辑ID
     */
    public void setBigAlbumId(Long bigAlbumId) {
        this.bigAlbumId = bigAlbumId;
    }

    /**
     * 获取封面节目得图片路径
     *
     * @return img_url - 封面节目得图片路径
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置封面节目得图片路径
     *
     * @param imgUrl 封面节目得图片路径
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        sb.append(", albumName=").append(albumName);
        sb.append(", authorId=").append(authorId);
        sb.append(", authorName=").append(authorName);
        sb.append(", createTime=").append(createTime);
        sb.append(", durantionSec=").append(durantionSec);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", filepath=").append(filepath);
        sb.append(", isLocal=").append(isLocal);
        sb.append(", isRoadShow=").append(isRoadShow);
        sb.append(", label=").append(label);
        sb.append(", md5=").append(md5);
        sb.append(", playCount=").append(playCount);
        sb.append(", praiseCount=").append(praiseCount);
        sb.append(", readCount=").append(readCount);
        sb.append(", remark=").append(remark);
        sb.append(", roadShow=").append(roadShow);
        sb.append(", tagId=").append(tagId);
        sb.append(", ticket=").append(ticket);
        sb.append(", title=").append(title);
        sb.append(", picUrl=").append(picUrl);
        sb.append(", author=").append(author);
        sb.append(", albumId=").append(albumId);
        sb.append(", bigAlbumId=").append(bigAlbumId);
        sb.append(", imgUrl=").append(imgUrl);
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
        Audio other = (Audio) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getAlbumName() == null ? other.getAlbumName() == null : this.getAlbumName().equals(other.getAlbumName()))
                && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
                && (this.getAuthorName() == null ? other.getAuthorName() == null : this.getAuthorName().equals(other.getAuthorName()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getDurantionSec() == null ? other.getDurantionSec() == null : this.getDurantionSec().equals(other.getDurantionSec()))
                && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
                && (this.getFileSize() == null ? other.getFileSize() == null : this.getFileSize().equals(other.getFileSize()))
                && (this.getFilepath() == null ? other.getFilepath() == null : this.getFilepath().equals(other.getFilepath()))
                && (this.getIsLocal() == null ? other.getIsLocal() == null : this.getIsLocal().equals(other.getIsLocal()))
                && (this.getIsRoadShow() == null ? other.getIsRoadShow() == null : this.getIsRoadShow().equals(other.getIsRoadShow()))
                && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()))
                && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
                && (this.getPlayCount() == null ? other.getPlayCount() == null : this.getPlayCount().equals(other.getPlayCount()))
                && (this.getPraiseCount() == null ? other.getPraiseCount() == null : this.getPraiseCount().equals(other.getPraiseCount()))
                && (this.getReadCount() == null ? other.getReadCount() == null : this.getReadCount().equals(other.getReadCount()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getRoadShow() == null ? other.getRoadShow() == null : this.getRoadShow().equals(other.getRoadShow()))
                && (this.getTagId() == null ? other.getTagId() == null : this.getTagId().equals(other.getTagId()))
                && (this.getTicket() == null ? other.getTicket() == null : this.getTicket().equals(other.getTicket()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getPicUrl() == null ? other.getPicUrl() == null : this.getPicUrl().equals(other.getPicUrl()))
                && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
                && (this.getAlbumId() == null ? other.getAlbumId() == null : this.getAlbumId().equals(other.getAlbumId()))
                && (this.getBigAlbumId() == null ? other.getBigAlbumId() == null : this.getBigAlbumId().equals(other.getBigAlbumId()))
                && (this.getImgUrl() == null ? other.getImgUrl() == null : this.getImgUrl().equals(other.getImgUrl()))
                && (this.getShowStatus() == null ? other.getShowStatus() == null : this.getShowStatus().equals(other.getShowStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlbumName() == null) ? 0 : getAlbumName().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getAuthorName() == null) ? 0 : getAuthorName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDurantionSec() == null) ? 0 : getDurantionSec().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileSize() == null) ? 0 : getFileSize().hashCode());
        result = prime * result + ((getFilepath() == null) ? 0 : getFilepath().hashCode());
        result = prime * result + ((getIsLocal() == null) ? 0 : getIsLocal().hashCode());
        result = prime * result + ((getIsRoadShow() == null) ? 0 : getIsRoadShow().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getPlayCount() == null) ? 0 : getPlayCount().hashCode());
        result = prime * result + ((getPraiseCount() == null) ? 0 : getPraiseCount().hashCode());
        result = prime * result + ((getReadCount() == null) ? 0 : getReadCount().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getRoadShow() == null) ? 0 : getRoadShow().hashCode());
        result = prime * result + ((getTagId() == null) ? 0 : getTagId().hashCode());
        result = prime * result + ((getTicket() == null) ? 0 : getTicket().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getPicUrl() == null) ? 0 : getPicUrl().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getAlbumId() == null) ? 0 : getAlbumId().hashCode());
        result = prime * result + ((getBigAlbumId() == null) ? 0 : getBigAlbumId().hashCode());
        result = prime * result + ((getImgUrl() == null) ? 0 : getImgUrl().hashCode());
        result = prime * result + ((getShowStatus() == null) ? 0 : getShowStatus().hashCode());
        return result;
    }
}