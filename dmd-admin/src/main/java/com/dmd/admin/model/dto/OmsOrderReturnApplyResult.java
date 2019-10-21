package com.dmd.admin.model.dto;

import com.dmd.admin.model.domain.OmsCompanyAddress;
import com.dmd.admin.model.domain.OmsOrderReturnApply;
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
