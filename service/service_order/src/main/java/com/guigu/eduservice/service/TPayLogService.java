package com.guigu.eduservice.service;

import com.guigu.eduservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-18
 */
public interface TPayLogService extends IService<TPayLog> {

    Map creCode(String orderNum);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrdersStatus(Map<String, String> map);
}
