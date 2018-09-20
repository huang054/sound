package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author music
 */
@ApiModel(value = "音频详情")
public class AudioDetails {

    @ApiModelProperty(value = "音频ID")
    private Long audioId;

    @ApiModelProperty(value = "音频名称")
    private String audioTitle;

    @ApiModelProperty(value = "作者名字")
    private String authorName;

    @ApiModelProperty(value = "专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "专辑名称")
    private String albumName;

    @ApiModelProperty(value = "显示状态")
    private Integer showStatus;

    @ApiModelProperty(value = "推荐等级 顶级为4 一级3 二级为2 三级为1 无 0")
    private Integer recommendLevel;

    @ApiModelProperty(value = "赠送音频币种")
    private List<AudioGiftInfo> audioGiftInfoList;

    @ApiModelProperty(value = "节目图片")
    private String audioImgUrl;

    @ApiModelProperty(value = "播放页面")
    private String playImgUrl;

    @ApiModelProperty(value = "音频文件路径")
    private String audioUrl;

    @ApiModelProperty(value = "音频文件大小")
    private Long audioFileSize;

    @ApiModelProperty(value = "音频文件名称")
    private String audioFileName;

    @ApiModelProperty(value = "音频文件MD5")
    private String audioFileMd5;

    @ApiModelProperty(value = "音频文件时长")
    private Long audioTime;

    @ApiModelProperty(value = "展示区域 推荐 1 轻路演 2")
    private Integer showArea;


    public Integer getShowArea() {
        return showArea;
    }

    public void setShowArea(Integer showArea) {
        this.showArea = showArea;
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }


    public String getAudioTitle() {
        return audioTitle;
    }

    public void setAudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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

    public Integer getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(Integer recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public List<AudioGiftInfo> getAudioGiftInfoList() {
        return audioGiftInfoList;
    }

    public void setAudioGiftInfoList(List<AudioGiftInfo> audioGiftInfoList) {
        this.audioGiftInfoList = audioGiftInfoList;
    }

    public String getAudioImgUrl() {
        return audioImgUrl;
    }

    public void setAudioImgUrl(String audioImgUrl) {
        this.audioImgUrl = audioImgUrl;
    }

    public String getPlayImgUrl() {
        return playImgUrl;
    }

    public void setPlayImgUrl(String playImgUrl) {
        this.playImgUrl = playImgUrl;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public Long getAudioFileSize() {
        return audioFileSize;
    }

    public void setAudioFileSize(Long audioFileSize) {
        this.audioFileSize = audioFileSize;
    }

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public String getAudioFileMd5() {
        return audioFileMd5;
    }

    public void setAudioFileMd5(String audioFileMd5) {
        this.audioFileMd5 = audioFileMd5;
    }

    public Long getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(Long audioTime) {
        this.audioTime = audioTime;
    }

    @Override
    public String toString() {
        return "AudioDetails{" +
                "audioId=" + audioId +
                ", audioTitle='" + audioTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                ", albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", showStatus=" + showStatus +
                ", recommendLevel=" + recommendLevel +
                ", audioGiftInfoList=" + audioGiftInfoList +
                ", audioImgUrl='" + audioImgUrl + '\'' +
                ", playImgUrl='" + playImgUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", audioFileSize=" + audioFileSize +
                ", audioFileName='" + audioFileName + '\'' +
                ", audioFileMd5='" + audioFileMd5 + '\'' +
                ", audioTime=" + audioTime +
                ", showArea=" + showArea +
                '}';
    }
}
