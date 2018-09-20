package com.dao.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Admin implements Serializable {
    @Id
    private Long id;

    /**
     * 最后登录IP
     */
    @Column(name = "last_ip")
    private String lastIp;

    /**
     * 最后登录时间
     */
    @Column(name = "last_time")
    private Date lastTime;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 该账号有权操作的菜单
     */
    private String privilege;

    /**
     * 管理员类别
     */
    private String type;

    /**
     * 账户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取最后登录IP
     *
     * @return last_ip - 最后登录IP
     */
    public String getLastIp() {
        return lastIp;
    }

    /**
     * 设置最后登录IP
     *
     * @param lastIp 最后登录IP
     */
    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    /**
     * 获取最后登录时间
     *
     * @return last_time - 最后登录时间
     */
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastTime 最后登录时间
     */
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 获取密码
     *
     * @return passwd - 密码
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * 设置密码
     *
     * @param passwd 密码
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * 获取该账号有权操作的菜单
     *
     * @return privilege - 该账号有权操作的菜单
     */
    public String getPrivilege() {
        return privilege;
    }

    /**
     * 设置该账号有权操作的菜单
     *
     * @param privilege 该账号有权操作的菜单
     */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    /**
     * 获取管理员类别
     *
     * @return type - 管理员类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置管理员类别
     *
     * @param type 管理员类别
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取账户名称
     *
     * @return user_name - 账户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置账户名称
     *
     * @param userName 账户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取联系人
     *
     * @return contact - 联系人
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系人
     *
     * @param contact 联系人
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取联系电话
     *
     * @return phone_number - 联系电话
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置联系电话
     *
     * @param phoneNumber 联系电话
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lastIp=").append(lastIp);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", passwd=").append(passwd);
        sb.append(", privilege=").append(privilege);
        sb.append(", type=").append(type);
        sb.append(", userName=").append(userName);
        sb.append(", contact=").append(contact);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Admin other = (Admin) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLastIp() == null ? other.getLastIp() == null : this.getLastIp().equals(other.getLastIp()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getPrivilege() == null ? other.getPrivilege() == null : this.getPrivilege().equals(other.getPrivilege()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getPhoneNumber() == null ? other.getPhoneNumber() == null : this.getPhoneNumber().equals(other.getPhoneNumber()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLastIp() == null) ? 0 : getLastIp().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getPrivilege() == null) ? 0 : getPrivilege().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getPhoneNumber() == null) ? 0 : getPhoneNumber().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}