package com.guigu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师; InnoDB free: 8192 kB 服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> getFamousTeacher();

    Map selTeacherBypage(Page<EduTeacher> eduTeacherPage);
}
