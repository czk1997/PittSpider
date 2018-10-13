package io.amoe.pittspid.classes.service.impl;

import io.amoe.pittspid.classes.entity.Classes;
import io.amoe.pittspid.classes.mapper.ClassesMapper;
import io.amoe.pittspid.classes.service.IClassesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.amoe.pittspid.course.entity.Courses;
import io.amoe.pittspid.course.mapper.CoursesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@Service
public class ClassesServiceImpl extends ServiceImpl<ClassesMapper, Classes> implements IClassesService {
    @Autowired
    CoursesMapper coursesMapper;

    public void insert(Courses entity) {
        coursesMapper.insert(entity);
    }
    public List<Courses> getBySubject(String subject){
        subject = subject.toUpperCase();
        Map<String,Object> query=new HashMap<>();
        query.put("subject",subject);
        return coursesMapper.selectByMap(query);
    }
}
