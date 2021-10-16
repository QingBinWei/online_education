package com.guigu.educms.service;

import com.guigu.educms.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guigu.educms.entity.vo.RegisterVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-07
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String toLogin(UcenterMember ucenterMember);

    void registerUser(RegisterVo registerVo);

    Integer selCount(String day);
}
