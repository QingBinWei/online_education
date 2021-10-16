package com.guigu.eduservice.client;

import com.guigu.commonutils.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-vod",fallback = VodClientimpl.class)
@Component
public interface VodClient {
    @DeleteMapping("/eduvod/video/delVideo/{id}")
    public ReturnResult delVideo(@PathVariable("id") String id);
}
