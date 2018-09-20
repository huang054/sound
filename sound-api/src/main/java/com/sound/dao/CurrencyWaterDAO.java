package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.CurrencyWater;
@Repository
public interface CurrencyWaterDAO extends JpaRepository<CurrencyWater, Long>{
  
}
