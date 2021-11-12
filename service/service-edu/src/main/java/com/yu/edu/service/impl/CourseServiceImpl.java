package com.yu.edu.service.impl;

import com.yu.edu.entity.Course;
import com.yu.edu.entity.CourseDescription;
import com.yu.edu.entity.vo.CourseInfoVo;
import com.yu.edu.entity.vo.CoursePublishVo;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.edu.mapper.CourseMapper;
import com.yu.edu.service.ChapterService;
import com.yu.edu.service.CourseDescriptionService;
import com.yu.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

    //添加课程基本信息
    @Override
    public String addCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        //CourseInfoVo对象转换为eduCourse
        Course course = new Course();

        BeanUtils.copyProperties(courseInfoVo, course);

        int insert = baseMapper.insert(course);

        if(insert == 0) {
            throw new GuliException(20001, "添加课程信息失败");
        }
        //获取添加之后课程id
        String cid = course.getId();
        //添加课程简介信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        Course course = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);

        CourseDescription description = courseDescriptionService.getById(courseId);

        courseInfoVo.setDescription(description.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoVo, course);
        int flag = baseMapper.updateById(course);
        if (flag == 0) {
            throw new GuliException(20001, "修改课程失败");
        }

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfoVo, courseDescription);
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getPublicCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        courseDescriptionService.removeById(courseId);
        //根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new GuliException(20001, "删除课程失败");
        }
    }
}
