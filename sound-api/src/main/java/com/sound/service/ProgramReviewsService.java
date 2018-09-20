package com.sound.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.ProgramReviewsDAO;
import com.sound.model.ProgramReviews;
import com.sound.vo.Charts;
import com.sound.vo.Discuss;

@Service
public class ProgramReviewsService extends BaseService<ProgramReviews>{
	 
	@Autowired
	private ProgramReviewsDAO programDao;
	public List<Discuss> findByaudioId(String audioId){
		List<Discuss> discuss = new ArrayList<>();
		 
		List<Object> objs = programDao.findByaudioId(audioId);
		Discuss c=null;
		for (Object s :objs) {

		    Object[] rowArray = (Object[])s;
			c= new Discuss();
			c.setUrl(rowArray[0].toString());
			c.setName(rowArray[1].toString());
			c.setContect(rowArray[2].toString());
			
			discuss.add(c);
		}
		return discuss;
	}
}
