package com.yu.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.commonUtils.R;
import com.yu.edu.entity.Course;
import com.yu.edu.entity.EduTeacher;
import com.yu.edu.service.CourseService;
import com.yu.edu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 9:48 2021/11/23
 * @Modified By:
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private CourseService courseService;

    //分页查询所有讲师
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page,@PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);

        //返回分页的所有数据
        return R.ok().data(map);
    }

    //讲师详细信息
    @GetMapping("getTeacherFrontInfo/{id}")
    public R getTeacherFrontInfo(@PathVariable long id) {
        //查询讲师信息
        EduTeacher teacher = teacherService.getById(id);

        //查询讲师课程信息
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", id);
        List<Course> list = courseService.list(wrapper);
        return R.ok().data("teacher",teacher).data("courseList", list);
    }
}
