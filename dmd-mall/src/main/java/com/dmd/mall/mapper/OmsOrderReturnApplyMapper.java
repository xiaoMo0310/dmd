package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsOrderReturnApply;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Mapper
@Component
public interface OmsOrderReturnApplyMapper extends MyMapper<OmsOrderReturnApply> {

    OmsOrderReturnApply selectByOrderSn(String orderSn);

    Map countReturnOrderNum(@Param("userId") Long userId, @Param("userType") String userType, @Param("status") Integer status);

    List<OmsOrderReturnApply> selectReturnOrderByStatus(Integer status);
}
