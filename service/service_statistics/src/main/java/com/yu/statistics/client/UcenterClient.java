package com.yu.statistics.client;

import com.yu.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 11:23 2021/11/27
 * @Modified By:
 */
@Component
@FeignClient(name = "service-ucenter")
public interface UcenterClient {
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable String day);
}
