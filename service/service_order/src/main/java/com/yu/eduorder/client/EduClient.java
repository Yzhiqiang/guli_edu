package com.yu.eduorder.client;

import com.yu.commonUtils.ordervo.CourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:08 2021/11/25
 * @Modified By:
 */
@Component
@FeignClient(name = "service-edu")
public interface EduClient {
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{courseid}")
    public CourseOrder getCourseInfoOrder(@PathVariable("courseid") String courseid);   //远程掉用PathVariable注解必须加参数
}
