package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

//推荐表
@Entity(name="sysRecommend")
@Table(name="sysRecommend")
public class SystemRecommend{

	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition="Int(11) default 0")
	private Integer orders;//推荐排序

	@Column(columnDefinition="Int(11) default 0")
	private Integer priority;

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="varchar(255) NOT null")
	private String bigimgUrl;

	@Column(columnDefinition="varchar(255) NOT null")
	private String bigImgName;

	@Column(columnDefinition="Int(11) default 0")
	private Long albumId;

	@Column(columnDefinition="tinyint(1) default 0")
	private boolean isable;//是否有效

	@ApiModelProperty(hidden = true)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;//创建时间
	
	@Column(columnDefinition="Int(11) default 0")
	private Long audioId; //节目id
	
	@Column(columnDefinition="Int(11) default 0")
	private Long typeId;//类型id

	public Long getAudioId() {
		return audioId;
	}

	public void setAudioId(Long audioId) {
		this.audioId = audioId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getBigimgUrl() {
		return bigimgUrl;
	}

	public void setBigimgUrl(String bigimgUrl) {
		this.bigimgUrl = bigimgUrl;
	}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean isable() {
		return isable;
	}

	public void setIsable(boolean isable) {
		this.isable = isable;
	}

	public String getBigImgName() {
		return bigImgName;
	}

	public void setBigImgName(String bigImgName) {
		this.bigImgName = bigImgName;
	}
}
