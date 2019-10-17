package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/17 11:49
 * @Description 订单总vo类
 */
@Data
public class OrderVo implements Serializable {

	private static final long serialVersionUID = 5829292780030349525L;
	/**
	 * 订单编号
	 */
	private String orderSn;

	/**
	 * 订单总的金额
	 */
	private BigDecimal totalAmount;
	/**
	 *实际支付的金额
	 */
	private BigDecimal payAmount;

	/**
	 * 订单支付方式
	 */
	private Integer payType;

	/**
	 * 订单支付方式描述
	 */
	private String payTypeDesc;
	/**
	 *订单运费
	 */
	private BigDecimal freightmount;

	/**
	 * 订单的状态
	 */
	private Integer status;


	private String statusDesc;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date paymentTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date sendTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date closeTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
	private String creator;

	/**
	 * 订单的明细
	 */
	private List<OrderItemVo> orderItemVoList = new ArrayList<>(0);

	/**
	 * 收获地址Vo
	 */
	private ShippingVo shippingVo;
}
