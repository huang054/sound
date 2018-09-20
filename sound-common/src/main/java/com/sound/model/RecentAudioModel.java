package com.sound.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/5/10.
 */

@Entity(name = "recentAudio")
@Table(name = "recentAudio",
        uniqueConstraints = {@UniqueConstraint(columnNames={"userId", "audioId"})})// 音频专辑表
@ApiModel
public class RecentAudioModel {

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

    @Column(columnDefinition="varchar(20) NOT null")
    private String userId;

    @Column(columnDefinition="bigint(20) DEFAULT 0")
    private Long audioId;

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

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

	@Override
	public String toString() {
		return "RecentAudioModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", playdate="
				+ playdate + ", userId=" + userId + ", audioId=" + audioId + "]";
	}
    
}
