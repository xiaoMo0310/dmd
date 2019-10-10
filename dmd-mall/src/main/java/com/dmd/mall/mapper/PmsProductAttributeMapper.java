package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.PmsProductAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 商品属性参数表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Mapper
@Component
public interface PmsProductAttributeMapper extends MyMapper<PmsProductAttribute> {
}
