package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "专辑")
public class AlbumVo {

    @ApiModelProperty(value = "专辑ID")
    private Long albumId;

    @ApiModelProperty(value = "专辑名称")
    private Long albumName;

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Long getAlbumName() {
        return albumName;
    }

    public void setAlbumName(Long albumName) {
        this.albumName = albumName;
    }


    @Override
    public String toString() {
        return "AlbumVo{" +
                "albumId=" + albumId +
                ", albumName=" + albumName +
                '}';
    }
}
