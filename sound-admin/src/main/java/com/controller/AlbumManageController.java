package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.github.pagehelper.PageInfo;
import com.service.AlbumManageService;
import com.vo.AlbumDetails;
import com.vo.AlbumInfoVo;
import com.vo.AlbumVo;
import com.vo.BannerInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author music
 */
@RequestMapping(value = "/api/album")
@RestController
@Api(description = "专辑管理")
public class AlbumManageController {


    @Autowired
    private AlbumManageService albumManageService;


    @ApiOperation(value = "查询专辑列表")
    @RequestMapping(value = "/findAlbums", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<AlbumInfoVo>> findAlbums(@ApiParam("显示状态 1为显示 0为不显示") @RequestParam(required = false) Integer showStatus,
                                                          @ApiParam("专辑名称") @RequestParam(required = false) String albumName,
                                                          @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum,
                                                          @ApiParam(value = "展示区域  1：大v专区") @RequestParam(required = false) Integer showArea) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return albumManageService.findAlbums(showStatus, albumName, pageNum, showArea);
    }


    @ApiOperation(value = "查询专辑信息")
    @RequestMapping(value = "/findAlbumInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<AlbumDetails> findAlbumInfo(@ApiParam("专辑ID") @RequestParam Long albumId) {

        return albumManageService.findAlbumInfo(albumId);
    }

    @ApiOperation(value = "更新专辑信息")
    @RequestMapping(value = "/updateAlbumInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateAlbumInfo(@Valid @RequestBody AlbumDetails albumDetails) {

        return albumManageService.updateAlbumInfo(albumDetails);
    }

    @ApiOperation(value = "新增专辑信息")
    @RequestMapping(value = "/insertAlbumInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertAlbumInfo(@Valid @RequestBody AlbumDetails albumDetails) {

        return albumManageService.insertAlbumInfo(albumDetails);
    }

    @ApiOperation(value = "删除专辑信息")
    @RequestMapping(value = "/deleteAlbumInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteAlbumInfo(@ApiParam("专辑ID") @RequestParam Long albumId) {

        return albumManageService.deleteAlbumInfo(albumId);
    }

    @ApiOperation(value = "获取专辑下拉列表")
    @RequestMapping(value = "/findAlbumAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<List<AlbumVo>> findAlbumAll() {

        return albumManageService.findAlbumAll();
    }

}
