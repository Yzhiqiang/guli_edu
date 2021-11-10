package com.yu.edu.controller;


import com.yu.commonUtils.R;
import com.yu.edu.entity.vo.CourseInfoVo;
import com.yu.edu.service.CourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("addCourseInfo")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.addCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    //根据课程查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }




}

