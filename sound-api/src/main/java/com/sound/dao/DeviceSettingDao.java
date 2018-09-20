package com.sound.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.DeviceSetting;

@Repository
public interface DeviceSettingDao extends JpaRepository<DeviceSetting,Long> {
 
}
 