package com.dmd.mall.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/14 17:08
 * @Description
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OmsApiConstant {

	/**
	 * The interface Shipping.
	 */
	public interface Shipping {
		/**
		 * The constant DEFAULT.
		 */
		int DEFAULT = 1;
		/**
		 * The constant NOT_DEFAULT.
		 */
		int NOT_DEFAULT = 0;
	}

	/**
	 * The enum Order status enum.
	 */
	public enum OrderStatusEnum {
		/**
		 * Canceled order status enum.
		 */
		CANCELED(6, "已取消"),
		/**
		 * No pay order status enum.
		 */
		NO_PAY(0, "未支付"),
		/**
		 * Paid order status enum.
		 */
		PAID(1, "已付款"),
		/**
		 * Shipped order status enum.
		 */
		SHIPPED(2, "已发货"),
		/**
		 * Order success order status enum.
		 */
		ORDER_SUCCESS(3, "订单完成"),
		/**
		 * Order close order status enum.
		 */
		ORDER_CLOSE(4, "订单关闭"),

		/**
		 * Order afterSale order status enum.
		 */
		AFTER_SALE(5, "售后");


		OrderStatusEnum(int code, String value) {
			this.code = code;
			this.value = value;
		}

		private String value;
		private int code;

		/**
		 * Gets value.
		 *
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * Gets code.
		 *
		 * @return the code
		 */
		public int getCode() {
			return code;
		}

		/**
		 * Code of order status enum.
		 *
		 * @param code the code
		 *
		 * @return the order status enum
		 */
		public static OrderStatusEnum codeOf(int code) {
			OrderStatusEnum result = null;
			for (OrderStatusEnum paymentTypeEnum : values()) {
				if (paymentTypeEnum.getCode() == code) {
					result = paymentTypeEnum;
					break;
				}
			}
			return result;
		}
	}
}
