package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.BigVAlbum;

  
@Repository
public interface BigVAlbumDAO extends JpaRepository<BigVAlbum, Long>{

	@Query(value = "select e from BigVAlbum e where e.isRecommended=?1")
	public List<BigVAlbum> findEvents(boolean b);
}
