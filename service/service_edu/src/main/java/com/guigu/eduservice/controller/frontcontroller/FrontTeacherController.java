package com.guigu.eduservice.controller.frontcontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.EduTeacher;
import com.guigu.eduservice.service.EduCourseService;
import com.guigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(description="讲师列表")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/frontteacher")
public class FrontTeacherController {
    @Autowired
    EduTeacherService  eduTeacherService;
    @Autowired
    EduCourseService eduCourseService;
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public ReturnResult selTeacher(@PathVariable(required = true) long current,@PathVariable(required = true) long limit){
        Page<EduTeacher> eduTeacherPage=new Page<>(current,limit);
        Map map=eduTeacherService.selTeacherBypage(eduTeacherPage);
        return ReturnResult.ok().data(map);
    }
    @ApiOperation(value = "讲师详情")
    @GetMapping("teacherDetail/{id}")
    public ReturnResult teacherDetail(@PathVariable(required = true) String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);
        return ReturnResult.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}
