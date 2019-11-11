package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsTransaction;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    List<OmsTransaction> selectAllMessage(BillingDetailDto billingDetailDto);
}
