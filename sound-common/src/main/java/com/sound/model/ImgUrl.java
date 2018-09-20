package com.sound.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity(name ="imgUrl")
@Table(name = "img_url")
public class ImgUrl implements Serializable{
	
	/**
	 * 小链频道图片
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String RecommendUrl;//推荐
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String LiveUrl;//轻路演
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String InformationUrl;//资讯
	@Column(columnDefinition="varchar(64)  DEFAULT ''")
	private String chornelUrl;//喜马拉雅
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getChornelUrl() {
		return chornelUrl;
	}

	public void setChornelUrl(String chornelUrl) {
		this.chornelUrl = chornelUrl;
	}

	public String getRecommendUrl() {
		return RecommendUrl;
	}

	public void setRecommendUrl(String recommendUrl) {
		RecommendUrl = recommendUrl;
	}

	public String getLiveUrl() {
		return LiveUrl;
	}

	public void setLiveUrl(String liveUrl) {
		LiveUrl = liveUrl;
	}

	public String getInformationUrl() {
		return InformationUrl;
	}

	public void setInformationUrl(String informationUrl) {
		InformationUrl = informationUrl;
	}

	@Override
	public String toString() {
		return "ImgUrl [id=" + id + ", RecommendUrl=" + RecommendUrl + ", LiveUrl=" + LiveUrl + ", InformationUrl="
				+ InformationUrl + ", chornelUrl=" + chornelUrl + "]";
	}
	
	
}
