package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.AlbumMapper;
import com.dao.model.Album;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.utils.util.TimeUtil;
import com.vo.AlbumDetails;
import com.vo.AlbumInfoVo;
import com.vo.AlbumVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author music
 */
@Service
public class AlbumManageServiceImpl implements AlbumManageService {

    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询专辑列表
     *
     * @param showStatus 显示状态 1为显示 0为不显示
     * @param albumName  专辑名称
     * @param pageNum    当前页
     * @param showArea   展示区域
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<AlbumInfoVo>> findAlbums(Integer showStatus, String albumName, Integer pageNum, Integer showArea) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<AlbumInfoVo> albumInfoVos = albumMapper.findAlbums(showStatus, showArea, albumName);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<AlbumInfoVo>) albumInfoVos).toPageInfo());
    }

    /**
     * 查询专辑信息
     *
     * @param albumId 专辑ID
     * @return
     */
    @Override
    public ResponseWrap<AlbumDetails> findAlbumInfo(Long albumId) {
        Album album = albumMapper.selectByPrimaryKey(albumId);
        if (album == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1003);
        }
        AlbumDetails albumDetails = new AlbumDetails();
        albumDetails.setAlbumId(album.getId());
        albumDetails.setAlbumName(album.getName());
        albumDetails.setRecommendLevel(album.getType());
        albumDetails.setShowStatus(album.getShowStatus());
        albumDetails.setAlbumIntroduction(album.getSummary());
        albumDetails.setImgUrl(album.getImgMinUrl());
        return new ResponseWrap<>(ResponseCode.CODE_200, albumDetails);
    }

    /**
     * 更新专辑信息
     *
     * @param albumDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateAlbumInfo(AlbumDetails albumDetails) {
        if (albumDetails.getAlbumId() == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        Album album = new Album();
        album.setId(albumDetails.getAlbumId());
        album.setName(albumDetails.getAlbumName());
        album.setSummary(albumDetails.getAlbumIntroduction());
        album.setImgMinUrl(albumDetails.getImgUrl());
        album.setShowStatus(albumDetails.getShowStatus());
        album.setType(albumDetails.getRecommendLevel());
        return new ResponseWrap<>(ResponseCode.CODE_200, albumMapper.updateByPrimaryKeySelective(album));
    }

    /**
     * 插入专辑信息
     *
     * @param albumDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertAlbumInfo(AlbumDetails albumDetails) {
        Album album = new Album();
        album.setId(albumDetails.getAlbumId());
        album.setName(albumDetails.getAlbumName());
        album.setSummary(albumDetails.getAlbumIntroduction());
        album.setImgMinUrl(albumDetails.getImgUrl());
        album.setShowStatus(albumDetails.getShowStatus());
        album.setType(albumDetails.getRecommendLevel());
        album.setCreateTime(new Date());
        album.setTitle("");
        album.setPlayCount(0L);
        album.setAudioCount(0L);
        album.setIsBigvalbum(albumDetails.getIsBigVAlbum());
        return new ResponseWrap<>(ResponseCode.CODE_200, albumMapper.insert(album));
    }

    /**
     * 删除专辑信息
     *
     * @param albumId 专辑ID
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteAlbumInfo(Long albumId) {
        if (albumId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        return new ResponseWrap<>(ResponseCode.CODE_200, albumMapper.deleteByPrimaryKey(albumId));
    }

    /**
     * 获取专辑下拉列表
     *
     * @return
     */
    @Override
    public ResponseWrap<List<AlbumVo>> findAlbumAll() {
        List<AlbumVo> albumVos = albumMapper.findAlbumAll();
        return new ResponseWrap<>(ResponseCode.CODE_200, albumVos);
    }
}
