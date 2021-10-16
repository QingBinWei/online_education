package com.guigu.eduservice.service;

import com.guigu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目; InnoDB free: 8192 kB 服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-24
 */
public interface EduSubjectService extends IService<EduSubject> {

    void redaExcel(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneMoreTwoSubject();

    boolean existSubject(EduSubject eduSubject,String flag);
}
