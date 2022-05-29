package com.yu.vodtes;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 21:48 2021/11/12
 * @Modified By:
 */
public class TestVod {
    public static void main(String[] args) {

        String title = "6 - What If I Want to Move Faster.mp4--upload by sdk";
        String fileName = "E:/6 - What If I Want to Move Faster.mp4";
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    //根据视频的id获得视频的播放凭证，可以通过此方式播放加密的视频
    public static void getPlayAuth () {
        //2.获取视频的播放凭证
        DefaultAcsClient client = InitObject.initVodClient("", "");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
//         request.setVideoId("");
        try {
            response = client.getAcsResponse(request);
            //播放凭证
            System.out.println("PlayAuth= "+ response.getPlayAuth());

            //videoMeta信息
            System.out.println("VideoMeta.Title = "+response.getVideoMeta().getTitle() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println("RequestId="+response.getRequestId());
    }
    //根据视频id获得视频的地址
    public static void GetVedioAddress () {
        //1.根据视频Id获取视频播放地址
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tEawQ7krqZzuj2K6YXs", "4Rt948uQZUtydwF2Qn5PerFGViFtZi");
        //向request对象里面设置视频id
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        request.setVideoId("");
        try {
            response = client.getAcsResponse(request);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //获取播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.println("PlayInfo.PlayURL = "+playInfo.getPlayURL());
            }

            //Base信息
            System.out.println("VideoBase.title = "+response.getVideoBase().getTitle() + "\n");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //第哦啊用初始化对象里面的方法，传递request，获取数据
    }
}
