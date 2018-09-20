package com.dao.mapper;

import com.dao.model.Banner;
import com.dao.util.MyMapper;
import com.vo.BannerInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author music
 */
@Repository
public interface BannerMapper extends MyMapper<Banner> {

    /**
     * 分页查询banner图列表
     *
     * @param bannerActivityName   活动名称
     * @param bannerActivityStatus 显示状态
     * @param showArea             展示区域 1：小链频道 2：小链社区
     * @return
     */
    List<BannerInfoVo> findBanners(@Param("bannerActivityName") String bannerActivityName,
                                   @Param("bannerActivityStatus") Integer bannerActivityStatus,
                                   @Param("showArea") Integer showArea);
}