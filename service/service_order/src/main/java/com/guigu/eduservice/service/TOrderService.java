package com.guigu.eduservice.service;

import com.guigu.eduservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-18
 */
public interface TOrderService extends IService<TOrder> {

    String createOrder(String courseId, HttpServletRequest request);
}
