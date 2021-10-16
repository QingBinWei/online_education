package com.guigu.eduservice.client;

import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.vo.EduCourseVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "service-edu")
@Component
public interface EduCourseClient {
    @PostMapping("/eduservice/course/getCourse/{id}")
    public EduCourseVo getCourse(@PathVariable("id") String id);
    @GetMapping("/eduservice/teacher/selectTeacherName/{id}")
    public String selectTeacherName( @PathVariable("id") String id);
}
