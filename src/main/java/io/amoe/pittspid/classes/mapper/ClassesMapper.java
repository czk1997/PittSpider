package io.amoe.pittspid.classes.mapper;

import io.amoe.pittspid.classes.entity.Classes;
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
@Mapper
@Repository
public interface ClassesMapper extends BaseMapper<Classes> {

}
