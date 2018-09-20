package com.sound.service;

 
import com.sound.dao.AlbumDAO;
import com.sound.model.Album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
 
@Service
public class AlbumService extends BaseService<Album>{

    @Autowired
    private AlbumDAO albumDAO;

   /* public Page<Album> findByPage(Pageable pageable){
        return albumDAO.findByPage(pageable);
    }
    */
    public void play( long id){
		Album albumModel =  this.findById(id);
		long playCount = albumModel.getPlayCount() + 1;
		albumModel.setPlayCount(playCount);
		this.save(albumModel);
	}
    
	public List<Album> findAlbumByType(String type){
		return albumDAO.findAlbumByType(type);
	}
    
  /*  public Iterable<Album> findAlbumByDispay(){
        return albumDAO.findAlbumByDispay();
    }
*/
   public List<Album> search(String search){

        return albumDAO.search(search);
    }
   
   public List<Album> searchLimit(String search){

       return albumDAO.searchLimit(search);
   }
   
   public List<Album> findBigAlbumByType(boolean b){
	   return albumDAO.findBigAlbumByType(b);
   }
   public List<Album> findBigAlbum(boolean b){
	   return albumDAO.findBigAlbum(b);
   }
}
