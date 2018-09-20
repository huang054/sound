package com.sound.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sound.model.RecentAlbumModel;

/** 
 * Created by Administrator on 2018/5/11.
 */
@Repository
public interface RecentAlbumDAO extends JpaRepository<RecentAlbumModel,Long> {
}
 