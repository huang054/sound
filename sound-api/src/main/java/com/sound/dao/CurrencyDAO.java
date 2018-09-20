package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.Currency;

@Repository
public interface CurrencyDAO extends JpaRepository<Currency, Long>{
  
	@Query("select u from currencyInfo u where u.audioId=?1")
	public List<Currency> findCurrencyByAudioId(long id);
}
