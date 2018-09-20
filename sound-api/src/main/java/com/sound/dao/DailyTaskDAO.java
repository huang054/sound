package com.sound.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.DailyTask;
@Repository
public interface DailyTaskDAO extends JpaRepository<DailyTask, Long>{
     
	@Query("select d from dailyTask d where d.userId=?1")
	public DailyTask findDailyTaskByUserId(long id);
	@Transactional
	@Modifying
	@Query(value="update daily_task u set u.audio_number=0,u.audio_time=0,u.is_beyou_voice=?1,u.is_sharing_audio=?1",nativeQuery=true)
	public void updateDailyTask(boolean b);
}
 