package com.guigu.eduservice.controller.frontcontroller;

import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.EduTeacher;
import com.guigu.eduservice.service.EduCourseService;
import com.guigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(description="前台banner")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/index")
public class IndexController {
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduTeacherService eduTeacherService;
    @Cacheable(value = "famousCourse", key="'courseList'")
    @ApiOperation(value = "查询著名课程")
    @GetMapping("famousCourse")
    public ReturnResult getFamousCourse(){
        List<EduCourse> eduCourseList=eduCourseService.getFamousCourse();
        return ReturnResult.ok().data("items",eduCourseList);
    }
    @Cacheable(value = "famousTeacher", key="'teacherList'")
    @ApiOperation(value = "查询著名老师")
    @GetMapping("famousTeacher")
    public ReturnResult getFamousTeacher(){
        List<EduTeacher> eduTeacherList=eduTeacherService.getFamousTeacher();
        return ReturnResult.ok().data("items",eduTeacherList);
    }

}
