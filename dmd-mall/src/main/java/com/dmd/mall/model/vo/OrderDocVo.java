package com.dmd.mall.model.vo;

import com.dmd.base.dto.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/17 11:36
 * @Description 订单基础bo类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderDocVo extends BaseVo {

	private static final long serialVersionUID = 4282588127249930700L;
	/**
	 * 订单号
	 */
	private String orderSn;

	/**
	 * 收货人
	 */
	private String receiverName;

	/**
	 * 实际付款金额,单位是元,保留两位小数
	 */
	private BigDecimal payment;

	/**
	 * 支付类型
	 */
	private Integer payType;

	/**
	 * 订单状态:0-已取消-10-未付款, 20-已付款, 40-已发货, 50-交易成功, 60-交易关闭
	 */
	private Integer status;

	/**
	 * 支付时间
	 */
	private Date paymentTime;

	/**
	 * 发货时间
	 */
	private Date sendTime;

	/**
	 * 交易完成时间
	 */
	private Date endTime;

	/**
	 * 交易关闭时间
	 */
	private Date closeTime;
}
