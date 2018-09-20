package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.BannerMangerService;
import com.vo.BannerInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author music
 */
@RestController
@RequestMapping(path = "/api/banner")
@Api(description = "banner设置")
public class BannerManagerController {

    @Autowired
    private BannerMangerService bannerMangerService;

    @ApiOperation(value = "查询banner活动列表")
    @RequestMapping(value = "/findBanners", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<BannerInfoVo>> findBanners(@ApiParam("banner活动名") @RequestParam(required = false) String bannerActivityName,
                                                            @ApiParam("banner活动状态 是否显示：1为显示 0为不显示") @RequestParam(required = false) Integer bannerActivityStatus,
                                                            @ApiParam(value = "当前页 默认第一页", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                                            @ApiParam(value = "展示区域 1：小链频道 2：小链社区 默认小链频道", defaultValue = "1") @RequestParam Integer showArea) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return bannerMangerService.findBanners(bannerActivityName, bannerActivityStatus, pageNum, showArea);
    }


    @ApiOperation(value = "查询banner信息")
    @RequestMapping(value = "/findBannerInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<BannerInfoVo> findBannerInfo(@ApiParam("bannerId") @RequestParam Long bannerId) {

        return bannerMangerService.findBannerInfo(bannerId);
    }

    @Secured(value = "ROLE_BANNER_ADD_UPDATE")
    @ApiOperation(value = "修改banner信息 ")
    @RequestMapping(value = "/updateBannerInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateBannerInfo(@Valid @RequestBody BannerInfoVo bannerInfoVo) {

        return bannerMangerService.updateBannerInfo(bannerInfoVo);
    }

    @Secured(value = "ROLE_BANNER_ADD_UPDATE")
    @ApiOperation(value = "插入banner信息 ")
    @RequestMapping(value = "/insertBannerInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertBannerInfo(@Valid @RequestBody BannerInfoVo bannerInfoVo) {

        return bannerMangerService.insertBannerInfo(bannerInfoVo);
    }

    @Secured(value = "ROLE_BANNER_DELETE")
    @ApiOperation(value = "删除banner信息 ")
    @RequestMapping(value = "/deleteBannerInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteBannerInfo(@ApiParam("bannerId") @RequestParam Long bannerId) {

        return bannerMangerService.deleteBannerInfo(bannerId);
    }


}
