package com.yu.edu.controller;


import com.yu.commonUtils.R;
import com.yu.edu.entity.Chapter;
import com.yu.edu.entity.chapter.ChapterVo;
import com.yu.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public R getChapterVideoByCourseId(@PathVariable("courseId") String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", chapter);
    }

    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateById(chapter);
        return R.ok();
    }

    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        Boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

