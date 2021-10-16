package com.guigu.eduservice.client;

import com.guigu.commonutils.vo.EduCourseVo;
import com.guigu.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "service-edcenter")
@Component
public interface UcenterClient {
    @PostMapping("/educenter/member/getUserInfoOrder/{userId}")
    public UcenterMemberVo getUserInfo(@PathVariable("userId") String userId);

}
