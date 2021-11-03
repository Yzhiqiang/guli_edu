package com.yu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:59 2021/11/2
 * @Modified By:
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
