package com.sound.dao;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.Album;
   
@Repository
public interface AlbumDAO extends JpaRepository<Album, Long>{

  /*  @Query("select u from album u")
    public Page<Album> findByPage(Pageable pageable);
    
    @Query("select u from album u where u.display=true")
    public Iterable<Album> findAlbumByDispay();*/

    @Query(value="select u.* from album u where u.title like %?1% or u.author_name like %?1% and u.show_status=1 limit 4", nativeQuery = true)
    public List<Album> searchLimit(String search);
    @Query(value="select u.* from album u where u.title like %?1% or u.author_name and u.show_status=1 like %?1%", nativeQuery = true)
    public List<Album> search(String search);
	
	@Query("select a from Album a where a.type=?1 and a.showStatus=1")
	@Modifying
	public List<Album> findAlbumByType(String type);
	
	@Query("select a from Album a where a.isBigVAlbum=?1 and a.showStatus=1")
	@Modifying
	public List<Album> findBigAlbumByType(boolean b);
	@Query("select a from Album a where a.isBigVAlbum=?1 and a.type=?1 and a.showStatus=1")
	@Modifying
	public List<Album> findBigAlbum(boolean b);
}
