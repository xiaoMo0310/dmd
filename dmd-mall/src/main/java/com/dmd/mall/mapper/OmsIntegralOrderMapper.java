package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsIntegralOrder;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.model.vo.IntegralOrderDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 积分好礼订单表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@Mapper
@Component
public interface OmsIntegralOrderMapper extends MyMapper<OmsIntegralOrder> {

    List<IntegralOrderDetailVo> selectIntegralOrderByStatus(@Param("userId") Long userId, @Param("userType") String userType, @Param("statusList") List<Integer> statusList);

    List<IntegralOrderDetailVo> selectUserOrderByStatus(@Param("userId") Long userId,@Param("userType") String userType, @Param("status") Integer status);

    IntegralOrderDetailVo selectUserIntegralOrderByOrderSn(@Param("userId") Long userId, @Param("orderSn") String orderSn);

    IntegralOrderDetailVo selectSellerIntegralOrderByOrderSn(@Param("coachId") Long coachId,@Param("orderSn") String orderSn);
}
