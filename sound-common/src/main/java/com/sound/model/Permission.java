package com.sound.model;

import javax.persistence.*;

@Entity(name = "permission")
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="varchar(255) default ''")
    private String permission;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permission=" + permission + ", role=" + role + "]";
	}
    
}