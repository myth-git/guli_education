package com.sise.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//上传视频到阿里云
public interface VodService {
    String uploadVideoAly(MultipartFile file);
    //删除多个阿里云视频的方法
    void removeMoreAlyVideo(List<String> videoIdList);
}
