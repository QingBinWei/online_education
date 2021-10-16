package com.guigu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.eduservice.entity.EduChapter;
import com.guigu.eduservice.entity.EduVideo;
import com.guigu.eduservice.entity.chapter.Chapter;
import com.guigu.eduservice.entity.chapter.Video;
import com.guigu.eduservice.mapper.EduChapterMapper;
import com.guigu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    EduVideoService eduVideoService;
    @Override
    public List<Chapter> getChapterAndVideo(String courseId) {
        List<Chapter> chapterList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("course_id",courseId);
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.orderByAsc("sort");
        List<EduChapter> eduChapterList = baseMapper.selectList(queryWrapper);
        List<EduVideo> eduVideoList = eduVideoService.list(null);
        for (int i=0;i<eduChapterList.size();i++){
            List<Video> videoList=new ArrayList<>();
            List<Video> sortVideoList=new ArrayList<>();
            Chapter chapter=new Chapter();
            chapter.setId(eduChapterList.get(i).getId());
            chapter.setTitle(eduChapterList.get(i).getTitle());
            for(int j=0;j<eduVideoList.size();j++){
                   if(eduChapterList.get(i).getId().equals(eduVideoList.get(j).getChapterId())){
                       Video video=new Video();
                       video.setId(eduVideoList.get(j).getId());
                       video.setTitle(eduVideoList.get(j).getTitle());
                       video.setSort(eduVideoList.get(j).getSort());
                       video.setVideoSourceId(eduVideoList.get(j).getVideoSourceId());
                       video.setIsFree(eduVideoList.get(j).getIsFree());
                       videoList.add(video);
                    }
            }
            if(videoList.size()>0){   //小节排序
                for(int m=0;m<videoList.size()-1;m++){
                    for(int n=0;n<videoList.size()-1-m;n++){
                        if(videoList.get(n).getSort()>videoList.get(n+1).getSort()){
                            Video video=new Video();
                            video=videoList.get(n);
                            videoList.set(n,videoList.get(n+1));
                            videoList.set(n+1,video);
                        }
                    }
                }
            }
            chapter.setVideoList(videoList);
            chapterList.add(chapter);
        }
        return chapterList;
    }
}
