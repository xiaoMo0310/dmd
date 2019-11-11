package com.dmd.admin.model.dto;

import com.dmd.base.dto.BaseQuery;
import lombok.Data;

import java.util.Date;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/4 15:29
 * @Description 收支明细 dto
 */
@Data
public class BillingDetailDto extends BaseQuery {

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 收支人姓名
     */
    private String userName;

    /**
     * 时间
     */
    private Date date;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer status;
}
