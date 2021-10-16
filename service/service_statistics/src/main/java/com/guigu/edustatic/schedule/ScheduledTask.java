package com.guigu.edustatic.schedule;

import com.guigu.edustatic.service.StatisticsDailyService;
import com.guigu.edustatic.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;

    //从8点 每小时执行一次 清空热门课程缓存
    @CacheEvict(value = "famousCourse", key="'courseList'")
    @Scheduled(cron = "0 0 8/1 * * ?")
    public void removeFamousCourse() {
        System.out.println("**************task1执行了..");
    }
    //从8点 每小时执行一次 清空热门老师缓存
    @CacheEvict(value = "famousTeacher", key="'teacherList'")
    @Scheduled(cron = "0 0 8/1 * * ?")
    public void removeFamousTeacher() {
        System.out.println("**************task1执行了..");
    }

    //在每天凌晨1点，把前一天数据进行数据查询添加
    @Cacheable(value = "famousCourse", key="'courseList'")
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        staService.inset(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
