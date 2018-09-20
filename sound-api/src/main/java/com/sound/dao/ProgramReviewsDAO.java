package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.ProgramReviews;

@Repository
public interface ProgramReviewsDAO extends JpaRepository<ProgramReviews, Long>{
 	
	@Query(value = "select u.header_url,u.name,p.context from user u join program_reviews p on u.id=p.user_id where p.audio_id=?1 order by p.time desc limit 5  ", nativeQuery = true)
	public List<Object> findByaudioId(String audioId);

}
 