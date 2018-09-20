package com.dao.mapper;

import com.dao.model.Audio;
import com.dao.util.MyMapper;
import com.vo.AudioInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AudioMapper extends MyMapper<Audio> {

    /**
     * 查询音频列表
     *
     * @param showArea   展示区域
     * @param showStatus 显示状态
     * @param audioName  节目名称
     * @return
     */
    List<AudioInfoVo> findAudios(@Param(value = "showArea") Integer showArea,
                                 @Param(value = "showStatus") Integer showStatus,
                                 @Param(value = "audioName") String audioName);

    /**
     * 查询音频列表
     *
     * @param showStatus 显示状态
     * @param audioName  节目名称
     * @param albumIds   专辑ID
     * @return
     */
    List<AudioInfoVo> findAudiosByAlbumIds(@Param(value = "showStatus") Integer showStatus,
                                 @Param(value = "audioName") String audioName,
                                 @Param(value = "albumIds") List<Long> albumIds);
}