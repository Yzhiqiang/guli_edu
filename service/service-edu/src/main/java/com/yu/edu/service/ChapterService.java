package com.yu.edu.service;

import com.yu.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.edu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    Boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
