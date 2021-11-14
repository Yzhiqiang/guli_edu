package com.yu.edu.service.Impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.yu.edu.service.VodService;
import com.yu.edu.utils.ConstantVodUtils;
import com.yu.edu.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.yu.edu.utils.ConstantVodUtils.ACCESS_KEY_SECRET;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:45 2021/11/13
 * @Modified By:
 */
@Service
public class VodServiceImpl implements VodService{
    @Override
    public String uploadVideoAly(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String title =  fileName.substring(0, fileName.lastIndexOf('.'));
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            //title 上传之后的显示名称
            //filename  上传文件的原始名称
            //inputstream  上传文件输入流
            UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID, ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = response.getVideoId();
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteVideoAly(String id) {
        DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        request.setVideoIds(id);
        try {
            response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

//    @Override
//    public void deleteBatch(List videoIdList) {
//        try {
//            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
//            DeleteVideoRequest request = new DeleteVideoRequest();
//            DeleteVideoResponse response = new DeleteVideoResponse();
//
//            String ids = StringUtils.join(videoIdList.toArray(), ",");   //第一步将list装换成array数组，然后是分隔符
//            request.setVideoIds(ids);
//            response = client.getAcsResponse(request);
//            System.out.print("RequestId = " + response.getRequestId() + "\n");
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
}
