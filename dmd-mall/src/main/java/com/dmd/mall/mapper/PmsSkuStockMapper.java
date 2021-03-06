package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.PmsSkuStock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Mapper
@Component
public interface PmsSkuStockMapper extends MyMapper<PmsSkuStock> {

    /**
     * 根据商品id查询sku数据
     * @param productId
     * @return
     */
    List<PmsSkuStock> selectSkuMessageByProductId(Long productId);
}
