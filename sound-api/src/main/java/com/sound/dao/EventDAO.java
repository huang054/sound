package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.Event;

@Repository
public interface EventDAO extends JpaRepository<Event, Long>{
	@Query(value="select e.id,u.header_url from event e join enroll c on e.id=c.event_id join user u on c.user_id=u.id where e.id=?1 order by c.id desc limit 10", nativeQuery = true)
	public List<Object> findEventById(Long id);
	@Query(value = "select e from event e where e.isRecommended=?1 and e.showStatus=1")
	public List<Event> findEvents(boolean b);
	@Query(value="select count(c.id) from event e join enroll c on e.id=c.event_id join user u on c.user_id=u.id where e.id=?1", nativeQuery = true)
	public int findCount(long id);
}
  