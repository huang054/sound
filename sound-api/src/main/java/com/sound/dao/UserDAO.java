package com.sound.dao;


import java.math.BigDecimal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.UserModel;

/**
 * Created by Administrator on 2018/4/28.
 */
@Repository
public interface UserDAO extends JpaRepository<UserModel, Long> {
 
	/*@Query("select u from userinfo u where u.userId=:userId and u.password=:password")
	@Modifying
	public UserModel login(@Param("userId") String userId, @Param("password") String password);
*/
	@Query("select u from userinfo u where u.phoneNum=:userId")
	public UserModel getByUserName(@Param("userId") String userId);
	
	@Query("select u from userinfo u where u.userToken=:token")
	public UserModel getByUserToken(@Param("token") String token);
	
	@Query("select count(*) from userinfo")
	public long getUserNum();
 
	@Query("select u from userinfo u where u.nowDeviceId=:nowDeviceId")
	public Iterable<UserModel> findByDeviceId(@Param("nowDeviceId") String nowDeviceId);
	@Transactional
	@Modifying
	@Query("update userinfo u set u.userToken=:token where u.phoneNum=:phone")
	public void updateTokenByPhone(@Param("token") String token,@Param("phone") String phone);
	
	@Transactional
	@Modifying
	@Query("update userinfo u set u.isSignIn=0")
	public void updateSignIn();
	
	@Transactional
	@Modifying
	@Query("update userinfo u set u.yesterdayIsSignIn=0,u.signInTotal=0 where u.isSignIn=0")
	public void updateYestodaySignIn();
	@Query(value="select count(u.calculating_force_value) from user u where u.calculating_force_value >?1",nativeQuery=true)
	public long findUserCount(int i);

	@Query(value="select count(u.invite_user_id) from user u where u.invite_user_id=?1",nativeQuery=true)
	public int inviteUser(long id);
}
