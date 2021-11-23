package com.yu.educenter.controller;

import com.google.gson.Gson;
import com.yu.commonUtils.JwtUtils;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.educenter.entity.UcenterMember;
import com.yu.educenter.service.UcenterMemberService;
import com.yu.educenter.utils.ConstantWxUtils;
import com.yu.educenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 12:28 2021/11/20
 * @Modified By:
 */
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    //获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {
        try {
            //1.获取token

            //使用token访问固定地址 获取access_token, openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            String  accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code);
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            //需要将accessTokenInfo字符串转换成map集合，根据map里面key获取对应值
            //使用json转换工具
            Gson gson = new Gson();
            HashMap map = gson.fromJson(accessTokenInfo, HashMap.class);
            String openid = (String) map.get("openid");
            String access_token = (String) map.get("access_token");

            UcenterMember member = memberService.getOpenIdMember(openid);
            //将扫描人信息添加数据库里面
            if(member == null) {
                //3 拿着得到的access_token 和 openid，再去请求微信提供固定的地址，获取到扫描人信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                //拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid);

                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                System.out.println(userInfoMap);
                //获取返回userinfo字符串扫描人信息
                String nickname = (String) userInfoMap.get("nickname");
                String headimgUrl = (String) userInfoMap.get("headimgurl");
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgUrl);
                memberService.save(member);
            }
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect://localhost:3000?token="+jwtToken;
        }catch(Exception e) {
            throw new GuliException(20001, "登录失败");
        }
    }
    //生成微信扫描的二维码
    @GetMapping("login")
    public String getWxCode() {
//固定地址，后面拼接参数
        //微信开放平台授权baseUrl， %s相当于占位符？，这个位置要传参数
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //对redirect_url进行URLEncoder编码
        String redirectUrl= ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try{
            redirectUrl= URLEncoder.encode(redirectUrl,"utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }

        //设置%s中的值
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "lujin");
        //请求一个微信地址
        return "redirect:"+url;
    }
}
