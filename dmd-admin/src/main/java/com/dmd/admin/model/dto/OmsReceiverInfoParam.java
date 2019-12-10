package com.dmd.admin.model.dto;

import lombok.Data;

/**
 * 订单修改收货人信息参数
 * Created by macro on 2018/10/29.
 */
@Data
public class OmsReceiverInfoParam {
    private Long orderId;

    private String receiverName;

    private String receiverMobileNo;

    private String provinceName;

    private String cityName;

    private String districtName;

    private String detailAddress;

    private String receiverZipCode;

    private Integer status;
}
