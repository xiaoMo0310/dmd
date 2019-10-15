package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsProduct;
import com.dmd.mall.model.dto.SortDto;
import com.dmd.mall.model.vo.PmsProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 * @author YangAnsheng
 * @since 2019-10-09
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * 分页查询船宿商品的列表信息
     * @param sortDto
     * @return
     */
    PageInfo findShipSleepsProduct(SortDto sortDto);

    /**
     * 根据商品的id查询商品的详细信息
     * @param productId
     * @return
     */
    PmsProductVo findShipSleepsMessage(Long productId);

    /**
     * 分页查询店铺商品的列表信息
     * @param sortDto
     * @param shopId
     * @return
     */
    PageInfo findProductByShopId(SortDto sortDto, Long shopId);

    /**
     * 分页查询所有课程产品的信息
     * @param baseQuery
     * @param loginAuthDto
     * @return
     */
    List<PageInfo> findCourseProduct(BaseQuery baseQuery, LoginAuthDto loginAuthDto);

}
