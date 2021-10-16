package com.guigu.edustatic.controller;


import com.guigu.commonutils.ReturnResult;
import com.guigu.edustatic.client.UcenterClient;
import com.guigu.edustatic.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weiqb
 * @since 2020-06-24
 */
@Api(description="统计管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/edustatic/sta")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    
    @PostMapping("selCountRegister/{day}")
    public ReturnResult selCount(@PathVariable String day){
        statisticsDailyService.inset(day);
        return ReturnResult.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public ReturnResult showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        return ReturnResult.ok().data(map);
    }
    
}

