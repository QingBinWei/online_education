package com.guigu.educms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.JwtUtils;
import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.commonutils.vo.UcenterMemberVo;
import com.guigu.educms.entity.UcenterMember;
import com.guigu.educms.entity.vo.RegisterVo;
import com.guigu.educms.service.UcenterMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
@Api(description="登录")
public class UcenterMemberController {
     @Autowired
    private UcenterMemberService ucenterMemberService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("selPhone/{phone}")
    public ReturnResult selPhone(@PathVariable String phone){
        QueryWrapper<UcenterMember> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        List<UcenterMember> list = ucenterMemberService.list(wrapper);
        if(list.size()>0){
            return ReturnResult.fail();
        }else {
            return ReturnResult.ok();
        }
    }
     @PostMapping("reginster")
    public ReturnResult registerUser(@RequestBody RegisterVo registerVo){
         ucenterMemberService.registerUser(registerVo);
         return ReturnResult.ok();
     }
    @PostMapping("login")
    public ReturnResult login(@RequestBody UcenterMember ucenterMember,HttpServletRequest req){
        HttpSession session = req.getSession();
        String token=ucenterMemberService.toLogin(ucenterMember);
         Map map=new HashMap();
        if(redisTemplate.hasKey(ucenterMember.getMobile()+"1")){
         map.put("haslogon",true);
        }else {
            map.put("haslogon",false);
        }
        map.put("token",token);
        redisTemplate.opsForValue().set(ucenterMember.getMobile()+"1",token);
        return ReturnResult.ok().data("item",map);
    }
    @GetMapping("getUserInfo")
    public ReturnResult getUserInfo(HttpServletRequest req){
        String userId = JwtUtils.getMemberIdByJwtToken(req);
        UcenterMember ucenterMember = ucenterMemberService.getById(userId);
        return ReturnResult.ok().data("userInfo",ucenterMember);
    }
    @PostMapping("getUserInfoOrder/{userId}")
    public UcenterMemberVo getUserInfo(@PathVariable String userId){
        UcenterMember ucenterMember=ucenterMemberService.getById(userId);
        UcenterMemberVo ucenterMemberVo=new UcenterMemberVo();
        BeanUtils.copyProperties(ucenterMember,ucenterMemberVo);
        return ucenterMemberVo;
    }
    @GetMapping("countRegister/{day}")
    public Integer selCount(@PathVariable String day){
         Integer count=ucenterMemberService.selCount(day);
         return count;
    }


}

