package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsOrder;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

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

}
