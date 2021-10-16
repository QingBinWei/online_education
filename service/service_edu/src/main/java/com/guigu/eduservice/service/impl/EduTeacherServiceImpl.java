package com.guigu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.eduservice.entity.EduCourse;
import com.guigu.eduservice.entity.EduTeacher;
import com.guigu.eduservice.mapper.EduTeacherMapper;
import com.guigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师; InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-16
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public List<EduTeacher> getFamousTeacher() {
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        queryWrapper.last("limit 4");
        List<EduTeacher> eduCourseList = baseMapper.selectList(queryWrapper);
        return eduCourseList;
    }

    @Override
    public Map selTeacherBypage(Page<EduTeacher> eduTeacherPage) {
        QueryWrapper<EduTeacher> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        baseMapper.selectPage(eduTeacherPage, wrapper);
        eduTeacherPage.getRecords();
        Map map=new HashMap();
        map.put("total",eduTeacherPage.getTotal());
        map.put("items",eduTeacherPage.getRecords());
        map.put("size",eduTeacherPage.getSize());
        map.put("current",eduTeacherPage.getCurrent());
        map.put("hasNext",eduTeacherPage.hasNext());
        map.put("hasPrevious",eduTeacherPage.hasPrevious());
        map.put("pages",eduTeacherPage.getPages());
        return map;
    }

}
