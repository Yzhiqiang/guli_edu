package com.yu.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.edu.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.edu.entity.subject.OneSubject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

    List<OneSubject> selectAllSubject(QueryWrapper<OneSubject> wrapper);
}
