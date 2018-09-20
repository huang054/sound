package com.sound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.DailyTaskDAO;
import com.sound.model.DailyTask;
@Service
public class DailyTaskService extends BaseService<DailyTask>{
  
	@Autowired
	private DailyTaskDAO dailyTaskDao;
	public DailyTask findDailyTaskByUserId(long id) {
		return dailyTaskDao.findDailyTaskByUserId(id);
	}
	public void updateDailyTask(boolean b) {
		dailyTaskDao.updateDailyTask(b);
	}
}

