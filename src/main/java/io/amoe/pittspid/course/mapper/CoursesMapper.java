package io.amoe.pittspid.course.mapper;

import io.amoe.pittspid.course.entity.Courses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-10-11
 */
@Repository
@Mapper
public interface CoursesMapper extends BaseMapper<Courses> {

}
