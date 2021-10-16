package com.guigu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduSubject;
import com.guigu.eduservice.entity.subject.OneSubject;
import com.guigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-05-24
 */
@Api(description="读取excel")
@Slf4j
@RestController
//@CrossOrigin
@RequestMapping("/eduservice/subject")
public class EduSubjectController {
    @Autowired
   private EduSubjectService eduSubjectService;

    @ApiOperation(value = "读取excel")
    @PostMapping("/readExcel")
    public ReturnResult addSubject(@ApiParam(name = "file", value = "excel表", required = true)MultipartFile file){
        eduSubjectService.redaExcel(file,eduSubjectService);
        return ReturnResult.ok();
    }
    @ApiOperation(value = "显示所有课程")
    @GetMapping("/findall")
    public ReturnResult findAllSubject(){
        log.error("胡汉三又回来了***************************************");
        List<OneSubject> list=eduSubjectService.getAllOneMoreTwoSubject();
        return ReturnResult.ok().data("list",list);
    }
    @ApiOperation(value = "添加课程")
    @PostMapping("/addSubject")
    public ReturnResult insertTeacher(@ApiParam(name="eduSubject",value = "添加课程",required = true)
                                      @RequestBody EduSubject eduSubject){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",eduSubject.getTitle());
        boolean existOneSubject;
        boolean existTwoSubject;
        EduSubject oneSubject=new EduSubject();
        EduSubject queryOneSubject=new EduSubject();
        EduSubject twoSubject=new EduSubject();
        existOneSubject= eduSubjectService.existSubject(eduSubject,"title");
        if(!StringUtils.isEmpty(eduSubject.getParentId())){   //二级课程不为空
            existTwoSubject= eduSubjectService.existSubject(eduSubject,"partentId");
            if(existOneSubject){  //一级课程不存在
                oneSubject.setTitle(eduSubject.getTitle());
                eduSubjectService.save(oneSubject);
                twoSubject.setTitle(eduSubject.getParentId());
                queryOneSubject= eduSubjectService.getOne(queryWrapper);
                twoSubject.setParentId(queryOneSubject.getId());
                eduSubjectService.save(twoSubject);
                return ReturnResult.ok().message("保存成功");
            }else{
                if(existTwoSubject){   //二级课程不存在
                    twoSubject.setTitle(eduSubject.getParentId());
                    queryOneSubject= eduSubjectService.getOne(queryWrapper);
                    twoSubject.setParentId(queryOneSubject.getId());
                    eduSubjectService.save(twoSubject);
                    return ReturnResult.ok().message("保存成功");
                }else{
                    return ReturnResult.fail().message("二级课程已存在");
                }
            }

        }else {
            if(existOneSubject){  //一级课程不存在
                oneSubject.setTitle(eduSubject.getTitle());
                eduSubjectService.save(oneSubject);
                return ReturnResult.ok().message("保存成功");
            }else {
                return ReturnResult.fail().message("一级课程已存在");
            }

        }

    }
}

