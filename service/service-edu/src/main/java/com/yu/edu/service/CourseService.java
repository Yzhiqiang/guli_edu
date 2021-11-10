package com.yu.edu.service;

import com.yu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.edu.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
public interface CourseService extends IService<Course> {

    void addCourseInfo(CourseInfoVo courseInfoVo);
}
