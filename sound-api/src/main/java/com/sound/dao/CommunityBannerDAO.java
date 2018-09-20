package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.CommunityBanner;
  
@Repository
public interface CommunityBannerDAO extends JpaRepository<CommunityBanner, Long>{

}
