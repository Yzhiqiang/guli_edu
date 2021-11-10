package com.yu.edu.service.impl;

import com.yu.edu.entity.Video;
import com.yu.edu.mapper.VideoMapper;
import com.yu.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
