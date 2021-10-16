package com.guigu.eduservice.entity.vo;

import com.guigu.eduservice.entity.EduCourse;
import lombok.Data;

@Data
public class EduCourseAndDescription {
    private String description;
    private EduCourse eduCourse;
}
