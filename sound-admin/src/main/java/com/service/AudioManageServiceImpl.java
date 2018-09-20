package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.AlbumMapper;
import com.dao.mapper.AudioMapper;
import com.dao.model.Album;
import com.dao.model.Audio;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vo.AudioDetails;
import com.vo.AudioInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author music
 */
@Service
public class AudioManageServiceImpl implements AudioManageService {

    @Autowired
    private AudioMapper audioMapper;
    @Autowired
    private AlbumMapper albumMapper;

    /**
     * 查询音频列表
     *
     * @param showStatus 显示状态
     * @param audioName  音频名称
     * @param pageNum    当前页
     * @param showArea   展示区域
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<AudioInfoVo>> findAudios(Integer showStatus, String audioName, Integer pageNum, Integer showArea) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<AudioInfoVo> audioInfoVos = audioMapper.findAudios(showArea, showStatus, audioName);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<AudioInfoVo>) audioInfoVos).toPageInfo());
    }

    /**
     * 查询音频信息
     *
     * @param audioId 音频ID
     * @return
     */
    @Override
    public ResponseWrap<AudioDetails> findAudioInfo(Long audioId) {
        if (audioId == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }

        Audio audio = audioMapper.selectByPrimaryKey(audioId);
        if (audio == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1003);
        }
        AudioDetails audioDetails = new AudioDetails();
        audioDetails.setAudioId(audio.getId());
        audio.setId(audioDetails.getAudioId());
        audioDetails.setAudioTitle(audio.getTitle());
        audioDetails.setAuthorName(audio.getAuthorName());
        audioDetails.setAlbumId(audio.getAlbumId());
        audioDetails.setAlbumName(audio.getAlbumName());
        audioDetails.setShowStatus(audio.getShowStatus());
        audioDetails.setRecommendLevel(audio.getRoadShow());
        audioDetails.setAudioFileName(audio.getFileName());
        audioDetails.setAudioFileSize(audio.getFileSize());
        audioDetails.setAudioUrl(audio.getFilepath());
        audioDetails.setAudioFileMd5(audio.getMd5());
        audioDetails.setAudioTime(audio.getDurantionSec());
        audioDetails.setAudioImgUrl(audio.getPicUrl());
        audioDetails.setPlayImgUrl(audio.getImgUrl());
        // TODO: 2018/8/30 查询币配置
        return new ResponseWrap<>(ResponseCode.CODE_200, audioDetails);


    }

    /**
     * 更新音频信息
     *
     * @param audioDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateAudioInfo(AudioDetails audioDetails) {
        if (audioDetails.getAudioId() == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1001);
        }
        Audio audio = new Audio();
        audio.setId(audioDetails.getAudioId());
        audio.setTitle(audioDetails.getAudioTitle());
        audio.setAuthorName(audioDetails.getAuthorName());
        audio.setAlbumId(audioDetails.getAlbumId());
        audio.setAlbumName(audioDetails.getAlbumName());
        audio.setShowStatus(audioDetails.getShowStatus());
        audio.setRoadShow(audioDetails.getRecommendLevel());
        // TODO: 2018/8/30 存储币配置

        audio.setFileName(audioDetails.getAudioFileName());
        audio.setFileSize(audioDetails.getAudioFileSize());
        audio.setFilepath(audioDetails.getAudioUrl());
        audio.setMd5(audioDetails.getAudioFileMd5());
        audio.setDurantionSec(audioDetails.getAudioTime());
        audio.setPicUrl(audioDetails.getAudioImgUrl());
        audio.setImgUrl(audioDetails.getPlayImgUrl());
        return new ResponseWrap<>(ResponseCode.CODE_200, audioMapper.updateByPrimaryKeySelective(audio));
    }

    /**
     * 新增音频信息
     *
     * @param audioDetails
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertAudioInfo(AudioDetails audioDetails) {
        Audio audio = new Audio();
        audio.setTitle(audioDetails.getAudioTitle());
        audio.setAuthorName(audioDetails.getAuthorName());
        audio.setAlbumId(audioDetails.getAlbumId());
        audio.setAlbumName(audioDetails.getAlbumName());
        audio.setShowStatus(audioDetails.getShowStatus());
        audio.setRoadShow(audioDetails.getRecommendLevel());
        // TODO: 2018/8/30 存储币配置

        audio.setFileName(audioDetails.getAudioFileName());
        audio.setFileSize(audioDetails.getAudioFileSize());
        audio.setFilepath(audioDetails.getAudioUrl());
        audio.setMd5(audioDetails.getAudioFileMd5());
        audio.setDurantionSec(audioDetails.getAudioTime());
        audio.setPicUrl(audioDetails.getAudioImgUrl());
        audio.setImgUrl(audioDetails.getPlayImgUrl());
        audio.setTagId(0L);
        audio.setCreateTime(new Date());
        audio.setIsRoadShow(audioDetails.getShowArea());
        audio.setPlayCount(0L);
        audio.setPraiseCount(0L);
        audio.setReadCount(0L);
        return new ResponseWrap<>(ResponseCode.CODE_200, audioMapper.insert(audio));
    }

    /**
     * 删除音频信息
     *
     * @param audioId 音频ID
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteAudioInfo(Long audioId) {
        return new ResponseWrap<>(ResponseCode.CODE_200, audioMapper.deleteByPrimaryKey(audioId));
    }

    /**
     * 查询大V音频
     *
     * @param showStatus 显示状态
     * @param audioName  音频名称
     * @param pageNum    当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<AudioInfoVo>> findBigVAudios(Integer showStatus, String audioName, Integer pageNum) {
        List<Long> albums = albumMapper.findAlbumByShowArea(1);
        if (albums == null || albums.size() == 0) {
            return new ResponseWrap<>(ResponseCode.CODE_200);
        }
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<AudioInfoVo> audioInfoVos = audioMapper.findAudiosByAlbumIds(showStatus, audioName, albums);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<AudioInfoVo>) audioInfoVos).toPageInfo());
    }
}
