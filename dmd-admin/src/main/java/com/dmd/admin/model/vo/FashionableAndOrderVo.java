package com.dmd.admin.model.vo;

import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.domain.OmsOrder;
import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/11 15:14
 * @Description 分账订单vo类
 */
@Data
public class FashionableAndOrderVo {

    /**
     * 分账信息
     */
    private OmsFashionable fashionable;

    /**
     * 订单数据
     */
    private OmsOrder order;
}
