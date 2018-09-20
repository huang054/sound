package com.sound.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.LabelDAO;
import com.sound.model.Label;

@Service
public class LabelService extends BaseService<Label>{
 	 
	@Autowired
	private LabelDAO labelDao;
	
	public List<Label> findLabelsByUserId(long userId){
		return labelDao.findLablesByUserId(userId);
	}

}
