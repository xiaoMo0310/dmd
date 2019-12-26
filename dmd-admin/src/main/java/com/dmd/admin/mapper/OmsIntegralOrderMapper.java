package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsIntegralOrder;
import com.dmd.admin.model.dto.OmsOrderDeliveryParam;
import com.dmd.admin.model.dto.OmsOrderQueryParam;
import com.dmd.admin.model.vo.IntegralOrderDetailVo;
import com.dmd.core.mybatis.MyMapper;
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

    List<OmsIntegralOrder> getList(@Param("queryParam") OmsOrderQueryParam queryParam);

    /**
     * 批量发货
     */
    int delivery(@Param("list") List<OmsOrderDeliveryParam> deliveryParamList);

    /**
     * 获取订单详情
     */
    IntegralOrderDetailVo getDetail(@Param("id") Long id);
}
