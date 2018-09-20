package com.service;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.mapper.BannerMapper;
import com.dao.model.Banner;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vo.BannerInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author music
 */
@Service
public class BannerMangerServiceImpl implements BannerMangerService {


    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 分页查询banner图列表
     *
     * @param bannerActivityName   活动名称
     * @param bannerActivityStatus 显示状态
     * @param pageNum              当前页
     * @return
     */
    @Override
    public ResponseWrap<PageInfo<BannerInfoVo>> findBanners(String bannerActivityName, Integer bannerActivityStatus, Integer pageNum, Integer showArea) {
        PageHelper.startPage(pageNum, StaticConstant.PAGE_SIZE_10);
        List<BannerInfoVo> banners = bannerMapper.findBanners(bannerActivityName, bannerActivityStatus, showArea);
        return new ResponseWrap<>(ResponseCode.CODE_200, ((Page<BannerInfoVo>) banners).toPageInfo());
    }

    /**
     * 查询banner信息
     *
     * @param bannerId banner图ID
     * @return
     */
    @Override
    public ResponseWrap<BannerInfoVo> findBannerInfo(Long bannerId) {
        Banner banner = bannerMapper.selectByPrimaryKey(bannerId);
        if (banner == null) {
            return new ResponseWrap<>(ResponseCode.CODE_1003);
        }
        BannerInfoVo bannerInfoVo = new BannerInfoVo();
        bannerInfoVo.setBannerId(banner.getId());
        bannerInfoVo.setBannerName(banner.getBannerName());
        bannerInfoVo.setImgUrl(banner.getBannerPicUrl());
        bannerInfoVo.setLinkUrl(banner.getBannerUrl());
        bannerInfoVo.setShowStatus(banner.getShowStatus());
        bannerInfoVo.setSort(banner.getSort());
        return new ResponseWrap<>(ResponseCode.CODE_200, bannerInfoVo);
    }

    /**
     * 修改banner信息
     *
     * @param bannerInfoVo
     * @return
     */
    @Override
    public ResponseWrap<Integer> updateBannerInfo(BannerInfoVo bannerInfoVo) {
        Banner banner = new Banner();
        banner.setId(bannerInfoVo.getBannerId());
        banner.setBannerName(bannerInfoVo.getBannerName());
        banner.setBannerPicUrl(bannerInfoVo.getImgUrl());
        banner.setBannerUrl(bannerInfoVo.getLinkUrl());
        banner.setShowStatus(bannerInfoVo.getShowStatus());
        banner.setSort(bannerInfoVo.getSort());
        return new ResponseWrap<>(ResponseCode.CODE_200, bannerMapper.updateByPrimaryKeySelective(banner));
    }

    /**
     * 插入banner信息
     *
     * @param bannerInfoVo
     * @return
     */
    @Override
    public ResponseWrap<Integer> insertBannerInfo(BannerInfoVo bannerInfoVo) {
        Banner banner = new Banner();
        banner.setBannerName(bannerInfoVo.getBannerName());
        banner.setBannerPicUrl(bannerInfoVo.getImgUrl());
        banner.setBannerUrl(bannerInfoVo.getLinkUrl());
        banner.setSort(bannerInfoVo.getSort());
        banner.setShowArea(bannerInfoVo.getShowArea());
        banner.setCreateTime(new Date());
        banner.setShowStatus(bannerInfoVo.getShowStatus());
        return new ResponseWrap<>(ResponseCode.CODE_200, bannerMapper.insert(banner));
    }

    /**
     * 删除banner信息
     *
     * @param bannerId
     * @return
     */
    @Override
    public ResponseWrap<Integer> deleteBannerInfo(Long bannerId) {
        return new ResponseWrap<>(ResponseCode.CODE_200, bannerMapper.deleteByPrimaryKey(bannerId));
    }
}
