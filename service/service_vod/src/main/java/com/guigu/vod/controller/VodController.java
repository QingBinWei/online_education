package com.guigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.guigu.vod.service.VodService;
import com.guigu.commonutils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAlyiVideo")
    public ReturnResult uploadAlyiVideo(MultipartFile file) {
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return ReturnResult.ok().data("videoId",videoId);
    }
    @DeleteMapping("delVideo/{id}")
    public ReturnResult delVideo(@PathVariable("id") String id) {
        //返回上传视频id
        System.out.println("55555**********************************************************");
        vodService.delVideo(id);
        return ReturnResult.ok();
    }
    //1 根据视频iD获取视频播放凭证
    @GetMapping("getAuth/{videoId}")
    public ReturnResult getPlayAuth(@PathVariable String videoId) throws Exception{
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, "替换你的密钥", "HnjJjtpysSXtIFZZ14DFS7gNXOwVqj");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);
        return ReturnResult.ok().data("playAuth",response.getPlayAuth());
    }
}
