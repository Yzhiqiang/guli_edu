package com.yu.edu.mapper;

import com.yu.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.edu.entity.frontvo.CourseWebvo;
import com.yu.edu.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    public CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebvo getBaseCourseInfo(String courseId);
}
