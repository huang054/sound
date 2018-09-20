package com.sound.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by Administrator on 2018/5/7.
 */

@Entity(name = "userType") // 用户挺播报的记录表
@Table(name = "user_type")
public class UserTypeModel {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;//主键

    @Column(columnDefinition="varchar(255) NOT null")
    @NotBlank(message="userId不能为空")
    private String userId;

    @NotBlank(message="typeIds不能为空")
    private String typeIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	@Override
	public String toString() {
		return "UserTypeModel [id=" + id + ", userId=" + userId + ", typeIds=" + typeIds + "]";
	}

	
}
