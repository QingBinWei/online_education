package com.guigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    void delVideo(String id);
}