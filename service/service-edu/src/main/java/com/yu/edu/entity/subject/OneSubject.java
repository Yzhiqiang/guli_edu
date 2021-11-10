package com.yu.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Yuzhiqiang
 * @Description:
 * @Date: Create in 15:51 2021/11/9
 * @Modified By:
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> twoSubjects = new ArrayList<>();
}
