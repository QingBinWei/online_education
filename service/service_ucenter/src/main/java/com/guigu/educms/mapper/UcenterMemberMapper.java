package com.guigu.educms.mapper;

import com.guigu.educms.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author weiqb
 * @since 2020-06-07
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    int selCount(String day);
}
