package com.yu.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:45 2021/11/13
 * @Modified By:
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    Boolean deleteVideoAly(String id);

//    void deleteBatch(List videoIdList);
}
