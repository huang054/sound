package com.sound.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sound.model.Device;
 
/**
 * Created by Administrator on 2018/5/3.
 */
@Repository
public interface DeviceDAO extends JpaRepository<Device,Long> {
	
	@Query("select u from device u where u.userId=:userId")
	public Iterable<Device> findByUserId(@Param("userId") String userId);
	
	@Query("select u from device u where u.deviceId=:deviceId")
	public Device findDeviceById(@Param("deviceId") String deviceId);
	
	@Query("delete from device u where u.deviceId=:deviceId")
	@Modifying
	public void unbind(@Param("deviceId") String deviceId);
}
 