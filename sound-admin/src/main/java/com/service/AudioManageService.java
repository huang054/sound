package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.AudioDetails;
import com.vo.AudioInfoVo;

/**
 * @author music
 */
public interface AudioManageService {

    /**
     * 查询音频列表
     *
     * @param showStatus 显示状态
     * @param audioName  音频名称
     * @param pageNum    当前页
     * @param showArea   展示区域
     * @return
     */
    ResponseWrap<PageInfo<AudioInfoVo>> findAudios(Integer showStatus, String audioName, Integer pageNum, Integer showArea);

    /**
     * 查询音频信息
     *
     * @param audioId 音频ID
     * @return
     */
    ResponseWrap<AudioDetails> findAudioInfo(Long audioId);

    /**
     * 更新音频信息
     *
     * @param audioDetails
     * @return
     */
    ResponseWrap<Integer> updateAudioInfo(AudioDetails audioDetails);

    /**
     * 新增音频信息
     *
     * @param audioDetails
     * @return
     */
    ResponseWrap<Integer> insertAudioInfo(AudioDetails audioDetails);

    /**
     * 删除音频信息
     *
     * @param audioId 音频ID
     * @return
     */
    ResponseWrap<Integer> deleteAudioInfo(Long audioId);

    /**
     * 查询大V音频
     *
     * @param showStatus 显示状态
     * @param audioName  音频名称
     * @param pageNum    当前页
     * @return
     */
    ResponseWrap<PageInfo<AudioInfoVo>> findBigVAudios(Integer showStatus, String audioName, Integer pageNum);
}
