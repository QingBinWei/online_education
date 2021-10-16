package com.guigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Video {
    private String id;
    private String title;
    private Integer sort;
    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;
    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;
}

