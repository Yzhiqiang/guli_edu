package com.yu.oss.service.Impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.yu.oss.service.OssService;
import com.yu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 22:00 2021/11/2
 * @Modified By:
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String buckedName = ConstantPropertiesUtils.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String filename = file.getOriginalFilename();

        //防止上传相同的文件名，会覆盖之前的文件
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        filename = uuid + filename;


        //将文件按日期分类
        String datePath = new DateTime().toString("yyyy/MM/dd");

        filename = datePath + "/" + filename;
        try {
            InputStream inputStream = file.getInputStream();
            ossClient.putObject(buckedName, filename, inputStream);
            ossClient.shutdown();
            String url = "https://" + buckedName + "." + endpoint + "/" + filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
