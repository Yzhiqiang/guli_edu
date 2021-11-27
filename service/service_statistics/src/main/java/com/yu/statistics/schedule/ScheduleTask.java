package com.yu.statistics.schedule;


import com.yu.statistics.service.StatisticsDailyService;
import com.yu.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 14:57 2021/11/27
 * @Modified By:
 */
@Component
public class ScheduleTask {

    @Autowired
    private StatisticsDailyService staService;

    //每隔5秒执行一次该方法， 只能是6位，不能是7位
    @Scheduled(cron = "0/5 * * * * ?")
    public void task1() {
        System.out.println(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

    //每天凌晨1点，把前一天数据进行添加
    @Scheduled(cron = "0 0 1 * * ? ")
    public void task2() {
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
