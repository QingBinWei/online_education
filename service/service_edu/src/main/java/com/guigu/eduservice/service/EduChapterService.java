package com.guigu.eduservice.service;

import com.guigu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.eduservice.entity.chapter.Chapter;

import java.util.List;

/**
 * <p>
 * 课程; InnoDB free: 8192 kB 服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-25
 */
public interface EduChapterService extends IService<EduChapter> {

    List<Chapter> getChapterAndVideo(String courseId);
}
