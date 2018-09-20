package com.controller;

import com.basic.constant.StaticConstant;
import com.basic.response.ResponseCode;
import com.basic.response.ResponseWrap;
import com.dao.model.Audio;
import com.github.pagehelper.PageInfo;
import com.service.AudioManageService;
import com.vo.AlbumDetails;
import com.vo.AudioDetails;
import com.vo.AudioInfoVo;
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
@RequestMapping(value = "/api/audio")
@RestController
@Api(description = "音频管理")
public class AudioManageController {

    @Autowired
    private AudioManageService audioManageService;


    @Secured(value = "ROLE_SUGGEST_AUDIO")
    @ApiOperation(value = "查询推荐音频列表")
    @RequestMapping(value = "/findSuggestAudios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<AudioInfoVo>> findSuggestAudios(@ApiParam("显示状态 1为显示 0为不显示") @RequestParam(required = false) Integer showStatus,
                                                                 @ApiParam("节目名称") @RequestParam(required = false) String audioName,
                                                                 @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return audioManageService.findAudios(showStatus, audioName, pageNum, 1);
    }


    @Secured(value = "ROLE_ROAD_SHOW_AUDIO")
    @ApiOperation(value = "查询轻路演音频列表")
    @RequestMapping(value = "/findRoadShowAudios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<AudioInfoVo>> findRoadShowAudios(@ApiParam("显示状态 1为显示 0为不显示") @RequestParam(required = false) Integer showStatus,
                                                                  @ApiParam("节目名称") @RequestParam(required = false) String audioName,
                                                                  @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return audioManageService.findAudios(showStatus, audioName, pageNum, 2);
    }

    @Secured(value = "ROLE_BIG_V_AUDIO")
    @ApiOperation(value = "查询大V音频列表")
    @RequestMapping(value = "/findBigVAudios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<PageInfo<AudioInfoVo>> findBigVAudios(@ApiParam("显示状态 1为显示 0为不显示") @RequestParam(required = false) Integer showStatus,
                                                              @ApiParam("节目名称") @RequestParam(required = false) String audioName,
                                                              @ApiParam(value = "当前页", defaultValue = "1") @RequestParam(required = false) Integer pageNum) {

        if (pageNum == null) {
            //默认展示第一页
            pageNum = StaticConstant.PAGE_NUM;
        }
        return audioManageService.findBigVAudios(showStatus, audioName, pageNum);
    }


    @ApiOperation(value = "查询音频信息")
    @RequestMapping(value = "/findAudioInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<AudioDetails> findAudioInfo(@ApiParam("音频ID") @RequestParam Long audioId) {

        return audioManageService.findAudioInfo(audioId);
    }

    @Secured(value = "ROLE_AUDIO_ADD_UPDATE")
    @ApiOperation(value = "更新音频信息")
    @RequestMapping(value = "/updateAudioInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> updateAudioInfo(@Valid @RequestBody AudioDetails audioDetails) {

        return audioManageService.updateAudioInfo(audioDetails);
    }

    @Secured(value = "ROLE_AUDIO_ADD_UPDATE")
    @ApiOperation(value = "新增音频信息")
    @RequestMapping(value = "/insertAudioInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> insertAudioInfo(@Valid @RequestBody AudioDetails audioDetails) {

        return audioManageService.insertAudioInfo(audioDetails);
    }

    @Secured(value = "ROLE_AUDIO_DELETE")
    @ApiOperation(value = "删除音频信息")
    @RequestMapping(value = "/deleteAudioInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseWrap<Integer> deleteAudioInfo(@ApiParam("音频ID") @RequestParam Long audioId) {

        return audioManageService.deleteAudioInfo(audioId);
    }
}
