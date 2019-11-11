package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsTransaction;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单交易表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-04
 */
@Mapper
@Component
public interface OmsTransactionMapper extends MyMapper<OmsTransaction> {

}
