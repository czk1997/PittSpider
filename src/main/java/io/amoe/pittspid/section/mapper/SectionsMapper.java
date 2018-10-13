package io.amoe.pittspid.section.mapper;

import io.amoe.pittspid.section.entity.Sections;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-10-12
 */
@Repository
@Mapper
public interface SectionsMapper extends BaseMapper<Sections> {

}
