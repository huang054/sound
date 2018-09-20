package com.sound.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/10.
 */
@Entity(name = "recentAlbum")
@Table(name = "recentAlbum") // 音频专辑表
@ApiModel
public class RecentAlbumModel {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="varchar(64) default ''")
    private String ticket;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//创建时间

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date playdate;//播放时间

    @Column(columnDefinition="varchar(32) NOT null")
    private String userId;

    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long AlbumId;

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

    public Date getPlaydate() {
        return playdate;
    }

    public void setPlaydate(Date playdate) {
        this.playdate = playdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getAlbumId() {
        return AlbumId;
    }

    public void setAlbumId(Long albumId) {
        AlbumId = albumId;
    }

	@Override
	public String toString() {
		return "RecentAlbumModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", playdate="
				+ playdate + ", userId=" + userId + ", AlbumId=" + AlbumId + "]";
	}
    
}
