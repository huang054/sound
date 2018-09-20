package com.sound.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity (name = "type")
// 类别表
@Table(name = "sound_type")
public class TypeModel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(columnDefinition="varchar(255) default ''")
	private String ticket;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//插入时间
    

	@NotBlank(message="类别名不能为空")
    private String name;//类别名

	@NotNull(message="归属哪个栏目")
    private Long cloId;//栏目ID

	@Column(columnDefinition="varchar(255) default ''")
	private String logoUrl;

	@Column(columnDefinition="varchar(255) default ''")
	private String logoName;
    
    @Column(columnDefinition="varchar(255)")
	@ApiModelProperty(hidden = true)
    private String imgMinUrl;//类型图片
    

   	@NotNull(message="归属哪个大类型")
    private Long bigTypeId;//大类型ID

	@Column(columnDefinition="varchar(255) default ''")
    private String bigTypeName;//大类型名称

	@Column(columnDefinition="int(11) default 0")
    private Integer priority;//推荐
    

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getBigTypeId() {
		return bigTypeId;
	}

	public String getBigTypeName() {
		return bigTypeName;
	}

	public void setBigTypeName(String bigTypeName) {
		this.bigTypeName = bigTypeName;
	}

	public void setBigTypeId(Long bigTypeId) {
		this.bigTypeId = bigTypeId;
	}

	public String getImgMinUrl() {
		return imgMinUrl;
	}

	public void setImgMinUrl(String imgMinUrl) {
		this.imgMinUrl = imgMinUrl;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCloId() {
		return cloId;
	}

	public void setCloId(Long cloId) {
		this.cloId = cloId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	@Override
	public String toString() {
		return "TypeModel [id=" + id + ", ticket=" + ticket + ", createTime=" + createTime + ", name=" + name
				+ ", cloId=" + cloId + ", logoUrl=" + logoUrl + ", logoName=" + logoName + ", imgMinUrl=" + imgMinUrl
				+ ", bigTypeId=" + bigTypeId + ", bigTypeName=" + bigTypeName + ", priority=" + priority + "]";
	}
	
}
