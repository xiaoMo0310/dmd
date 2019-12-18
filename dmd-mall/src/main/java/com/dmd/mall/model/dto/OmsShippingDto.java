package com.dmd.mall.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/20 13:41
 * @Description 地址信息 dto
 */
@Data
public class OmsShippingDto implements Serializable {

    private static final long serialVersionUID = 1385007679112016466L;
    private Long id;

    @Column(name = "receiver_name")
    @ApiModelProperty("收货姓名")
    private String receiverName;

    @Column(name = "receiver_phone_no")
    @ApiModelProperty("收货固定电话")
    private String receiverPhoneNo;

    @Column(name = "receiver_mobile_no")
    @ApiModelProperty("收货移动电话")
    private String receiverMobileNo;

    @Column(name = "province_id")
    @ApiModelProperty("收货人省ID")
    private Long provinceId;

    @Column(name = "province_name")
    @ApiModelProperty("省份")
    private String provinceName;

    @Column(name = "city_id")
    @ApiModelProperty("收货人城市ID")
    private Long cityId;

    @Column(name = "city_name")
    @ApiModelProperty("收货人城市名称")
    private String cityName;

    @Column(name = "district_id")
    @ApiModelProperty("区/县 编码")
    private String districtId;

    @Column(name = "district_name")
    @ApiModelProperty("区/县")
    private String districtName;

    @Column(name = "detail_address")
    @ApiModelProperty("详细地址")
    private String detailAddress;

    @Column(name = "receiver_zip_code")
    @ApiModelProperty("邮编")
    private String receiverZipCode;

    @Column(name = "default_address")
    @ApiModelProperty("默认地址 1默认地址")
    private Integer defaultAddress;

}
