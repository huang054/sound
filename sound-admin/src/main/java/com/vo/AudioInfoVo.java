package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "音频信息")
public class AudioInfoVo {

    @ApiModelProperty(value = "音频ID")
    private Long audioId;

    @ApiModelProperty(value = "节目名称")
    private String title;

    @ApiModelProperty(value = "作者名字")
    private String authorName;

    @ApiModelProperty(value = "推荐等级")
    private String recommendLevel;

    @ApiModelProperty(value = "送币类型")
    private String giftType;

    @ApiModelProperty(value = "所属专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "所属专辑名称")
    private String albumName;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "阅读人数")
    private Long readCount;

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
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

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Long getReadCount() {
        return readCount;
    }

    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    @Override
    public String toString() {
        return "AudioInfoVo{" +
                "audioId=" + audioId +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", recommendLevel='" + recommendLevel + '\'' +
                ", giftType='" + giftType + '\'' +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", showStatus=" + showStatus +
                ", readCount=" + readCount +
                '}';
    }
}
