package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;

@ApiModel(value = "更新资源vo")
public class UpdateRecVo {

    @ApiModelProperty(value = "资源信息")
    private ArrayList<RoleInfoVo> roleInfoVos;

    public ArrayList<RoleInfoVo> getRoleInfoVos() {
        return roleInfoVos;
    }

    public void setRoleInfoVos(ArrayList<RoleInfoVo> roleInfoVos) {
        this.roleInfoVos = roleInfoVos;
    }

    @Override
    public String toString() {
        return "UpdateRecVo{" +
                "roleInfoVos=" + roleInfoVos +
                '}';
    }
}
