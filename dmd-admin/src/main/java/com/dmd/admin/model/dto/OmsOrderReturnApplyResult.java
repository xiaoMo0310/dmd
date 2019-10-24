package com.dmd.admin.model.dto;

import com.dmd.admin.model.domain.OmsOrderReturnApply;
import com.dmd.admin.model.domain.OmsShipping;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created by macro on 2018/10/18.
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    private OmsShipping omsShipping;
}
