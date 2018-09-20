package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sound.model.Collection;
 
@Repository
public interface CollectionDao extends JpaRepository<Collection, Long>{
	@Query(value = "select c.pid,a.id,a.title,a.remark,a.pic_url,a.create_time from collection c join audio a on c.audio_id=a.id where c.user_id=:userId", nativeQuery = true)
	public List<Object> findAudioCollectionByUserId(@Param("userId") long userId);
	 
	@Query(value = "select c.pid,a.id,a.name,a.summary,a.img_min_url,a.create_time,a.play_count,a.is_bigvalbum from collection c join album a on c.album_id=a.id where c.user_id=:userId", nativeQuery = true)
	public List<Object> findAlbumCollectionByUserId(@Param("userId") long userId);
	@Query("select c from Collection c where c.userId=?1 and c.albumId=?2")
     public Collection iSCollection(long userId,long albumId);
	@Modifying
	@Transactional
	@Query("delete from Collection c where c.userId=?1 and c.albumId in(?2)")
    public void deleteCollectionByAlbumId(long userId,List<Long> ids);
	
	@Modifying
	@Transactional
	@Query("delete from Collection c where c.userId=?1 and c.audioId in(?2)")
    public void deleteCollectionByAudioId(long userId,List<Long> albumId);
	@Query("select c from Collection c where c.userId=?1 and c.audioId=?2")
    public Collection iSAudioCollection(long userId,long albumId);
}
