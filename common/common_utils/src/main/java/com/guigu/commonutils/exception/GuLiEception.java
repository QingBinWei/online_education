package com.guigu.commonutils.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//自定义异常
@Data  //生成get和set
@AllArgsConstructor //生成有参构造
@NoArgsConstructor
public class GuLiEception extends RuntimeException {
    private Integer code;
    private String message;
}
