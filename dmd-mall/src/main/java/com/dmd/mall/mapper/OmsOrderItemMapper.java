package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Mapper
@Component
public interface OmsOrderItemMapper extends MyMapper<OmsOrderItem> {

}
