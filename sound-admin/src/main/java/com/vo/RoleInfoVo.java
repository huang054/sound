package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author music
 */
@ApiModel(value = "角色信息")
public class RoleInfoVo {

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色权限资源")
    private List<CompetenceRes> competenceRes;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<CompetenceRes> getCompetenceRes() {
        return competenceRes;
    }

    public void setCompetenceRes(List<CompetenceRes> competenceRes) {
        this.competenceRes = competenceRes;
    }

    @Override
    public String toString() {
        return "RoleInfoVo{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", competenceRes=" + competenceRes +
                '}';
    }
}
