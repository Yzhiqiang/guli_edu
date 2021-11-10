package com.yu.edu.service.impl;

import com.yu.edu.entity.CourseDescription;
import com.yu.edu.entity.vo.CourseInfoVo;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.edu.mapper.CourseDescriptionMapper;
import com.yu.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {
//    @Override
//    public void addCourseDescription(CourseInfoVo courseInfoVo) {
//        CourseDescription courseDescription = new CourseDescription();
//        BeanUtils.copyProperties(courseInfoVo, courseDescription);
//        int flag = baseMapper.insert(courseDescription);
//        if(flag == 0) {
//            throw new GuliException(20001, "添加课程简介信息失败");
//        }
//    }
}
