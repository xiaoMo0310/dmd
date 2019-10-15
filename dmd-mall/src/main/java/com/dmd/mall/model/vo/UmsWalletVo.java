package com.dmd.mall.model.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/9/25 9:57
 * @Description 用户钱包vo类
 */
@Data
public class UmsWalletVo implements Serializable {

    private static final long serialVersionUID = 8665210949129897554L;
    /**
     *钱包总的金额
     */
    private BigDecimal totalMoney;

    /**
     * 总的支出
     */
    private BigDecimal totalExpenses;

    /**
     * 总的收入
     */
    private BigDecimal totalRevenue;

    /**
     * 钱包的记录数据
     */
    private PageInfo<UmsWalletLogVo> userWalletLogs;
}
