package com.yu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.edu.client.VodClient;
import com.yu.edu.entity.Video;
import com.yu.edu.mapper.VideoMapper;
import com.yu.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-11-10
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        //根据课程id查出所有的视频id
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.select("video_source_id");
        List<Video> videos = baseMapper.selectList(wrapper);
        List<String> videoIds = new ArrayList<>();
        for (Video video : videos) {
            if (video.getVideoSourceId() != null)
                videoIds.add(video.getVideoSourceId());
        }
        String ids = StringUtils.join(videoIds.toArray(), ",");
        if (!StringUtils.isEmpty(ids)) {
            vodClient.removeAlyVideo(ids);
        }
        QueryWrapper<Video> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id", courseId);
        baseMapper.delete(wrapper1);
    }
}
