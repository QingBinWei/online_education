package com.guigu.eduservice.controller;

import com.guigu.commonutils.ReturnResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j

@Api("后台登录")
@RestController
@RequestMapping("eduservice/user")
//@CrossOrigin  //解决跨域问题（前后台 协议 IP 端口号不同）
public class EduLoginController {
    @ApiOperation(value = "模拟登录")
    @PostMapping("/login")
    public ReturnResult login(){
        return ReturnResult.ok().data("token","admin");
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping ("/info")
    public ReturnResult getUserInfo(){
        return ReturnResult.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public ReturnResult logout(){
        return ReturnResult.ok();
    }

}
