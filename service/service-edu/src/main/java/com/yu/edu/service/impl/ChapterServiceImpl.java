package com.yu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.edu.entity.Chapter;
import com.yu.edu.entity.Video;
import com.yu.edu.entity.chapter.ChapterVo;
import com.yu.edu.entity.chapter.VideoVo;
import com.yu.edu.exceptionhandler.GuliException;
import com.yu.edu.mapper.ChapterMapper;
import com.yu.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-11-09
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //根据课程id查询章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(wrapper);

        //根据课程id查询各章节的小结
        QueryWrapper<Video> wrapper2 = new QueryWrapper<>();
        wrapper2.eq("course_id", courseId);
        List<Video> videos = videoService.list(wrapper2);

        List<ChapterVo> finalList = new ArrayList<>();

        //根据章节的id，进行组合
        for(int i = 0; i < chapters.size(); i++) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapters.get(i), chapterVo);
            //finalList.add(chapterVo);
            List<VideoVo> VideoList = new ArrayList<>();
            for(int j = 0; j < videos.size(); j++)
            {
                Video video = videos.get(j);
                if(video.getChapterId().equals(chapterVo.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    VideoList.add(videoVo);
                }
            }
            chapterVo.setChildren(VideoList);
            finalList.add(chapterVo);
        }
        return finalList;
    }

    @Override
    public Boolean deleteChapter(String chapterId) {
        //根据chapterid章节id查询小节表，如果章节中有小节则不进行删除
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chapter_id", chapterId);
        int cnt = videoService.count(wrapper);
        if (cnt == 0) {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        } else {
            throw new GuliException(20001, "该章节中有若干小节，不能删除");
        }
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
