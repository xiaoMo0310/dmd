package com.dmd.mall.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/9/25 14:18
 * @Description 钱包记录 vo
 */
@Data
public class UmsWalletLogVo {
    /**
     * 操作金额
     */
    private BigDecimal money;

    /**
     * 操作类型(1:增加 2:减少)
     */
    private Integer type;

    /**
     * 来源去向(1:微信  2:支付宝 3:银联 4:邀请 5:消费)
     */
    private Integer sourceDestination;

    /**
     * 创建日期
     */
    private Date createdTime;
}
