package com.yu.edu.service;

import com.yu.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.edu.entity.vo.CourseInfoVo;
import com.yu.edu.entity.vo.CoursePublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
public interface CourseService extends IService<Course> {

    String addCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getPublicCourseInfo(String id);

    void removeCourse(String courseId);

    List<Course> indexCourse();
}