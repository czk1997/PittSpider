package io.amoe.pittspid.course.service.impl;

import io.amoe.pittspid.course.entity.Courses;
import io.amoe.pittspid.course.mapper.CoursesMapper;
import io.amoe.pittspid.course.service.ICoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.amoe.pittspid.section.entity.Sections;
import io.amoe.pittspid.section.mapper.SectionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses> implements ICoursesService {

}
