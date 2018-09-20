package com.sound.vo;


import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/29.
 */
public class SystemMsgVo {

    private Long id;

    private Date createTime;//插入时间

    private String content;

    private String title;//标题

    private String imgurl;//图片

    private String imgName;

    private boolean isread;

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

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public boolean isread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

	@Override
	public String toString() {
		return "SystemMsgVo [id=" + id + ", createTime=" + createTime + ", content=" + content + ", title=" + title
				+ ", imgurl=" + imgurl + ", imgName=" + imgName + ", isread=" + isread + "]";
	}
    
}


