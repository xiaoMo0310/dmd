package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsOrderReturnApply;
import com.dmd.admin.model.dto.OmsOrderReturnApplyResult;
import com.dmd.admin.model.dto.OmsReturnApplyQueryParam;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-26
 */
@Mapper
@Component
public interface OmsOrderReturnApplyMapper extends MyMapper<OmsOrderReturnApply> {

    /**
     * 查询申请列表
     */
    List<OmsOrderReturnApply> getList(@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id")Long id);
}
