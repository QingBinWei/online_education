package com.guigu.eduservice.controller.frontcontroller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduComment;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.chapter.Chapter;
import com.guigu.eduservice.entity.publish.CourseDetail;
import com.guigu.eduservice.entity.vo.FrontCourseQuery;
import com.guigu.eduservice.service.EduChapterService;
import com.guigu.eduservice.service.EduCommentService;
import com.guigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description="前台课程列表列表")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/eduservice/froncourse")
public class FrontCourseController {
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduCommentService eduCommentService;

    @ApiOperation(value = "分页查询课程")
    @PostMapping("pagecourses/{current}/{limit}")
    public ReturnResult selCourse(@ApiParam(name="current",value = "当前页",required = true)
                                      @PathVariable long current,
                                      @ApiParam(name="limit",value = "每页显示条数",required = true)
                                      @PathVariable long limit,
                                      @ApiParam(name="frontCourseQuery",value = "查询条件",required = false)
                                      @RequestBody(required = false) FrontCourseQuery frontCourseQuery){
        Page<EduCourse> eduCoursePage=new Page<>(current,limit);
        Map map=eduCourseService.selCourseBypage(eduCoursePage,frontCourseQuery);
        return ReturnResult.ok().data(map);
    }
    @CacheEvict(value = "famousCourse", key="'courseList'")
    @ApiOperation(value = "课程详情")
    @GetMapping("courseDetail/{id}")
    public ReturnResult teacherDetail(@PathVariable(required = true) String id){
        CourseDetail courseDetail=eduCourseService.getCourseDetail(id);
        courseDetail.getViewCount();
        //每点击课程详情 该课程 浏览数加一
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setViewCount(courseDetail.getViewCount()+1);
        eduCourseService.updateById(eduCourse);
        List<Chapter> chapterList=eduChapterService.getChapterAndVideo(id);
        return ReturnResult.ok().data("courseInfo",courseDetail).data("chapter",chapterList);
    }
    @ApiOperation(value = "课程详情")
    @GetMapping("searchCourse/{current}/{limit}/{search}")
    public ReturnResult searchCourse(@PathVariable(required = true) String search,
                         @PathVariable long current, @PathVariable long limit){
        Page<EduCourse> eduCoursePage=new Page<>(current,limit);
        QueryWrapper<EduCourse> wrapper=new QueryWrapper<>();
        wrapper.like("title",search);
        IPage<EduCourse> page = eduCourseService.page(eduCoursePage, wrapper);
        Map map=new HashMap();
        map.put("total",eduCoursePage.getTotal());
        map.put("items",eduCoursePage.getRecords());
        map.put("size",eduCoursePage.getSize());
        map.put("current",eduCoursePage.getCurrent());
        map.put("hasNext",eduCoursePage.hasNext());
        map.put("hasPrevious",eduCoursePage.hasPrevious());
        map.put("pages",eduCoursePage.getPages());
        return ReturnResult.ok().data(map);
    }
}
