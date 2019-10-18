package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/16 9:55
 * @Description 创建订单成功后返回vo类
 */

@Data
public class OrderCreateResultVo implements Serializable {
    private static final long serialVersionUID = -5581041089047976745L;

    /**
     * 需要支付的价格
     */
    private BigDecimal totalPayment;

    /**
     * 需要支付的订单编号
     */
    private List<String> orderSns;
}
