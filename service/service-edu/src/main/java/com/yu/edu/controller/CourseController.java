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
        courseService.addCourseInfo(courseInfoVo);
        return R.ok();
    }
}

