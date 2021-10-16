package com.guigu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.JwtUtils;
import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.entity.TOrder;
import com.guigu.eduservice.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class TOrderController {
    @Autowired
    TOrderService tOrderService;
    @PostMapping("creOrder/{courseId}")
    public ReturnResult createOrder(@PathVariable String courseId, HttpServletRequest request){
        String orderNumber=tOrderService.createOrder(courseId,request);
        return ReturnResult.ok().data("item",orderNumber);
    }
    @GetMapping("selOrder/{orderNumber}")
    public ReturnResult selOrder(@PathVariable String orderNumber){
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("order_no",orderNumber);
        TOrder order = tOrderService.getOne(wrapper);
        return ReturnResult.ok().data("item",order);
    }
    @GetMapping("isPay/{courseId}")
    public ReturnResult selIsPay(@PathVariable String courseId,HttpServletRequest request){
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        QueryWrapper<TOrder> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",userId);
        wrapper.eq("status",1);
       int count = tOrderService.count(wrapper);
        if(count>0){
            return ReturnResult.ok();
        }else{
            return ReturnResult.fail();
        }
    }
}

