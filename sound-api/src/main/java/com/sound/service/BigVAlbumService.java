package com.sound.service;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sound.dao.BigVAlbumDAO;
import com.sound.model.BigVAlbum;

@Service
public class BigVAlbumService extends BaseService<BigVAlbum>{
 
	@Autowired
	private BigVAlbumDAO bigDao;
	
	public List<BigVAlbum> findEvents(boolean b){
		
		return bigDao.findEvents(b);
	}
}
