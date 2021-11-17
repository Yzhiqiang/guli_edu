package com.yu.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.commonUtils.R;
import com.yu.edu.entity.Course;
import com.yu.edu.entity.EduTeacher;
import com.yu.edu.service.CourseService;
import com.yu.edu.service.EduTeacherService;
import com.yu.edu.service.IndexFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 22:14 2021/11/15
 * @Modified By:
 */
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    private IndexFrontService indexFrontService;

    @GetMapping("index")
    public R index() {
        List<Course> courseList = courseService.indexCourse();
        List<EduTeacher> teacherList = teacherService.indexTeacher();
        return R.ok().data("eduList", courseList).data("teacherList", teacherList);
    }
}
