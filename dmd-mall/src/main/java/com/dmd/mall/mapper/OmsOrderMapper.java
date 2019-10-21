package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.dto.OrderPageQueryDto;
import com.dmd.mall.model.vo.OrderDocVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Mapper
@Component
public interface OmsOrderMapper extends MyMapper<OmsOrder> {

    /**
     * 根据用户id和订单编号查询订单信息
     * @param userId
     * @param orderSn
     * @return
     */
    OmsOrder selectByUserIdAndOrderNo(Long userId, String orderSn);

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    List<OmsOrder> selectByUserId(Long userId);

    /**
     * 根据订单编号和订单状态查询订单和收货地址信息
     * @param orderPageQuery
     * @return
     */
    List<OrderDocVo> queryOrderListWithPage(OrderPageQueryDto orderPageQuery);
}