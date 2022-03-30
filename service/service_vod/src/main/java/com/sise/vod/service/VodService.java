package com.sise.vod.service;

import org.springframework.web.multipart.MultipartFile;
//上传视频到阿里云
public interface VodService {
    String uploadVideoAly(MultipartFile file);
}
