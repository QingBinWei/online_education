package com.guigu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.eduservice.client.VodClient;
import com.guigu.eduservice.entity.*;
import com.guigu.eduservice.entity.publish.CourseDetail;
import com.guigu.eduservice.entity.publish.PublishCourse;
import com.guigu.eduservice.entity.vo.EduCourseAndDescription;
import com.guigu.eduservice.entity.vo.FrontCourseQuery;
import com.guigu.eduservice.mapper.EduCourseMapper;
import com.guigu.eduservice.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    VodClient vodClient;
    @Autowired
    EduSubjectService eduSubjectService;
    @Override
    public String insertCourse(EduCourseAndDescription eduCourseAndDescription) {
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        String courseDescription=eduCourseAndDescription.getDescription();
        EduCourse eduCourse=eduCourseAndDescription.getEduCourse();
        int insert = baseMapper.insert(eduCourse);
        if(insert<1){
            throw new GuLiEception(20001,"添加课程失败");
        }
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courseDescription);
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public EduCourseAndDescription selCourseById(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        EduCourseDescription eduCourserDescription = eduCourseDescriptionService.getById(courseId);
        String description=eduCourserDescription.getDescription();
        EduCourseAndDescription eduCourseAndDescription=new EduCourseAndDescription();
        eduCourseAndDescription.setDescription(description);
        eduCourseAndDescription.setEduCourse(eduCourse);
        return eduCourseAndDescription;
    }

    @Override
    public String updateCourse(EduCourseAndDescription eduCourseAndDescription) {
        EduCourse eduCourse = eduCourseAndDescription.getEduCourse();
        baseMapper.updateById(eduCourse);
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        String description = eduCourseAndDescription.getDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(description);
        eduCourseDescriptionService.updateById(eduCourseDescription);
        return eduCourse.getId();

    }

    @Override
    public PublishCourse getPublishCourseInfo(String courseId) {
        PublishCourse publishCourseInFo = baseMapper.getPublishCourseInFo(courseId);
        return publishCourseInFo;
    }

    @Override
    public void deleteCourseDescription(String id) {
        eduCourseDescriptionService.removeById(id);

    }

    @Override
    public void deleteCourseChapter(String id) {
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        eduChapterService.remove(queryWrapper);

    }

    @Override
    public void deleteCourseVideo(String id) {
        String videoSourceId="";
        QueryWrapper<EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",id);
        List<EduVideo> listVideo = eduVideoService.list(queryWrapper);
        for(EduVideo video:listVideo){
            if(!StringUtils.isEmpty(video.getVideoSourceId())){
                videoSourceId+=video.getVideoSourceId()+",";
            }
        }
        if(!StringUtils.isEmpty(videoSourceId)){
            videoSourceId=videoSourceId.substring(0,videoSourceId.lastIndexOf(","));
            //调用edu-vod 删除阿里云视频
            ReturnResult returnResult= vodClient.delVideo(videoSourceId);
            if (returnResult.getCode() == 20001) {
                throw new GuLiEception(20001, "删除视频失败");
            }
        }

        eduVideoService.remove(queryWrapper);
    }

    @Override
    public List<EduCourse> getFamousCourse() {
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        List<EduCourse> eduCourseList = baseMapper.selectList(queryWrapper);
        return eduCourseList;
    }

    @Override
    public Map selCourseBypage(Page<EduCourse> eduCoursePage, FrontCourseQuery frontCourseQuery) {
        QueryWrapper<EduCourse> courseQueryWrapper =new QueryWrapper<>();
        if(!StringUtils.isEmpty(frontCourseQuery.getSubjectParentId())){
                courseQueryWrapper.eq("subject_parent_id",frontCourseQuery.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(frontCourseQuery.getSubjectId())){
                courseQueryWrapper.eq("subject_id",frontCourseQuery.getSubjectId());
        }
        if(!StringUtils.isEmpty(frontCourseQuery.getGmtModified())){
            courseQueryWrapper.orderByDesc("gmt_modified");

        }
        if(!StringUtils.isEmpty(frontCourseQuery.getPrice())){
            if("desc".equals(frontCourseQuery.getPrice())){
                courseQueryWrapper.orderByDesc("price");
            }else {
                courseQueryWrapper.orderByAsc("price");
            }
        }
        if(!StringUtils.isEmpty(frontCourseQuery.getBuyCount())){
            courseQueryWrapper.orderByDesc("buy_count");
        }
        baseMapper.selectPage(eduCoursePage, courseQueryWrapper);
        Map map=new HashMap();
        map.put("total",eduCoursePage.getTotal());
        map.put("items",eduCoursePage.getRecords());
        map.put("size",eduCoursePage.getSize());
        map.put("current",eduCoursePage.getCurrent());
        map.put("hasNext",eduCoursePage.hasNext());
        map.put("hasPrevious",eduCoursePage.hasPrevious());
        map.put("pages",eduCoursePage.getPages());
        return map;
    }

    @Override
    public CourseDetail getCourseDetail(String id) {
        CourseDetail baseCourseInfo = baseMapper.getBaseCourseInfo(id);
        return baseCourseInfo;
    }
}
