package com.sise.eduservice.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sise.commonutils.R;
import com.sise.eduservice.client.VodClient;
import com.sise.eduservice.entity.EduVideo;
import com.sise.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author myth
 * @since 2022-03-25
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;
    //注入vodClient
    @Autowired
    private VodClient vodClient;

    //增加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }
    //删除小节，删除对应阿里云视频
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id，远程调用实现视频删除
            vodClient.removeAlyVideo(videoSourceId);
        }
        //删除小节
        videoService.removeById(id);
        return R.ok();
    }

    //TODO 修改小节
    //根据id查询小节
    @GetMapping("getVideoInfo/{id}")
    public R getVideoInfo(@PathVariable String id){
        EduVideo eduVideo = videoService.getById(id);
        return R.ok().data("video",eduVideo);
    }

    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }



}

