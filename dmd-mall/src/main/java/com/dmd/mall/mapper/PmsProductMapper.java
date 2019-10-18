package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsProductListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-12
 */
@Mapper
@Component
public interface PmsProductMapper extends MyMapper<PmsProduct> {

    List<PmsProductListVo> selectShipSleepsProduct(@PathParam("sortDto") SortDto sortDto, @PathParam("productType") Integer productType);

    List<PmsProductListVo> selectProductByShopId(@PathParam("sortDto") SortDto sortDto, @PathParam("shopId") Long shopId);

    List<PmsProduct> queryPmsProductByContent(@Param("content") String content);
}
