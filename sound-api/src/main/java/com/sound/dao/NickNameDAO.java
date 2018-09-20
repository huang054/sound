package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.NickName;

@Repository
public interface NickNameDAO extends JpaRepository<NickName, Long>{
 
}
 