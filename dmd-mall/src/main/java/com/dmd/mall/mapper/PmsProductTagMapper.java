package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsProductTag;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 产品标签关系表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-17
 */
@Mapper
@Component
public interface PmsProductTagMapper extends MyMapper<PmsProductTag> {

    int deleteByProductId(Long productId);
}
