package com.sound.controller;

import com.sound.common.ParamCode;
import com.sound.common.Response;

import com.sound.model.AudioModel;
import com.sound.model.SystemRecommend;
import com.sound.service.AlbumService;
import com.sound.service.AudioService;
import com.sound.service.SysRecommendService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Administrator on 2018/4/28.
 */
@RequestMapping(path="sysRecommend")
@Api(tags="系统强制推荐")
@RestController
public class RecommendController extends BaseController<SystemRecommend>{

    private static final Logger logger = LoggerFactory.getLogger(RecommendController.class);


    //1.系统推荐列表
    @Autowired
    private SysRecommendService sysRecommendService;

    @Autowired
    private AudioService audioService;

    @Autowired
    private AlbumService albumService;


    @GetMapping(path="recommendList.do")
    @ApiOperation(value = "Banner列表接口",tags = "",response = SystemRecommend.class)
    public Response getReList(int page,int size){
        Response resp = new Response();
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        PageRequest pageable = PageRequest.of(page, size,sort);
        try{
            Page<SystemRecommend> page1= sysRecommendService.findAll(pageable);
            Map result = new HashMap<String,Object>();
            result.put("imghost",imgsDsownload);
            result.put("audiohost",soundDownload);
            result.put("result",page1.getContent());
            resp.setData(result);
            resp.setRespone(ParamCode.SUCSESS);
            return resp;
        }catch (Exception e){
            logger.error("recommendList fail:",e);
        }
        resp.setRespone(ParamCode.FAIL);
        return resp;
    }

    @GetMapping(path="recommendServer.do")
    @ResponseBody
    public List<AudioModel> getReList(){
        Response<List<AudioModel>> resp = new Response<>(true);
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageable = PageRequest.of(0, 10,sort);
        Page<SystemRecommend> page1= sysRecommendService.findAll(pageable);

        List<AudioModel> audioModelList = new ArrayList<>();
        List<SystemRecommend> systemRecommendList=page1.getContent();
        for( SystemRecommend sysd:systemRecommendList){
            List<AudioModel> lsit =audioService.findByAlbumId(sysd.getAlbumId());
            audioModelList.addAll(lsit);
        }
        Collections.sort(audioModelList, new Comparator<AudioModel>() {
            @Override
            public int compare(AudioModel o1, AudioModel o2) {
                return Long.compare(o1.getCreateTime().getTime(),o2.getCreateTime().getTime());
            }
        });
        List<AudioModel> resultList = new ArrayList<>();
        for(AudioModel model:audioModelList){
            resultList.add(model);
            if(resultList.size()>=10) break;
        }
       
        return resultList;
    }
}
