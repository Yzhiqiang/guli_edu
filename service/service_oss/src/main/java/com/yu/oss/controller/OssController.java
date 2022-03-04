package com.yu.oss.controller;

import com.yu.commonUtils.R;
import com.yu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:59 2021/11/2
 * @Modified By:
 */
@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin
public class OssController {
    @Autowired
    OssService ossService;
    //上传头像的方法
    @PostMapping
    public R uploadOssFile(MultipartFile file)
    {
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
