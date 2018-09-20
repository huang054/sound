package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author music
 */
@ApiModel(value = "专辑详情")
public class AlbumDetails {

    @ApiModelProperty(value = "专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "专辑名称")
    @NotNull
    private String albumName;

    @ApiModelProperty(value = "显示状态")
    @NotNull
    private Integer showStatus;

    @ApiModelProperty(value = "推荐等级")
    @NotNull
    private String recommendLevel;

    @ApiModelProperty(value = "专辑简介")
    @NotNull
    private String albumIntroduction;

    @ApiModelProperty(value = "是否是大v :1  非大v ：0")
    private Integer isBigVAlbum;

    @ApiModelProperty(value = "专辑图片")
    @NotNull
    private String imgUrl;

    @ApiModelProperty(value = "圆形白底图")
    private String roundImgUrl;

    public Integer getIsBigVAlbum() {
        return isBigVAlbum;
    }

    public void setIsBigVAlbum(Integer isBigVAlbum) {
        this.isBigVAlbum = isBigVAlbum;
    }

    public String getRoundImgUrl() {
        return roundImgUrl;
    }

    public void setRoundImgUrl(String roundImgUrl) {
        this.roundImgUrl = roundImgUrl;
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

    public String getRecommendLevel() {
        return recommendLevel;
    }

    public void setRecommendLevel(String recommendLevel) {
        this.recommendLevel = recommendLevel;
    }

    public String getAlbumIntroduction() {
        return albumIntroduction;
    }

    public void setAlbumIntroduction(String albumIntroduction) {
        this.albumIntroduction = albumIntroduction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "AlbumDetails{" +
                "albumId=" + albumId +
                ", albumName='" + albumName + '\'' +
                ", showStatus=" + showStatus +
                ", recommendLevel='" + recommendLevel + '\'' +
                ", albumIntroduction='" + albumIntroduction + '\'' +
                ", isBigVAlbum=" + isBigVAlbum +
                ", imgUrl='" + imgUrl + '\'' +
                ", roundImgUrl='" + roundImgUrl + '\'' +
                '}';
    }
}
