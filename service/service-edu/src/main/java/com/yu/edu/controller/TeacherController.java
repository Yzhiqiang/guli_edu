package com.yu.edu.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.commonUtils.R;
import com.yu.edu.entity.Teacher;
import com.yu.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-10-30
 */
@Api(value = "讲师列表")
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/list")
    public R findAllTeachers() {
        List<Teacher> teaList = teacherService.list(null);
        return R.ok().data("items", teaList);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id) {
        boolean flag = teacherService.removeById(id);
        return flag == true ? R.ok() : R.error();
    }

    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable("current") long current,
                             @ApiParam(name = "limit", value = "每页数", required = true) @PathVariable("limit") long limit) {

        Page<Teacher> teacherPage = new Page<>(current, limit);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

}

