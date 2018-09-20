package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.CommentModel;
 
 
@Repository
public interface CommentDAO extends JpaRepository<CommentModel,Long>{

	@Query(value="select c.id,c.content,c.create_time,u.name,u.header_url from comment c join user u on c.user_id=u.id where c.news_id=?1 order by c.id desc limit ?2,?3", nativeQuery = true)
	public List<Object> findComments(long newsId,int index,int size); 
	@Query(value="select count(c.id) from comment c where c.news_id=?1", nativeQuery = true)
	public int findCount(long newsId);
}
