package com.guigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.eduservice.entity.EduSubject;
import com.guigu.eduservice.entity.excel.SubjectData;
import com.guigu.eduservice.service.EduSubjectService;

import java.util.Map;

//创建读取excel监听器
public class ExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService eduSubjectService;

    public ExcelListener( ) {
    }
    public ExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //一行一行去读取excle内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        EduSubject oneSubject=null;
        if(subjectData==null){
           throw new GuLiEception(20011,"文件数据为空");
       }
       //判断一级学科和二级学科分别是否重复
         oneSubject = getOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (oneSubject==null) {
            EduSubject eduSubject=new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(eduSubject);
        }
        oneSubject = getOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        EduSubject twoSubject = getTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(),oneSubject.getId());
        if (twoSubject==null) {
            EduSubject eduSubject=new EduSubject();
            eduSubject.setParentId(oneSubject.getId());
            eduSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(eduSubject);
        }
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    private static EduSubject getOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id","0");
        EduSubject oneSubject= eduSubjectService.getOne(queryWrapper);
        return oneSubject;
    }

    private static EduSubject getTwoSubject(EduSubjectService eduSubjectService,String name,String parentId){
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("title",name);
        queryWrapper.eq("parent_id",parentId);
        EduSubject twoSubject= eduSubjectService.getOne(queryWrapper);
        return twoSubject;
    }

}