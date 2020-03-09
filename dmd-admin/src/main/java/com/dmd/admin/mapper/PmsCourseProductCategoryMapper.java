package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.PmsCourseProductCategory;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-03
 */
@Mapper
@Component
public interface PmsCourseProductCategoryMapper extends MyMapper<PmsCourseProductCategory> {

    List<PmsCourseProductCategory> selectByParentId(Long parentId);

    List<Map> selectParentIdsByIds(List<Long> ids);
}
