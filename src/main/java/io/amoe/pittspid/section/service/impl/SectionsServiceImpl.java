package io.amoe.pittspid.section.service.impl;

import io.amoe.pittspid.course.entity.Courses;
import io.amoe.pittspid.course.mapper.CoursesMapper;
import io.amoe.pittspid.section.entity.Sections;
import io.amoe.pittspid.section.mapper.SectionsMapper;
import io.amoe.pittspid.section.service.ISectionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2018-10-12
 */
@Service
public class SectionsServiceImpl extends ServiceImpl<SectionsMapper, Sections> implements ISectionsService {
    @Autowired
    SectionsMapper sectionsMapper;
    public void insert(Sections sections){
        sectionsMapper.insert(sections);
    }
}
