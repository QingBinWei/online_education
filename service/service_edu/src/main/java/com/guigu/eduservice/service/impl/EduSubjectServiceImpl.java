package com.guigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.eduservice.entity.EduSubject;
import com.guigu.eduservice.entity.subject.OneSubject;
import com.guigu.eduservice.entity.subject.TwoSubject;
import com.guigu.eduservice.entity.excel.SubjectData;
import com.guigu.eduservice.listener.ExcelListener;
import com.guigu.eduservice.mapper.EduSubjectMapper;
import com.guigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目; InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author weiqb
 * @since 2020-05-24
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    public void redaExcel(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in=file.getInputStream();
            EasyExcel.read(in, SubjectData.class, new ExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){

        }
    }
    //加载课程机构树
    @Override
    public List<OneSubject> getAllOneMoreTwoSubject() {
        List<OneSubject> oneSubjectList=new ArrayList<>();
        List<TwoSubject> twoSubjectList=null;
        List<EduSubject> eduSubjectList = baseMapper.selectList(null);
        for (int i=0;i<eduSubjectList.size();i++){
            OneSubject oneSubject=new OneSubject();
            if("0".equals(eduSubjectList.get(i).getParentId())){
                 twoSubjectList=new ArrayList<>();
                 oneSubject.setId(eduSubjectList.get(i).getId());
                 oneSubject.setTitle(eduSubjectList.get(i).getTitle());
                 for(int j=0;j<eduSubjectList.size();j++){
                     if(eduSubjectList.get(i).getId().equals(eduSubjectList.get(j).getParentId())){
                         TwoSubject twoSubject=new TwoSubject();
                         twoSubject.setId(eduSubjectList.get(j).getId());
                         twoSubject.setTitle(eduSubjectList.get(j).getTitle());
                         //BeanUtils.copyProperties等同于把eduSubjectList.get(j)的值赋给twoSubject
                         //BeanUtils.copyProperties(eduSubjectList.get(j),twoSubject);
                         twoSubjectList.add(twoSubject);
                     }
                 }
                 oneSubject.setChildren(twoSubjectList);
                 oneSubjectList.add(oneSubject);
            }
        }
        return oneSubjectList;
    }

    @Override
    public boolean existSubject(EduSubject eduSubject,String flag) {
        QueryWrapper<EduSubject> queryWrapper=new QueryWrapper<>();
        if("title".equals(flag)){
            queryWrapper.eq("title",eduSubject.getTitle());
        }else{
            queryWrapper.eq("title",eduSubject.getParentId());
        }
        EduSubject eduSubject1 = baseMapper.selectOne(queryWrapper);
        return eduSubject1==null;
    }

}
