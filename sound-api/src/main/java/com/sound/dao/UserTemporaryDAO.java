package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.UserTemporary;
@Repository
public interface UserTemporaryDAO extends JpaRepository<UserTemporary, Long>{
 
}
 