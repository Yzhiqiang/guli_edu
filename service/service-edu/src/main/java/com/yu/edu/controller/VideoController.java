package com.yu.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.commonUtils.R;
import com.yu.edu.client.VodClient;
import com.yu.edu.entity.Video;
import com.yu.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-11-10
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    VideoService videoService;

    //将定义的接口注入进来
    @Autowired
    VodClient vodClient;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video) {
        videoService.save(video);
        return R.ok();
    }

    //删除小节（附带删除视频）
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        Video video = videoService.getById(id);
        String videoSourceId = video.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId))
            vodClient.removeAlyVideo(videoSourceId);    //远程调用实现调用
        videoService.removeById(id);
        return R.ok();
    }

}

