package com.sound.service;

import com.sound.dao.DeviceDAO;
import com.sound.model.Device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/5/4.
 */
@Service
public class DeviceService extends BaseService<Device>{
	  
	@Autowired
	private DeviceDAO deviceDao;
	
	public Iterable<Device> findByUserId(String userId){
		return deviceDao.findByUserId(userId);
	}
	public Device findDeviceById(String deviceId) {
		return deviceDao.findDeviceById(deviceId);
	}
	
	public void unbind(String deviceId) {
		deviceDao.unbind(deviceId);
	}
}
