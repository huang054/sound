package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "专辑信息")
public class AlbumInfoVo {

    @ApiModelProperty(value = "专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "专辑名称")
    private String albumName;

    @ApiModelProperty(value = "推荐等级")
    private String recommendLevel;

    @ApiModelProperty(value = "节目数量")
    private Long audioCount;

    @ApiModelProperty(value = "播放数量")
    private Long playCount;

    @ApiModelProperty(value = "显示状态 1为显示 0为不显示")
    private Integer showStatus;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

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

    public String getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public Long getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(Long audioCount) {
        this.audioCount = audioCount;
    }

    public Long getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Long playCount) {
        this.playCount = playCount;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AlbumInfoVo{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", recommendLevel=" + recommendLevel +
                ", audioCount=" + audioCount +
                ", playCount=" + playCount +
                ", showStatus=" + showStatus +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
