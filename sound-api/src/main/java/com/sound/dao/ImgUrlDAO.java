package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.ImgUrl;

@Repository
public interface ImgUrlDAO extends JpaRepository<ImgUrl, Long>{
 
}
 