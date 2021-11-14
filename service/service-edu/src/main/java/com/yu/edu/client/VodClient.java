package com.yu.edu.client;

import com.yu.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 12:16 2021/11/14
 * @Modified By:
 */
@Component
@FeignClient("service-vod")   //根据服务名称远程调用，相当于为接口创建实现类
public interface VodClient {
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo (@PathVariable("id") String id);    //此处一定要指定参数的名称

//    @DeleteMapping("/eduvod/video/delete-batch")
//    public R deleteBatch(@RequestParam("videoIdList") List videoIdList);    //删除多个视频
}
