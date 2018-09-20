package com.sound.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity(name ="BigVAlbum")
@Table(name = "big_v_album")
public class BigVAlbum implements Serializable{

	/**
	 * 大V专辑
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String bigVName;
	
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String bigVUrl;
	@Column(columnDefinition="bigint(20) DEFAULT 0")
	private long hearingNumber;
	@Column(columnDefinition="varchar(255)  DEFAULT ''")
	private String context;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean isRecommended;
/*	 @OneToMany(mappedBy = "albumId",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	 private List<AudioModel> audios;*/
	
	public boolean isRecommended() {
		return isRecommended;
	}
	public void setRecommended(boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBigVName() {
		return bigVName;
	}
	public void setBigVName(String bigVName) {
		this.bigVName = bigVName;
	}
	
	public String getBigVUrl() {
		return bigVUrl;
	}
	public void setBigVUrl(String bigVUrl) {
		this.bigVUrl = bigVUrl;
	}
	public long getHearingNumber() {
		return hearingNumber;
	}
	public void setHearingNumber(long hearingNumber) {
		this.hearingNumber = hearingNumber;
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
	/*public List<AudioModel> getAudios() {
		return audios;
	}
	public void setAudios(List<AudioModel> audios) {
		this.audios = audios;
	}*/
	@Override
	public String toString() {
		return "BigVAlbum [id=" + id + ", bigVName=" + bigVName + ", bigVUrl=" + bigVUrl + ", hearingNumber="
				+ hearingNumber + ", context=" + context + ", createTime=" + createTime + ", isRecommended="
				+ isRecommended + "]";
	}
	
	

}
