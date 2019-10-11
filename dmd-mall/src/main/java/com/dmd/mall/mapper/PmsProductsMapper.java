package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsShipProductListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Mapper
@Component
public interface PmsProductsMapper extends MyMapper<PmsProduct> {

    List<PmsShipProductListVo> selectShipSleepsProduct(SortDto sortDto);
}
