package com.guigu.eduservice.service.impl;

import com.guigu.commonutils.JwtUtils;
import com.guigu.commonutils.ReturnResult;
import com.guigu.commonutils.vo.EduCourseVo;
import com.guigu.commonutils.vo.UcenterMemberVo;
import com.guigu.eduservice.client.EduCourseClient;
import com.guigu.eduservice.client.UcenterClient;
import com.guigu.eduservice.entity.TOrder;
import com.guigu.eduservice.mapper.TOrderMapper;
import com.guigu.eduservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guigu.eduservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-18
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Autowired
    EduCourseClient eduCourseClient;
    @Autowired
    UcenterClient ucenterClient;
    @Override
    public String createOrder(String courseId, HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        EduCourseVo courseInfoOrder = eduCourseClient.getCourse(courseId);
        UcenterMemberVo userInfoVo = ucenterClient.getUserInfo(userId);
        TOrder order=new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        String teacherName=eduCourseClient.selectTeacherName(courseInfoOrder.getTeacherId());
        order.setTeacherName(teacherName);
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(userId);
        order.setMobile(userInfoVo.getMobile());
        order.setNickname(userInfoVo.getNickname());
        order.setStatus(0);  //订单状态（0：未支付 1：已支付）
        order.setPayType(1);  //支付类型 ，微信1
        baseMapper.insert(order);
        //返回订单号
        return order.getOrderNo();
    }
}
