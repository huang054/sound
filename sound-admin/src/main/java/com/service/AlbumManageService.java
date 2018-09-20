package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.AlbumDetails;
import com.vo.AlbumInfoVo;
import com.vo.AlbumVo;

import java.util.List;

/**
 * @author music
 */
public interface AlbumManageService {

    /**
     * 查询专辑列表
     *
     * @param showStatus 显示状态 1为显示 0为不显示
     * @param albumName  专辑名称
     * @param pageNum    当前页
     * @param showArea   展示区域
     * @return
     */
    ResponseWrap<PageInfo<AlbumInfoVo>> findAlbums(Integer showStatus, String albumName, Integer pageNum, Integer showArea);

    /**
     * 查询专辑信息
     *
     * @param albumId 专辑ID
     * @return
     */
    ResponseWrap<AlbumDetails> findAlbumInfo(Long albumId);

    /**
     * 更新专辑信息
     *
     * @param albumDetails
     * @return
     */
    ResponseWrap<Integer> updateAlbumInfo(AlbumDetails albumDetails);

    /**
     * 插入专辑信息
     *
     * @param albumDetails
     * @return
     */
    ResponseWrap<Integer> insertAlbumInfo(AlbumDetails albumDetails);

    /**
     * 删除专辑信息
     *
     * @param albumId 专辑ID
     * @return
     */
    ResponseWrap<Integer> deleteAlbumInfo(Long albumId);

    /**
     * 获取专辑下拉列表
     *
     * @return
     */
    ResponseWrap<List<AlbumVo>> findAlbumAll();
}
