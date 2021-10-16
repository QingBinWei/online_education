package com.guigu.eduservice.controller;


import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.eduservice.client.VodClient;
import com.guigu.eduservice.entity.EduVideo;
import com.guigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
@Api(description="视频管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    VodClient vodClient;
    @ApiOperation(value = "显示小节")
    @GetMapping("/getVideoById/{videoId}")
    public ReturnResult seVideoById(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);
        return ReturnResult.ok().data("eduVideo",video);
    }
    @ApiOperation(value = "添加小节")
    @PostMapping("/addVideo")
    public ReturnResult addChapter(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return ReturnResult.ok().message("添加成功");
    }
    @ApiOperation(value = "更新小节")
    @PostMapping("/updateVideo")
    public ReturnResult updateChapter(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return ReturnResult.ok().message("添加成功");
    }
    @ApiOperation(value = "删除小节")
    @DeleteMapping("/delVideo/{videoID}")
    public ReturnResult delChapter(@PathVariable String videoID) {
        EduVideo eduVideo = eduVideoService.getById(videoID);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            ReturnResult returnResult = vodClient.delVideo(videoSourceId);
            if (!StringUtils.isEmpty(returnResult)&&returnResult.getCode() == 20001) {
                throw new GuLiEception(20001, "删除视频失败");
            }
        }
        eduVideoService.removeById(videoID);
        return ReturnResult.ok().message("删除成功");
     }

}

