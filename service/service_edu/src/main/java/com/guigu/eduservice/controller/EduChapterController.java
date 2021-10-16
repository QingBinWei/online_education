package com.guigu.eduservice.controller;


import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduChapter;
import com.guigu.eduservice.entity.chapter.Chapter;
import com.guigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
@Api(description="章节管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {
     @Autowired
    EduChapterService eduChapterService;
    @ApiOperation(value = "显示所有章节")
    @GetMapping("/getChapterVideo/{courseId}")
    public ReturnResult getChapterVideo(@PathVariable String courseId){
        List<Chapter> list=eduChapterService.getChapterAndVideo(courseId);
        return ReturnResult.ok().data("list",list);
    }
    @ApiOperation(value = "添加章节")
    @PostMapping("/addChapter")
    public ReturnResult addChapter(@RequestBody EduChapter eduChapter){
       eduChapterService.save(eduChapter);
        return ReturnResult.ok().message("添加成功");
    }
    @ApiOperation(value = "显示章节")
    @GetMapping("/getChapterById/{chapterId}")
    public ReturnResult selChapterById(@PathVariable String chapterId){
       EduChapter eduChapter=eduChapterService.getById(chapterId);
        return ReturnResult.ok().data("eduChapter",eduChapter);
    }
    @ApiOperation(value = "更新章节")
    @PostMapping("/updateChapter")
    public ReturnResult updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return ReturnResult.ok().message("保存成功");
    }
    @ApiOperation(value = "删除章节")
    @DeleteMapping("/delChapter/{chapterId}")
    public ReturnResult delChapter(@PathVariable String chapterId){
        eduChapterService.removeById(chapterId);
        return ReturnResult.ok().message("删除成功");
    }

}

