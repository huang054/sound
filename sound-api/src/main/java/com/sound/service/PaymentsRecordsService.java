package com.sound.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sound.dao.PaymentsRecordsDAO;
import com.sound.model.PaymentsRecords;
import com.sound.vo.Charts;
 
@Service
public class PaymentsRecordsService extends BaseService<PaymentsRecords>{
 
	@Autowired
	private PaymentsRecordsDAO payDAO;
	
	public PaymentsRecords save(PaymentsRecords p) {
		return payDAO.save(p);
	}
	
	public List<Charts> findChartsByPayments(){
		List<Charts> charts= new ArrayList<>();
		List<Object> objs = payDAO.findByPayments();
		Charts c=null;
		for (Object s :objs) {

		    Object[] rowArray = (Object[])s;
			c= new Charts();
			c.setName(rowArray[0].toString());
			c.setUserLevel(Integer.parseInt(rowArray[1].toString()));
			c.setPayments(new BigDecimal(rowArray[2].toString()));
			c.setUrl(rowArray[3].toString());
			charts.add(c);
		}
		return charts;
	}
}
