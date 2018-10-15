package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.Album;
import com.sound.model.Currency;
import com.sound.model.PaymentPassword;
@Repository
public interface PaymentPasswordDAO extends JpaRepository<PaymentPassword, Long>{
	
	@Query("select u from PaymentPassword u where u.userId=?1")
	public PaymentPassword findPaymentPasswordByuserId(long userId);

}
