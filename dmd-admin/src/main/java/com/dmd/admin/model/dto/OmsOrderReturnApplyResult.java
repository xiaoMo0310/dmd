package com.dmd.admin.model.dto;

import com.dmd.admin.model.domain.OmsOrderReturnApply;
import com.dmd.admin.model.domain.OmsShipping;
import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/26 11:12
 * @Description 申请信息封装
 */
@Data
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {

    private OmsShipping omsShipping;
}
