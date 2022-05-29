package com.yu.msm_edu.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yu.msm_edu.service.MsmService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 13:23 2021/11/17
 * @Modified By:
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        //设置相关固定的参数
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //设置发送方式
//        request.putQueryParameter("PhoneNumbers",phone); //手机号
//        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
//
//        try {
//            //最终发送
//            CommonResponse response = client.getCommonResponse(request);
//            boolean success = response.getHttpResponse().isSuccess();
//            return success;
//        }catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }
        HtmlEmail email=new HtmlEmail();//创建一个HtmlEmail实例对象
        email.setHostName("smtp.163.com");//邮箱的SMTP服务器，一般123邮箱的是smtp.123.com,qq邮箱为smtp.qq.com
        email.setCharset("utf-8");//设置发送的字符类型
        try {
            email.addTo("yu_zhiqiang123@163.com");
            email.setFrom("yu_zhiqiang123@163.com","于先生");//发送人的邮箱为自己的，用户名可以随便填
            email.setAuthentication("yu_zhiqiang123@163.com","");//设置发送人到的邮箱和用户名和授权码(授权码是自己设置的)
            email.setSubject("登录验证码");//设置发送主题
            email.setMsg(phone+"   验证码如下："+param.get("code"));//设置发送内容
            email.send();//进行发送
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
