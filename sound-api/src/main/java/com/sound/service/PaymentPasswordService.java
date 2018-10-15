package com.sound.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.PaymentPasswordDAO;
import com.sound.model.PaymentPassword;

@Service
public class PaymentPasswordService extends BaseService<PaymentPassword>{
	
	@Autowired
	private PaymentPasswordDAO paymentPasswordDAO;

	public PaymentPassword findPaymentPasswordByuserId(long userId) {
		return paymentPasswordDAO.findPaymentPasswordByuserId(userId);
	}
}
