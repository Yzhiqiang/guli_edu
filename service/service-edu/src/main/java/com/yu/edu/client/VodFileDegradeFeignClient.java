package com.yu.edu.client;

import com.yu.commonUtils.R;
import org.springframework.stereotype.Component;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:14 2021/11/14
 * @Modified By:
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{

    //如果服务出错后才执行该方法
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }
}
