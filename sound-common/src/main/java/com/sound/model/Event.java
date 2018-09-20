package com.sound.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name ="event")
@Table(name = "event")
public class Event implements Serializable{

	/**
	 * 线下活动
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition="varchar(256)  DEFAULT ''" )
	private String context;//活动的内容
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;//创建时间
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String address;//活动地址
	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date eventTime;//活动时间
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String phone;//联系人电话
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String contextUrl;//内容
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String imgUrl;//封面
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean isRecommended;//是否推荐
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String authorName;//作者名字
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String url;//内容插图
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String title;//活动标题
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean showStatus;//是否显示1为显示 0为不显示
	
	public boolean isShowStatus() {
		return showStatus;
	}
	public void setShowStatus(boolean showStatus) {
		this.showStatus = showStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContextUrl() {
		return contextUrl;
	}
	public void setContextUrl(String contextUrl) {
		this.contextUrl = contextUrl;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public boolean isRecommended() {
		return isRecommended;
	}
	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", context=" + context + ", createTime=" + createTime + ", address=" + address
				+ ", eventTime=" + eventTime + ", phone=" + phone + ", contextUrl=" + contextUrl + ", imgUrl=" + imgUrl
				+ ", isRecommended=" + isRecommended + ", authorName=" + authorName + ", url=" + url + "]";
	}
	
	

}
