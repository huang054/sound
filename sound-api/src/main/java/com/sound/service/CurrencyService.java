package com.sound.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.CurrencyDAO;
import com.sound.model.Currency;

 
@Service
public class CurrencyService extends BaseService<Currency>{
	 
	@Autowired
	private CurrencyDAO currencyDao;
	
	public List<Currency> findCurrencyByAudioId(long id) {
		return currencyDao.findCurrencyByAudioId(id);
	}

}
