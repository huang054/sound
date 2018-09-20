package com.dao.mapper;

import com.dao.model.Album;
import com.dao.util.MyMapper;
import com.vo.AlbumInfoVo;
import com.vo.AlbumVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author music
 */
@Repository
public interface AlbumMapper extends MyMapper<Album> {


    /**
     * 查询专辑列表
     *
     * @param showStatus 显示状态 1为显示 0为不显示
     * @param albumName  专辑名称
     * @param showArea   展示区域
     * @return
     */
    List<AlbumInfoVo> findAlbums(@Param(value = "showStatus") Integer showStatus,
                                 @Param(value = "showArea") Integer showArea,
                                 @Param(value = "albumName") String albumName);

    /**
     * 获取专辑下拉列表
     *
     * @return
     */
    List<AlbumVo> findAlbumAll();

    /**
     * 查询专辑
     *
     * @param showArea 展示区域 1：大v
     * @return
     */
    List<Long> findAlbumByShowArea(Integer showArea);
}