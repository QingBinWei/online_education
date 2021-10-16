package com.guigu.eduservice.mapper;

import com.guigu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guigu.eduservice.entity.publish.CourseDetail;
import com.guigu.eduservice.entity.publish.PublishCourse;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB Mapper 接口
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
       public PublishCourse getPublishCourseInFo(String courseId);
       public CourseDetail getBaseCourseInfo(String courseId);
}
