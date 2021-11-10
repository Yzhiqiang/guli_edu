package com.yu.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yu.edu.entity.EduSubject;
import com.yu.edu.entity.excel.SubjectData;
import com.yu.edu.entity.subject.OneSubject;
import com.yu.edu.entity.subject.TwoSubject;
import com.yu.edu.listener.SubjectExcelListener;
import com.yu.edu.mapper.EduSubjectMapper;
import com.yu.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-02-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public List<OneSubject> getAllSubjects() {
        //查找一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", 0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper);

        //查找二级分类
        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id", 0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);

        List<OneSubject> finalList = new ArrayList<>();

        for(int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject oneeduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(oneeduSubject.getId());
//            oneSubject.setTitle(oneeduSubject.getTitle());
            //将第一个对象中的值拷贝到第二个对象中的值
            BeanUtils.copyProperties(oneeduSubject, oneSubject);

            List<TwoSubject> twoSubjects = new ArrayList<>();
            for(int j = 0; j < twoSubjectList.size(); j++) {
                if(twoSubjectList.get(j).getParentId().equals(oneeduSubject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(twoSubjectList.get(j), twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }
            oneSubject.setTwoSubjects(twoSubjects);
            /**
             * 自己实现
             */
//            QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
//            wrapper2.eq("parent_id", oneeduSubject.getId());
//            List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);
//            List<TwoSubject> twoSubjects = new ArrayList<>();
//            for(int j = 0; j < twoSubjectList.size(); j++)
//            {
//                TwoSubject twoSubject = new TwoSubject();
//                BeanUtils.copyProperties(twoSubjectList.get(j), twoSubject);
//                twoSubjects.add(twoSubject);
//            }
//            oneSubject.setTwoSubjects(twoSubjects);
            finalList.add(oneSubject);
        }
        return finalList;
    }

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
