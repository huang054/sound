package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.Enroll;

@Repository
public interface EnrollDAO extends JpaRepository<Enroll, Long>{
     
	@Query(value = "select e from enroll e where e.userId=?1 and e.eventId=?2")
	public Enroll findEnroll(long userId,long eventId);
}
 