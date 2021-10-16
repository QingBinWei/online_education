package com.guigu.eduservice.controller;

import com.guigu.commonutils.ReturnResult;
import com.guigu.eduservice.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/eduorder/pay")
//@CrossOrigin
public class TPayLogController {
  @Autowired
    TPayLogService tPayLogService;

  @GetMapping("creCode/{orderNum}")
    public ReturnResult creCode(@PathVariable String orderNum){
      Map map=tPayLogService.creCode(orderNum);
      System.out.println(map);
      return ReturnResult.ok().data(map);
  }

  //查询订单支付状态
  //参数：订单号，根据订单号查询 支付状态
  @GetMapping("queryPayStatus/{orderNo}")
  public ReturnResult queryPayStatus(@PathVariable String orderNo) {
    Map<String,String> map = tPayLogService.queryPayStatus(orderNo);
    System.out.println("*****查询订单状态map集合:"+map);
    if(map == null) {
      return ReturnResult.fail().code(25001).message("支付出错了");
    }
    //如果返回map里面不为空，通过map获取订单状态
    if(map.get("trade_state").equals("SUCCESS")) {//支付成功
      //添加记录到支付表，更新订单表订单状态
      tPayLogService.updateOrdersStatus(map);
      return ReturnResult.ok().message("支付成功");
    }
    return ReturnResult.ok().code(25000).message("支付中");

  }
}

