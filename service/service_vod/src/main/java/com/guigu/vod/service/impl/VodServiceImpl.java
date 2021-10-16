package com.guigu.vod.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.vod.service.VodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideoAly(MultipartFile file) {
        try {
            String accessKeyId = "LTAI4GCnvkuz3KhPD9djkYS4";
            String accessKeySecret = "HnjJjtpysSXtIFZZ14DFS7gNXOwVqj";
            String title=file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")-1); //上传后文件名
            String fileName=file.getOriginalFilename();  //原文件名
            String viedoId="";
            //inputStream：上传文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId,accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            if (response.isSuccess()) {
                viedoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                viedoId = response.getVideoId();
            }
            return viedoId;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void delVideo(String id) {
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(id);
            DefaultAcsClient client =  initVodClient("LTAI4GCnvkuz3KhPD9djkYS4", "HnjJjtpysSXtIFZZ14DFS7gNXOwVqj");
            DeleteVideoResponse response = new DeleteVideoResponse();
            response=client.getAcsResponse(request);
        }catch (Exception e){
            e.printStackTrace();
            //排除视频不在情况
            if(!e.getMessage().contains("The video does not exist")){
                throw new GuLiEception(20001,"调用阿里云删除视频失败");
            }
        }
    }
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
