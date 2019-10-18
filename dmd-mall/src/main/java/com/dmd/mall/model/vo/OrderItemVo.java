package com.dmd.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/17 10:28
 * @Description 商品详情vo类
 */
@Data
public class OrderItemVo implements Serializable {

	private static final long serialVersionUID = -8309014197153665106L;
	/**
	 * 订单编号
	 */
	private String orderSn;

	/**
	 * 商品id
	 */
	private Long productId;

	/**
	 * 商品名称
	 */
	private String productName;

	/**
	 * 商品图片
	 */
	private String productPic;

	/**
	 * 商品单价
	 */
	private BigDecimal productPrice;

	/**
	 * 商品数量
	 */
	private Integer product_quantity;

	/**
	 * 总价
	 */
	private BigDecimal totalPrice;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;
}
