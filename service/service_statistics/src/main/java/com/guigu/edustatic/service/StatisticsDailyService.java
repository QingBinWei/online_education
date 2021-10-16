package com.guigu.edustatic.service;

import com.guigu.edustatic.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weiqb
 * @since 2020-06-24
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void inset(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
