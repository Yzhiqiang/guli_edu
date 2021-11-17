package com.yu.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yu.edu.entity.EduTeacher;
import com.yu.edu.mapper.EduTeacherMapper;
import com.yu.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Cacheable(key = "'teacher'", value = "teacheList")
    @Override
    public List<EduTeacher> indexTeacher() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");
        return baseMapper.selectList(wrapper);
    }
}
