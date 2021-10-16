package com.guigu.msm.controller;

import com.guigu.commonutils.ReturnResult;
import com.guigu.msm.service.MsmService;
import com.guigu.msm.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin
public class MsmController {
    @Autowired
    private MsmService msmService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("code/{phone}")
    public ReturnResult sendMessage(@PathVariable String phone){


        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(StringUtils.isEmpty(code)) {
            code = RandomUtil.getFourBitRandom();
        }
        //2 如果redis获取 不到，进行阿里云发送
        //生成随机值，传递阿里云进行发送
        Map<String,Object> map=new HashMap<>();
        map.put("code",code);
        boolean isSend=msmService.sendMessage(map,phone);
        if(isSend==true){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return ReturnResult.ok();
        }else {
            return ReturnResult.fail().message("发送失败");
        }
    }

}
