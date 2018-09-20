package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @author music
 */
@ApiModel(value = "账户信息vo")
public class AccountInfoVo {

    @ApiModelProperty(value = "用户ID")
    private Long accountId;

    @ApiModelProperty(value = "用户账户")
    @NotNull(message = "用户帐户不能为空")
    private String userAccount;

    @ApiModelProperty(value = "角色ID")
    @NotNull(message = "请选择一个角色")
    private Long roleId;

    @ApiModelProperty(value = "角色")
    @NotNull(message = "请选择一个角色")
    private String roleName;

    @ApiModelProperty(value = "联系人")
    @NotNull(message = "联系人必填")
    private String contact;

    @ApiModelProperty(value = "手机号码")
    @NotNull(message = "请输入联系方式")
    private String phoneNumber;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountInfoVo{" +
                "accountId=" + accountId +
                ", userAccount='" + userAccount + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", contact='" + contact + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                '}';
    }
}
