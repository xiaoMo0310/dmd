package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsOrderReturnReason;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 退货原因表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-22
 */
@Mapper
@Component
public interface OmsOrderReturnReasonMapper extends MyMapper<OmsOrderReturnReason> {

    List<OmsOrderReturnReason> selectOrderReturnReason();
}
