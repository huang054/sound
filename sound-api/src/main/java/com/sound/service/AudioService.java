package com.sound.service;



import com.sound.dao.AudioDAO;
import com.sound.model.AudioModel;
import com.sound.model.Banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

  

@Service
public class AudioService extends BaseService<AudioModel>{

	@Autowired
	private AudioDAO audioDao;

	/*public List<AudioModel> findByAlbumId(@Param("albumId") Long albumId){

		return audioDao.findByAlbumId(albumId);
	}*/

/*	public Page<AudioModel> findByPage(Pageable pageable){
		return audioDao.findByPage(pageable);
	}
*/
	public List<AudioModel> searchLimit(String search){

		return audioDao.searchLimit(search);
	}
	public List<AudioModel> search(String search){

		return audioDao.search(search);
	}
	
	public List<AudioModel> findByAlbumId(@Param("albumId") Long albumId){

		return audioDao.findByAlbumId(albumId);
	}
	public List<Banner> findBanner() {
		return audioDao.findBanner();
	}
	
	public List<Banner> findBanner1() {
		return audioDao.findBanner1();
	}
	

	
	/*public List<AudioModel> findAudioByRoadShow(){
		return audioDao.findAudioByRoadShow();
	}*/
	
	public List<AudioModel> findAudioPriority() {
		return audioDao.findAudioPriority();
	};
	
	public List<AudioModel> findAudioRoadShow() {;
	    return audioDao.findAudioRoadShow();
	}
	
	public List<AudioModel> findAudioByAlbumId(long id){
		return audioDao.findAudioByAlbumId(id);
	}
	
	public List<AudioModel> findAudioByBigAlbumId(long id){
		return audioDao.findAudioByBigAlbumId(id);
	}
	
}
