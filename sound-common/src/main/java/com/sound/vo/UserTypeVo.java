package com.sound.vo;

import java.util.List;

import com.sound.model.TypeModel;
import com.sound.model.UserTypeModel;

public class UserTypeVo {
	private Iterable<TypeModel> typeModels;
	private UserTypeModel userTypeModel;
	public Iterable<TypeModel> getTypeModels() {
		return typeModels;
	}
	public void setTypeModels(Iterable<TypeModel> typeModels) {
		this.typeModels = typeModels;
	}
	public void setTypeModels(List<TypeModel> typeModels) {
		this.typeModels = typeModels;
	}
	public UserTypeModel getUserTypeModel() {
		return userTypeModel;
	}
	public void setUserTypeModel(UserTypeModel userTypeModel) {
		this.userTypeModel = userTypeModel;
	}
	
}
