package com.yu.edu.entity.vo;

import lombok.Data;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:34 2021/11/11
 * @Modified By:
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;
}
