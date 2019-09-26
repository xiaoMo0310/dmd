package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 * 用户账户日志表 
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_user_wallet_log")
@Alias(value = "UmsUserWalletLog")
@ApiModel
public class UmsUserWalletLog extends BaseEntity {

private static final long serialVersionUID = 1L;


    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "wallet_id")
    @ApiModelProperty("钱包账户id")
    private Long walletId;

    @Column(name = "single_number")
    @ApiModelProperty("充值支付单号")
    private String singleNumber;

    @ApiModelProperty("操作金额")
    private BigDecimal money;

    @ApiModelProperty("操作类型(1:增加 2:减少)")
    private Integer type;

    @Column(name = "source_destination")
    @ApiModelProperty("来源去向(1:微信  2:支付宝 3:银联 4:邀请 5:消费)")
    private Integer sourceDestination;

    @ApiModelProperty("状态(1:有效 2:无效)")
    private Integer status;

    @ApiModelProperty("操作系统")
    private String os;

    @Column(name = "ip_source")
    @ApiModelProperty("操作来源ip")
    private String ipSource;

}
