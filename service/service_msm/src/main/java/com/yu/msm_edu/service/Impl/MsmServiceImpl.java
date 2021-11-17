package com.yu.msm_edu.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yu.msm_edu.service.MsmService;

import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:23 2021/11/17
 * @Modified By:
 */
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4FvvVEWiTJ3GNJJqJnk7", "9st82dv7EvFk9mTjYO1XXbM632fRbG");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送方式
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
        return false;
    }
}
