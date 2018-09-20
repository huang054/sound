package com.dao.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "admin_gr")
public class AdminGr implements Serializable {
    /**
     * ID
     */
    @Id
    @Column(name = "ID")
    private Long id;

    /**
     * 组ID
     */
    @Column(name = "GID")
    private Long gid;

    /**
     * 资源ID
     */
    @Column(name = "RID")
    private Long rid;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return ID - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取组ID
     *
     * @return GID - 组ID
     */
    public Long getGid() {
        return gid;
    }

    /**
     * 设置组ID
     *
     * @param gid 组ID
     */
    public void setGid(Long gid) {
        this.gid = gid;
    }

    /**
     * 获取资源ID
     *
     * @return RID - 资源ID
     */
    public Long getRid() {
        return rid;
    }

    /**
     * 设置资源ID
     *
     * @param rid 资源ID
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", gid=").append(gid);
        sb.append(", rid=").append(rid);
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
        AdminGr other = (AdminGr) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
            && (this.getRid() == null ? other.getRid() == null : this.getRid().equals(other.getRid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
        result = prime * result + ((getRid() == null) ? 0 : getRid().hashCode());
        return result;
    }
}