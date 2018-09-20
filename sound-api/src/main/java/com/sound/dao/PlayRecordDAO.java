package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.PlayRecord;
@Repository
public interface PlayRecordDAO extends JpaRepository<PlayRecord, Long>{
 
	@Query("select p from playRecord p where p.userId=?1 and p.audioId=?2")
	public PlayRecord find(long userId,long audioId);
}
 