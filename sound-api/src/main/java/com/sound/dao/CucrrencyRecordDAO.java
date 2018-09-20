package com.sound.dao;
 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.CucrrencyRecord;
@Repository
public interface CucrrencyRecordDAO extends JpaRepository<CucrrencyRecord, Long>{
	@Query("select c from CurrencyRecord c where c.cucrrencyName=?1 and c.userId=?2 order by c.id desc")
	public List<CucrrencyRecord> findRecord(String name,long userId);
 
}
