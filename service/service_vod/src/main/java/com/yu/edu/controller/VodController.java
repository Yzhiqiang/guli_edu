package com.yu.edu.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.yu.commonUtils.R;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.edu.service.VodService;
import com.yu.edu.utils.ConstantVodUtils;
import com.yu.edu.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.invoke.ConstantCallSite;
import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:43 2021/11/13
 * @Modified By:
 */
@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {

    @Autowired
    VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliYVideo")
    public R uploadAliYVideo(MultipartFile file) {
        //返回上传视频的id值
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }

    //根据视频id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo (@PathVariable String id) {
        Boolean flag = vodService.deleteVideoAly(id);
        if(flag) return R.ok();
        else {
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //根据视频的id获取视频的凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {

        try {
            //创建初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth", playAuth);
        } catch (ClientException e) {
            throw new GuliException(20001, "获取凭证失败");
        }
    }

//    @DeleteMapping("delete-batch")
//    public R deleteBatch(@RequestParam("videoIdList") List videoIdList) {
//        vodService.deleteBatch(videoIdList);
//        return R.ok();
//    }
}
