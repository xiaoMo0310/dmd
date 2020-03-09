package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsCourseProductCategory;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@Mapper
@Component
public interface PmsCourseProductCategoryMapper extends MyMapper<PmsCourseProductCategory> {

    List<PmsCourseProductCategory> selectCategoryListByParentId(Long parentId);
}
