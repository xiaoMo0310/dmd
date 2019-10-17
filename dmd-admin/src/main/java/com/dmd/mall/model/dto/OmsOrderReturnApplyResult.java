package com.dmd.mall.model.dto;

import com.dmd.mall.model.domain.OmsCompanyAddress;
import com.dmd.mall.model.domain.OmsOrderReturnApply;
import lombok.Getter;
import lombok.Setter;

/**
 * 申请信息封装
 * Created by macro on 2018/10/18.
 */
public class OmsOrderReturnApplyResult extends OmsOrderReturnApply {
    @Getter
    @Setter
    private OmsCompanyAddress companyAddress;
}
