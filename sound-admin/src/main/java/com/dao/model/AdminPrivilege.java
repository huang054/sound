package com.dao.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "admin_privilege")
public class AdminPrivilege implements Serializable {
    /**
     * 权限组ID
     */
    @Id
    private Long id;

    /**
     * 用户ID
     */
    @Column(name = "admin_id")
    private Long adminId;

    /**
     * 资源ID
     */
    @Column(name = "group_id")
    private Long groupId;

    private static final long serialVersionUID = 1L;

    /**
     * 获取权限组ID
     *
     * @return id - 权限组ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置权限组ID
     *
     * @param id 权限组ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return admin_id - 用户ID
     */
    public Long getAdminId() {
        return adminId;
    }

    /**
     * 设置用户ID
     *
     * @param adminId 用户ID
     */
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取资源ID
     *
     * @return group_id - 资源ID
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置资源ID
     *
     * @param groupId 资源ID
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", adminId=").append(adminId);
        sb.append(", groupId=").append(groupId);
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
        AdminPrivilege other = (AdminPrivilege) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        return result;
    }
}