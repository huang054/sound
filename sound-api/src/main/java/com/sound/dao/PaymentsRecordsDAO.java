package com.sound.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sound.model.PaymentsRecords;



@Repository
public interface PaymentsRecordsDAO extends JpaRepository<PaymentsRecords, Long>{
 	
	@Query(value = "select u.name,u.user_level,sum(p.payments) c,u.header_url from user u inner join payments_records p on p.user_id=u.id where p.currency_name='LXB'  group by p.user_id order by c DESC limit 10", nativeQuery = true)
	public List<Object> findByPayments();
/*	@Query(value = "select  p.currency_name,sum(p.payments) c from user u inner join payments_records p on p.user_id=u.id where p.user_id=?1 group by p.currency_name ", nativeQuery = true)
	public List<Object> findUserPayments(long userId);*/
}
 