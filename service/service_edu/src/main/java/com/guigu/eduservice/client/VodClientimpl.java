package com.guigu.eduservice.client;

import com.guigu.commonutils.ReturnResult;
import org.springframework.stereotype.Component;

@Component
public class VodClientimpl implements VodClient {
    @Override
    public ReturnResult delVideo(String id) {
        System.out.println("*执行熔断机制********************");
        return ReturnResult.fail().message("删除视频失败,执行熔断器&&&&&&&&&&&&&");
    }
}
