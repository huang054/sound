package com.sound.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.EventDAO;
import com.sound.model.Event;
import com.sound.vo.EventVo;
@Service
public class EventService extends BaseService<Event>{
 	
	@Autowired
	private EventDAO eventDao;
   
	public EventVo findEventById(Long id) throws ParseException {
		
		List<Object> objs= eventDao.findEventById(id);
		EventVo event = new EventVo();
		List<String> s = new ArrayList<String>();
		if(null==objs||objs.size()==0) {
			return null;
		}
		for(Object obj:objs) {
		Object[] rowArray = (Object[])obj;
	
	    s.add(rowArray[1].toString());
		
		}
		event.setImgs(s);
		return event;
		
	}
		
	public List<Event> findEvents(boolean b){
		return eventDao.findEvents(b);
	}
	
	public int findCount(long id) {
		return eventDao.findCount(id);
	}
}
