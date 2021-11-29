package com.yu.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.commonUtils.JwtUtils;
import com.yu.commonUtils.R;
import com.yu.commonUtils.ordervo.CourseOrder;
import com.yu.edu.client.OrdersClient;
import com.yu.edu.entity.Course;
import com.yu.edu.entity.EduTeacher;
import com.yu.edu.entity.chapter.ChapterVo;
import com.yu.edu.entity.frontvo.CourseFrontVo;
import com.yu.edu.entity.frontvo.CourseWebvo;
import com.yu.edu.service.ChapterService;
import com.yu.edu.service.CourseService;
import com.yu.edu.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 9:48 2021/11/23
 * @Modified By:
 */
@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private OrdersClient ordersClient;

    //分页查询所有课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getCourseFrontList(@PathVariable long page,@PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo coursefrontvo) {    //require=false可以没有查询条件
        Page<Course> coursePage = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(coursePage, coursefrontvo);
        return R.ok().data(map);
    }

    //通过课程id获取课程详细信息
    @GetMapping("getFrontCourseInfo/{courseid}")
    public R getFrontCourseInfo(@PathVariable String courseid, HttpServletRequest request) {
        CourseWebvo courseWebvo = courseService.getBaseCourseInfo(courseid);

        //根据课程id查询章节、小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseid);

        if(courseWebvo.getPrice().equals(0)) {
            return R.ok().data("courseWebVo", courseWebvo).data("chapterVideoList", chapterVideoList);
        }
        String userid = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("用户的id为================"+userid);
        //根据课程id和用户id查询当前课程否已经被购买过了
        if(!StringUtils.isEmpty(userid)){
            boolean buyCourse = ordersClient.isBuyCourse(courseid, userid);
            return R.ok().data("courseWebVo", courseWebvo).data("chapterVideoList", chapterVideoList).data("isBuy", buyCourse);
        }
        return R.ok().data("courseWebVo", courseWebvo).data("chapterVideoList", chapterVideoList).data("isBuy",false);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{courseid}")
    public CourseOrder getCourseInfoOrder(@PathVariable String courseid) {
        CourseWebvo coursevo = courseService.getBaseCourseInfo(courseid);
        CourseOrder order = new CourseOrder();
        BeanUtils.copyProperties(coursevo, order);
        return order;
    }

}
