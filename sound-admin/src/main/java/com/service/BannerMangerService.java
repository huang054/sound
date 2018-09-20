package com.service;

import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.vo.BannerInfoVo;

/**
 * @author music
 */
public interface BannerMangerService {


    /**
     * 分页查询banner图列表
     *
     * @param bannerActivityName   活动名称
     * @param bannerActivityStatus 显示状态
     * @param pageNum              当前页
     * @param showArea             显示区域
     * @return
     */
    ResponseWrap<PageInfo<BannerInfoVo>> findBanners(String bannerActivityName, Integer bannerActivityStatus, Integer pageNum, Integer showArea);

    /**
     * 查询单个Banner图
     *
     * @param bannerId banner图ID
     * @return
     */
    ResponseWrap<BannerInfoVo> findBannerInfo(Long bannerId);

    /**
     * 修改banner图信息
     *
     * @param bannerInfoVo
     * @return
     */
    ResponseWrap<Integer> updateBannerInfo(BannerInfoVo bannerInfoVo);

    /**
     * 插入banner 图信息
     *
     * @param bannerInfoVo
     * @return
     */
    ResponseWrap<Integer> insertBannerInfo(BannerInfoVo bannerInfoVo);

    /**
     * 删除banner 图信息
     *
     * @param bannerId
     * @return
     */
    ResponseWrap<Integer> deleteBannerInfo(Long bannerId);
}
