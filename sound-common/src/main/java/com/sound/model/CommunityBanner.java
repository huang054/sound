package com.sound.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name ="communityBanner")
@Table(name = "community_banner")
public class CommunityBanner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String bannerPicUrl;
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String bannerUrl;
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean showStatus;//是否显示1为显示 0为不显示
	

	public boolean isShowStatus() {
		return showStatus;
	}
	public void setShowStatus(boolean showStatus) {
		this.showStatus = showStatus;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBannerPicUrl() {
		return bannerPicUrl;
	}
	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "CommunityBanner [id=" + id + ", bannerPicUrl=" + bannerPicUrl + ", bannerUrl=" + bannerUrl
				+ ", createTime=" + createTime + "]";
	}
	
	

}
