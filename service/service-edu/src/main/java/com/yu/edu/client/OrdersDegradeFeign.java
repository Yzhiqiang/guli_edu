package com.yu.edu.client;

import org.springframework.stereotype.Component;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 10:25 2021/11/27
 * @Modified By:
 */
@Component
public class OrdersDegradeFeign implements OrdersClient{
    @Override
    public boolean isBuyCourse(String courseId, String userid) {
        System.out.println("查询课程是否购买失败");
        return false;
    }
}
