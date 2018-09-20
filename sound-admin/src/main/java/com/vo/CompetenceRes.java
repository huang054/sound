package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author music
 */
@ApiModel(value = "权限资源信息")
public class CompetenceRes {

    @ApiModelProperty(value = "权限资源ID")
    private Long resId;

    @ApiModelProperty(value = "权限名称")
    private String resName;

    @ApiModelProperty(value = "权限描述")
    private String resDescription;

    @ApiModelProperty(value = "权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理")
    private Integer area;

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResDescription() {
        return resDescription;
    }

    public void setResDescription(String resDescription) {
        this.resDescription = resDescription;
    }

    @Override
    public String toString() {
        return "CompetenceRes{" +
                "resId=" + resId +
                ", resName='" + resName + '\'' +
                ", resDescription='" + resDescription + '\'' +
                ", area=" + area +
                '}';
    }
}
