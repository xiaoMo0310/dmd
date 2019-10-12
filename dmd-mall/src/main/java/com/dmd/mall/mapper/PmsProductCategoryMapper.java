package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsProductCategory;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 产品分类 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@Mapper
@Component
public interface PmsProductCategoryMapper extends MyMapper<PmsProductCategory> {

}
