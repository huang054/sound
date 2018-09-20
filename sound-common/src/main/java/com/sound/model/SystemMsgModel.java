package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Created by Administrator on 2018/4/29.
 */

@Entity(name="sysmsg")
//系统消息表
@Table(name = "sys_msg")
public class SystemMsgModel {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(hidden = true)
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;//插入时间

    @Column(columnDefinition="text not null")
    @NotBlank(message="消息内容不能为空")
    private String content;

    @Column(columnDefinition="varchar(400) NOT null")
    private String title;//标题

    @ApiModelProperty(hidden = true)
    @Column(columnDefinition="varchar(400) NOT null")
    private String imgurl;//图片

    @Column(columnDefinition="varchar(200) NOT null")
    private String imgName;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean isable;//是否有效
    
    @Column(columnDefinition="tinyint(1) default 1")
	private boolean showStatus;//是否显示1为显示 0为不显示
	
	

	public boolean isShowStatus() {
		return showStatus;
	}

	public void setShowStatus(boolean showStatus) {
		this.showStatus = showStatus;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public boolean isable() {
        return isable;
    }

    public void setIsable(boolean isable) {
        this.isable = isable;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

	@Override
	public String toString() {
		return "SystemMsgModel [id=" + id + ", createTime=" + createTime + ", content=" + content + ", title=" + title
				+ ", imgurl=" + imgurl + ", imgName=" + imgName + ", isable=" + isable + "]";
	}
    
}
