package com.yu.edu.controller;

import com.yu.commonUtils.R;
import com.yu.edu.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:43 2021/11/13
 * @Modified By:
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliYVideo")
    public R uploadAliYVideo(MultipartFile file) {
        //返回上传视频的id值
        String url = vodService.uploadVideoAly(file);
        return R.ok().data("url", url);
    }
}
