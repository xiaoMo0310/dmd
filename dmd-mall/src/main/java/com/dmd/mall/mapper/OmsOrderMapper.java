package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    OmsOrder selectByUserIdAndOrderNo(@Param("userId")Long userId, @Param("userType") String userType, @Param("orderSn") String orderSn);

    /**
     * 根据用户id查询订单信息
     * @param userId
     * @return
     */
    List<OmsOrder> selectByUserId(Long userId);

    /**
     * 根据订单状态查询当前用户的订单信息
     * @param status
     * @return
     */
    List<CourseOrderDetailVo> selectUserOrderByStatus(@Param("userId") Long userId,@Param("userType") String userType, @Param("status") Integer status);

    List<CourseOrderDetailVo> selectSellerOrderByStatus(@Param("coachId") Long coachId, @Param("userType") String userType, @Param("status") Integer status);

    CourseOrderDetailVo selectUserOrderByOrderSn(@Param("userId") Long userId, @Param("orderSn") String orderSn);

    CourseOrderDetailVo selectSellerOrderByOrderSn(@Param("coachId") Long coachId,@Param("orderSn") String orderSn);

    OmsOrder selectByUserIdAndOrderId(@Param("userId") Long userId,@Param("userType") String userType, @Param("orderId") Long orderId);

    List<CourseOrderDetailVo> selectByStatus(@Param("orderType")Integer orderType, @Param("status") Integer status);

    Map countOrderNum(@Param("userId") Long userId,@Param("userType") String userType, @Param("status") Integer status);
}
