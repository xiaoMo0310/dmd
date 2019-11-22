package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.OmsOrderAppraise;
import com.dmd.core.support.IService;
import com.dmd.mall.model.dto.OrderAppraiseDto;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
public interface OmsOrderAppraiseService extends IService<OmsOrderAppraise> {

    /**
     * 保存订单评价信息
     * @param loginAuthDto
     * @param orderAppraiseDto
     * @return
     */
    int insertAppraiseMessage(LoginAuthDto loginAuthDto, OrderAppraiseDto orderAppraiseDto);

    /**
     * 查询商品评价的消息
     * @param productId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAppraiseMessage(String productId, Integer pageNum, Integer pageSize);
}
