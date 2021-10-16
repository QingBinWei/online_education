package com.guigu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.vo.EduCourseVo;
import com.guigu.eduservice.client.VodClient;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.publish.PublishCourse;
import com.guigu.eduservice.entity.vo.CourseQuery;
import com.guigu.eduservice.entity.vo.EduCourseAndDescription;
import com.guigu.eduservice.service.EduCourseService;
import com.guigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
@Api(description="课程管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    VodClient vodClient;
    @Autowired
    EduVideoService eduVideoService;
    @ApiOperation(value = "课程管理")
    @PostMapping("/insertCourse")
    public ReturnResult insertCourse(@ApiParam(name = "添加课程",value = "eduCourseAndDescription",required = true) @RequestBody EduCourseAndDescription eduCourseAndDescription){
        String courseId=eduCourseService.insertCourse(eduCourseAndDescription);
        return ReturnResult.ok().data("courseId",courseId);
    }
    @ApiOperation(value = "课程查询")
    @GetMapping("/selCourse/{courseId}")
    public ReturnResult selCourseById(@ApiParam(name = "查询课程",value = "courseId",required = true) @PathVariable String courseId){
        EduCourseAndDescription eduCourseAndDescription = eduCourseService.selCourseById(courseId);
        return ReturnResult.ok().data("eduCourseAndDescription",eduCourseAndDescription);
    }
    @ApiOperation(value = "课程修改")
    @PostMapping("/updateCourse")
    public ReturnResult updateCourse(@ApiParam(name = "更新课程",value = "eduCourseAndDescription",required = true) @RequestBody EduCourseAndDescription eduCourseAndDescription){
       String courseId=eduCourseService.updateCourse(eduCourseAndDescription);
        return ReturnResult.ok().data("courseId",courseId);
    }
    @ApiOperation(value = "课程发布信息显示")
    @GetMapping("/publishCourseInfo/{courseId}")
    public ReturnResult getPublishCourseInFo(@ApiParam(name = "查询课程",value = "courseId",required = true) @PathVariable String courseId){
        PublishCourse publishCourse = eduCourseService.getPublishCourseInfo(courseId);
        return ReturnResult.ok().data("publishCourse",publishCourse);
    }
    @ApiOperation(value = "课程发布信息显示")
    @PostMapping("/publishCourse/{courseId}")
    public ReturnResult publishCourse(@ApiParam(name = "查询课程",value = "courseId",required = true) @PathVariable String courseId){
       EduCourse eduCourse=new EduCourse();
       eduCourse.setId(courseId);
       eduCourse.setStatus("1");
       eduCourseService.updateById(eduCourse);
        return ReturnResult.ok().message("发布成功");
    }
    /**
     * 课程列表显示
     */
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ReturnResult findAllTeacher(){
        List<EduCourse> list = eduCourseService.list(null);
        return ReturnResult.ok().data("items",list);
    }
    //条件分页查询
    @ApiOperation(value = "条件分页查询")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public ReturnResult pageTeacherCondition(@ApiParam(name="current",value = "当前页",required = true)
                                             @PathVariable long current,
                                             @ApiParam(name="limit",value = "每页显示条数",required = true)
                                             @PathVariable long limit,
                                             @ApiParam(name="courseQuery",value = "查询条件",required = false)
                                             @RequestBody(required = false) CourseQuery courseQuery){
        Page<EduCourse> page=new Page<>(current,limit);
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        //多条件组合查询
        String name = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("title",name);
        }
        if(!StringUtils.isEmpty(status)){
            queryWrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        queryWrapper.orderByDesc("gmt_create");
        eduCourseService.page(page,queryWrapper);
        List<EduCourse> records = page.getRecords();
        long total = page.getTotal();
        Map map=new HashMap();
        map.put("total",total); //总记录数
        map.put("record",records);//当前页数据
        return ReturnResult.ok().data("items",map);
    }
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("deleCourse/{id}")
    public ReturnResult deleteCourse( @ApiParam(name = "id", value = "讲师ID", required = true)
                                       @PathVariable("id") String id){

        //小节
        eduCourseService.deleteCourseVideo(id);
        //章节
        eduCourseService.deleteCourseChapter(id);
        //描述
        eduCourseService.deleteCourseDescription(id);
        //课程
        eduCourseService.removeById(id);
        return ReturnResult.ok();
    }

    @PostMapping("getCourse/{id}")
    public EduCourseVo getCourse(@PathVariable String id){
        EduCourseVo eduCourseVo=new EduCourseVo();
          EduCourse eduCourse = eduCourseService.getById(id);
          BeanUtils.copyProperties(eduCourse,eduCourseVo);
          return eduCourseVo;
      }

}

