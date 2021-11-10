package com.yu.edu.entity.chapter;

import com.yu.edu.entity.Chapter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 9:49 2021/11/10
 * @Modified By:
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
