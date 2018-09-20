package com.sound.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.sound.dao.CollectionDao;
import com.sound.model.Collection;
import com.sound.vo.AlbumCollection;
import com.sound.vo.AudioCollection;
 
@Service
public class CollectionService extends BaseService<Collection>{
	
	@Autowired
	private CollectionDao collectionDao;
	 
	public List<AudioCollection> findAudioCollectionByUserId(long userId){
		 List<AudioCollection> audios= new ArrayList<>();
		 
		List<Object> objs =collectionDao.findAudioCollectionByUserId(userId);
	    
		AudioCollection audio=null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		for (Object obj:objs) {
			audio = new AudioCollection();
			Object[] rowArray = (Object[])obj;
			audio.setId(Long.parseLong(rowArray[0].toString()));
			audio.setAudioId(Long.parseLong(rowArray[1].toString()));
			audio.setTitle(rowArray[2].toString());
			audio.setContext(rowArray[3].toString());
			audio.setPicUrl(rowArray[4].toString());
			try {
				audio.setTime(sdf.parse(rowArray[5].toString()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			audios.add(audio);
		}
		
		return audios;
	}
	
	public List<AlbumCollection> findAlbumCollectionByUserId(long userId) throws ParseException{
		
		 List<AlbumCollection> albums= new ArrayList<>();
			List<Object> objs =collectionDao.findAlbumCollectionByUserId(userId);
			AlbumCollection album=null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
			for (Object obj:objs) {
				album = new AlbumCollection();
				Object[] rowArray = (Object[])obj;
				album.setId(Long.parseLong(rowArray[0].toString()));
				album.setAlbumId(Long.parseLong(rowArray[1].toString()));
				album.setTitle(rowArray[2].toString());
				album.setContext(rowArray[3].toString());
				album.setPicUrl(rowArray[4].toString());
				album.setTime(sdf.parse(rowArray[5].toString()));
				album.setCount(Integer.parseInt(rowArray[6].toString().toString()));
				album.setBigVAlbum(Boolean.parseBoolean(rowArray[7].toString()));
				albums.add(album);
			}
			
			return albums;
	}
		
	 public Collection iSCollection(long userId,long albumId) {
		 return collectionDao.iSCollection(userId, albumId);
	 }
	 
	 public void deleteCollectionByAlbumId(long userId,List<Long> albumId) {
		collectionDao.deleteCollectionByAlbumId(userId, albumId);
	 }
	 public void deleteCollectionByAudioId(long userId,List<Long> albumId) {
		 collectionDao.deleteCollectionByAudioId(userId, albumId);
	 }
	  public Collection iSAudioCollection(long userId,long albumId) {
		  return collectionDao.iSAudioCollection(userId, albumId);
	  }
}
