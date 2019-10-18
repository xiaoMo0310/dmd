package com.dmd.mall.model.dto;

import com.dmd.base.dto.BaseQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Order page query.
 *
 * @author paascloud.net @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderPageQueryDto extends BaseQuery {
	private static final long serialVersionUID = -7997684399705866704L;

	@ApiModelProperty("订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
	private String status;

	@ApiModelProperty("订单编号")
	private String orderSn;
}
