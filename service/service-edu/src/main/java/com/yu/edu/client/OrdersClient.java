package com.yu.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:31 2021/11/26
 * @Modified By:
 */
@Component
@FeignClient(name = "service-order", fallback = OrdersDegradeFeign.class)
public interface OrdersClient {
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{userid}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("userid") String userid);
}
