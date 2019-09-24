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
 * 用户账户表
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "ums_user_wallet")
@Alias(value = "UmsUserWallet")
@ApiModel
public class UmsUserWallet extends BaseEntity {

private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    @Column(name = "total_money")
    @ApiModelProperty("钱包余额")
    private BigDecimal totalMoney;

    @Column(name = "amount_available")
    @ApiModelProperty("可用余额")
    private BigDecimal amountAvailable;

    @Column(name = "wallet_password")
    @ApiModelProperty("支付密码")
    private String walletPassword;


}
