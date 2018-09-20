package com.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * AccountAuthVO
 * 用于关联查询封装结果
 *
 * @author sunrh
 */
@ApiModel(value = "用户账户信息对象", description = "accountAuthVO")
public class AccountAuthVO {

    /**
     * 自增长ID
     */
    @ApiModelProperty(value = "账户ID", required = true)
    private Long id;

    /**
     * 用户账户
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPasswd;


    /**
     * 默认为1 0为数据逻辑删除状态
     */
    private Byte status;

    /**
     * 资源名称
     */
    private List<String> resourceName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<String> getResourceName() {
        return resourceName;
    }

    public void setResourceName(List<String> resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String toString() {
        return "AccountAuthVO{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", status=" + status +
                ", resourceName='" + resourceName + '\'' +
                '}';
    }
}
