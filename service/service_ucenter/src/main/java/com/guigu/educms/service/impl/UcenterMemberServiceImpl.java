package com.guigu.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guigu.commonutils.JwtUtils;
import com.guigu.commonutils.exception.GuLiEception;
import com.guigu.commonutils.exception.MD5;
import com.guigu.educms.entity.UcenterMember;
import com.guigu.educms.entity.vo.RegisterVo;
import com.guigu.educms.mapper.UcenterMemberMapper;
import com.guigu.educms.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-07
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String toLogin(UcenterMember ucenterMember) {
        String mobile=ucenterMember.getMobile();
        String password=ucenterMember.getPassword();

        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new GuLiEception(20001,"用户名或密码为空");
        }
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember queryUcenterMember = baseMapper.selectOne(queryWrapper);



        if(StringUtils.isEmpty(queryUcenterMember)){
            throw new GuLiEception(20001,"用户名或密码错误");
        }
        if(!MD5.encrypt(password).equals(queryUcenterMember.getPassword())){
            throw new GuLiEception(20001,"用户名或密码错误");
        }
        Boolean isDisabled = queryUcenterMember.getIsDisabled();
        if(isDisabled){
            throw new GuLiEception(20001,"此账号已被禁用，请联系管理员");
        }
        String token=JwtUtils.getJwtToken(queryUcenterMember.getId(),queryUcenterMember.getNickname());
        return token;
    }

    @Override
    public void registerUser(RegisterVo registerVo) {
        String code=registerVo.getCode();
        String phone=registerVo.getMobile();
        String name=registerVo.getNickname();
        String password=registerVo.getPassword();
        String validateCode=redisTemplate.opsForValue().get(phone);
        if(StringUtils.isEmpty(validateCode)){
            throw  new GuLiEception(20001,"验证码失效");
        }
        if(StringUtils.isEmpty(code)||StringUtils.isEmpty(password)||StringUtils.isEmpty(name)
        ){
            throw  new GuLiEception(20001,"登录失败");
        }
        if(StringUtils.isEmpty(validateCode)){
            throw  new GuLiEception(20001,"验证码失效");
        }
        if(!code.equals(validateCode)){
            throw  new GuLiEception(20001,"验证码错误");
        }
        QueryWrapper<UcenterMember> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("mobile",phone);
        int count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw  new GuLiEception(20001,"手机号已存在");
        }
        UcenterMember ucenterMember=new UcenterMember();
        ucenterMember.setMobile(phone);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setNickname(name);
        ucenterMember.setIsDisabled(false);
        baseMapper.insert(ucenterMember);
    }

    @Override
    public Integer selCount(String day) {
          Integer count=baseMapper.selCount(day);
        return count;
    }
}
