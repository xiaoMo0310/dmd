package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.PmsTag;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-16
 */
@Mapper
@Component
public interface PmsTagMapper extends MyMapper<PmsTag> {

    List<PmsTag> findTagListByPage(PmsTag pmsTag);
}
