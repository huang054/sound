package com.dao.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "admin_res")
public class AdminRes implements Serializable {
    /**
     * 资源表ID
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 资源名称
     */
    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源描述
     */
    @Column(name = "RESOURCE_COMT")
    private String resourceComt;

    /**
     * 权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理
     */
    @Column(name = "AREA")
    private Integer area;

    private static final long serialVersionUID = 1L;

    /**
     * 获取资源表ID
     *
     * @return ID - 资源表ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置资源表ID
     *
     * @param id 资源表ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取资源名称
     *
     * @return RESOURCE_NAME - 资源名称
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * 设置资源名称
     *
     * @param resourceName 资源名称
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取资源描述
     *
     * @return RESOURCE_COMT - 资源描述
     */
    public String getResourceComt() {
        return resourceComt;
    }

    /**
     * 设置资源描述
     *
     * @param resourceComt 资源描述
     */
    public void setResourceComt(String resourceComt) {
        this.resourceComt = resourceComt;
    }

    /**
     * 获取权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理
     *
     * @return AREA - 权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理
     */
    public Integer getArea() {
        return area;
    }

    /**
     * 设置权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理
     *
     * @param area 权限区域 1：首页 2：用户管理 3：设备管理 4：币种管理 5：音频管理 6：系统管理
     */
    public void setArea(Integer area) {
        this.area = area;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", resourceName=").append(resourceName);
        sb.append(", resourceComt=").append(resourceComt);
        sb.append(", area=").append(area);
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
        AdminRes other = (AdminRes) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getResourceName() == null ? other.getResourceName() == null : this.getResourceName().equals(other.getResourceName()))
            && (this.getResourceComt() == null ? other.getResourceComt() == null : this.getResourceComt().equals(other.getResourceComt()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getResourceName() == null) ? 0 : getResourceName().hashCode());
        result = prime * result + ((getResourceComt() == null) ? 0 : getResourceComt().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        return result;
    }
}