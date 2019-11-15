package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/13 10:36
 * @Description 订单生成基础返回 vo
 */
@Data
public class OrderResultVo implements Serializable {

    private static final long serialVersionUID = -6434142141140559615L;
    /**
     * 需要支付的价格
     */
    private BigDecimal totalPayment;

    /**
     * 需要支付的订单编号
     */
    private String  orderSn;
}
