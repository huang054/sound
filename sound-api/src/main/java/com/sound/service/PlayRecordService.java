package com.sound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.PlayRecordDAO;
import com.sound.model.PlayRecord;
@Service
public class PlayRecordService extends BaseService<PlayRecord>{
	
	@Autowired
	private PlayRecordDAO playDao;
	  
	public PlayRecord find(long userId,long audioId) {
		return playDao.find(userId, audioId);
	}
		

}
