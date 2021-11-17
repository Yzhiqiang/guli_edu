package com.yu.msm_edu.controller;

import com.yu.commonUtils.R;
import com.yu.msm_edu.service.MsmService;
import com.yu.msm_edu.utils.RandomUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.HashAttributeSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:20 2021/11/17
 * @Modified By:
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {
    private MsmService msmService;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //生成随机值，传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);
        if(isSend) {
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
