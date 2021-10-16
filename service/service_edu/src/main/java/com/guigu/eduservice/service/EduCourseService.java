package com.guigu.eduservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.eduservice.entity.EduTeacher;
import com.guigu.eduservice.entity.publish.CourseDetail;
import com.guigu.eduservice.entity.publish.PublishCourse;
import com.guigu.eduservice.entity.vo.EduCourseAndDescription;
import com.guigu.eduservice.entity.vo.FrontCourseQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB 服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
public interface EduCourseService extends IService<EduCourse> {

    String insertCourse(EduCourseAndDescription eduCourseAndDescription);

    EduCourseAndDescription selCourseById(String courseId);

    String updateCourse(EduCourseAndDescription eduCourseAndDescription);

    PublishCourse getPublishCourseInfo(String courseId);

    void deleteCourseDescription(String id);

    void deleteCourseChapter(String id);

    void deleteCourseVideo(String id);

    List<EduCourse> getFamousCourse();

    Map selCourseBypage(Page<EduCourse> eduCoursePage, FrontCourseQuery frontCourseQuery);

    CourseDetail getCourseDetail(String id);
}
