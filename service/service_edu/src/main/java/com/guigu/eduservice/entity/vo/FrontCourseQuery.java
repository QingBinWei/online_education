package com.guigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCourseQuery implements Serializable {
    @ApiModelProperty(value = "类别名称")
    private String subjectParentId;
    private String subjectId;
    @ApiModelProperty(value = "销量")
    private String buyCount;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private String price;
    @ApiModelProperty(value = "更新时间")
    private String gmtModified;
}
