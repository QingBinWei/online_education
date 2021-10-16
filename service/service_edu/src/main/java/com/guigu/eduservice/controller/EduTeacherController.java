package com.guigu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.EduTeacher;
import com.guigu.eduservice.entity.vo.TeacherQuery;
import com.guigu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师; InnoDB free: 8192 kB 前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-05-16
 */
@Api(description="讲师管理")
@Slf4j
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin
public class EduTeacherController {
    @Autowired
    EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ReturnResult findAllTeacher(){
        List<EduTeacher> list=eduTeacherService.list(null);
        return ReturnResult.ok().data("items",list);
    }
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("deleteTeacher/{id}")
    public ReturnResult deleteTeacher( @ApiParam(name = "id", value = "讲师ID", required = true)
                                      @PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return ReturnResult.ok();
        }else {
            return ReturnResult.fail();
        }
    }
    //分页查询
    @ApiOperation(value = "分页查询")
    @GetMapping("pageTeacher/{current}/{limit}")
    public ReturnResult pageTeacher(@ApiParam(name="current",value = "当前页",required = true)
                                    @PathVariable long current,
                                    @ApiParam(name="limit",value = "每页显示条数",required = true)
                                    @PathVariable long limit){
        Page<EduTeacher> page=new Page<>(current,limit);
        eduTeacherService.page(page,null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        Map map=new HashMap();
        map.put("total",total); //总记录数
        map.put("record",records);//当前页数据
        return ReturnResult.ok().data("items",map);
    }
    //条件分页查询
    @ApiOperation(value = "条件分页查询")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ReturnResult pageTeacherCondition(@ApiParam(name="current",value = "当前页",required = true)
                                    @PathVariable long current,
                                    @ApiParam(name="limit",value = "每页显示条数",required = true)
                                    @PathVariable long limit,
                                    @ApiParam(name="teacherQuery",value = "查询条件",required = false)
                                    @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page=new Page<>(current,limit);
        QueryWrapper<EduTeacher> queryWrapper=new QueryWrapper<>();
        //多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }
        queryWrapper.orderByDesc("gmt_create");
        eduTeacherService.page(page,queryWrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        Map map=new HashMap();
        map.put("total",total); //总记录数
        map.put("record",records);//当前页数据
        return ReturnResult.ok().data("items",map);
    }
    @ApiOperation(value = "添加讲师")
    @PostMapping("/inserTeacher")
    public ReturnResult insertTeacher(@ApiParam(name="eduTeacher",value = "讲师对象",required = true)
            @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return ReturnResult.ok();
        }else {
            return ReturnResult.fail();
        }
    }
    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("/selectTeacher/{id}")
    public ReturnResult selectTeacher(@ApiParam(name="id",value = "讲师id",required = true)
                                      @PathVariable String id){
        EduTeacher eduTeacher= eduTeacher = eduTeacherService.getById(id);
        log.info(eduTeacher.toString());
       /* try {
            int i=1/0;
        }catch (Exception e){  自定义异常  不执行return语句
         throw new GuLiEception(500,"除数不能为零");
        }*/
        return ReturnResult.ok().data("teacher",eduTeacher);
    }

    @GetMapping("/selectTeacherName/{id}")
    public String selectTeacherName(@ApiParam(name="id",value = "讲师id",required = true)
                                      @PathVariable String id){
        EduTeacher eduTeacher= eduTeacher = eduTeacherService.getById(id);
        log.info(eduTeacher.toString());
        String name = eduTeacher.getName();
        return name;
    }
    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateTeacher")
    public ReturnResult updateTeacher(@ApiParam(name="eduTeacher",value = "讲师对象",required = true)
                                      @RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.updateById(eduTeacher);
        if(save){
            return ReturnResult.ok();
        }
        return ReturnResult.fail();
    }

}

