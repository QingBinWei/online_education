package com.guigu.commonutils.exception;

import com.guigu.commonutils.ReturnResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GloableException {
    //全局异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ReturnResult error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return ReturnResult.fail().message(e.getMessage());

    }
    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ReturnResult error(ArithmeticException e){
        e.printStackTrace();
        return ReturnResult.fail().message("执行了特定异常");

    }
    //自定义异常
    @ExceptionHandler(GuLiEception.class)
    @ResponseBody
    public ReturnResult error(GuLiEception e){
        e.printStackTrace();
        return ReturnResult.fail().code(e.getCode()).message(e.getMessage());

    }
}
