package com.sound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.EnrollDAO;
import com.sound.model.Enroll;
 
@Service
public class EnrollService extends BaseService<Enroll>{
    @Autowired
	private EnrollDAO enrollDAO;
	public Enroll findEnroll(long userId,long eventId) {
		return enrollDAO.findEnroll(userId, eventId);
 		
	}
}
