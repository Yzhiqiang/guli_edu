package com.yu.edu.controller;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.commonUtils.R;
import com.yu.edu.entity.Teacher;
import com.yu.edu.entity.vo.TeacherQuery;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@CrossOrigin
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

    @ApiOperation(value = "分页查询所有讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable("current") long current,
                             @ApiParam(name = "limit", value = "每页数", required = true) @PathVariable("limit") long limit) {

        Page<Teacher> teacherPage = new Page<>(current, limit);
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    /**
     * 条件查询带分页的方法
     * @param current
     * @param limit
     * @param teacherQuery
     * @return
     *
     * @RequestBody  将接收到的json数据封装成对象，必须使用Post方式
     */
    @ApiOperation("按条件分页查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable long current,
                                  @ApiParam(name = "limit", value = "每页数", required = true) @PathVariable long limit,
                                  @RequestBody(required = false)  TeacherQuery teacherQuery)  {
        Page<Teacher> teacherPage = new Page<>();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        queryWrapper.orderByDesc("gmt_create");
        teacherService.page(teacherPage, queryWrapper);

        long total = teacherPage.getTotal();
        List<Teacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("records", records);
    }

    @ApiOperation("添加讲师")
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody  Teacher teacher) {
        Boolean flag = teacherService.save(teacher);
        return flag == true ? R.ok() : R.error();
    }

    @ApiOperation("根据讲师ID进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @ApiOperation("讲师修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@ApiParam(name = "teacher", value = "讲师对象", required = true)@RequestBody Teacher teacher) {
         return teacherService.updateById(teacher) == true ? R.ok() : R.error();
    }

}

