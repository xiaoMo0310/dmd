package com.dmd.admin.model.vo;

import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.domain.OmsOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/11 15:14
 * @Description 分账订单vo类
 */
@Data
public class FashionableAndOrderVo implements Serializable {

    private static final long serialVersionUID = -3547345551586543537L;
    /**
     * 分账信息
     */
    private OmsFashionable fashionable;

    /**
     * 订单数据
     */
    private OmsOrder order;
}
