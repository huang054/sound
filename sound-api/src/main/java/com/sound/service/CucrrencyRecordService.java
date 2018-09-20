package com.sound.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.CucrrencyRecordDAO;
import com.sound.model.CucrrencyRecord;
@Service
public class CucrrencyRecordService extends BaseService<CucrrencyRecord>{
  
	@Autowired
	private CucrrencyRecordDAO cucrrencyRecordDao;
	
	public List<CucrrencyRecord> findRecord(String name,long userId){
		return cucrrencyRecordDao.findRecord(name, userId);
	}
}
