package com.guigu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduComment;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.vo.EduCourseAndDescription;
import com.guigu.eduservice.service.EduCommentService;
import com.guigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-17
 */
@Api(description="评论管理")
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {
    @Autowired
    EduCommentService eduCommentService;
    @Autowired
    EduCourseService eduCourseService;

    @GetMapping("selCommnet/{courseId}")
    public ReturnResult selComment(@PathVariable String courseId){
        QueryWrapper<EduComment> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("gmt_create");
        List<EduComment> eduCommentList = eduCommentService.list(wrapper);
        return ReturnResult.ok().data("commentList",eduCommentList);
    }
    @CacheEvict(value = "famousCourse", key="'courseList'")
    @PostMapping("addCommnet")
    public ReturnResult addComment(@RequestBody EduComment eduComment){
        boolean save = eduCommentService.save(eduComment);
      /* EduCourse eduCourse=new EduCourse();
       eduCourse.setCommentCount(edu)*/
            if(save){
                //课程评论数加一
                EduCourse eduCourse = eduCourseService.getById(eduComment.getCourseId());
                eduCourse.setCommentCount(eduCourse.getCommentCount()+1);
                eduCourseService.updateById(eduCourse);
                return ReturnResult.ok().message("发表成功");
        }else{
            return ReturnResult.ok().message("发表失败");
        }
    }

}

