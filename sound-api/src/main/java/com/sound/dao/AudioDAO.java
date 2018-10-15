
package com.sound.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.AudioModel;
import com.sound.model.Banner;

import java.util.List;
  
@Repository
public interface AudioDAO extends JpaRepository<AudioModel,Long>{
	
	/*@Query("select u from audio u where u.albumId=:albumId")
	@Modifying
	public List<AudioModel> findByAlbumId(@Param("albumId") Long albumId);

	@Query("select u from audio u")
	public Page<AudioModel> findByPage(Pageable pageable);
*/
    @Query(value="select u.* from audio u where u.title like %?1% or u.author_name like %?1% and u.show_status=1 limit 4", nativeQuery = true)
	public List<AudioModel> searchLimit(String search);
    @Query(value="select u.* from audio u where u.title like %?1% or u.author_name like %?1% and u.show_status=1", nativeQuery = true)
	public List<AudioModel> search(String search);
	
	@Query("select b from Banner b where b.showStatus=1 and b.showArea=1 order by b.sort desc")
	public List<Banner> findBanner();
	
	@Query("select b from Banner b where b.showStatus=1 and b.showArea=2 order by b.sort desc")
	public List<Banner> findBanner1();

/*	@Query("select a from audio a where a.roadShow=1")
	@Modifying
	public List<AudioModel> findAudioByRoadShow();*/
	@Query("select a from audio a where a.isRoadShow=1 and a.showStatus=1 and a.roadShow=4")
	public List<AudioModel> findAudioPriority();
	@Query("select a from audio a where a.roadShow=4 and a.showStatus=1 and a.isRoadShow=2")
	public List<AudioModel> findAudioRoadShow();
	@Query("select a from audio a where a.albumId=?1 and a.showStatus=1 order by a.id ASC")
	public List<AudioModel> findAudioByAlbumId(long id);
	@Query("select a from audio a where a.bigAlbumId=?1 and a.showStatus=1")
	public List<AudioModel> findAudioByBigAlbumId(long id);
	
	@Query("select u from audio u where u.albumId=:albumId")
	@Modifying
	public List<AudioModel> findByAlbumId(@Param("albumId") Long albumId);
}

