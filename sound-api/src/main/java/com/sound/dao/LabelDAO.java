package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.Label;

@Repository
public interface LabelDAO extends JpaRepository<Label, Long>{
     
	@Query("select u from Label u where u.userId=:userId")
	public List<Label> findLablesByUserId(@Param("userId") long userId);
}
 