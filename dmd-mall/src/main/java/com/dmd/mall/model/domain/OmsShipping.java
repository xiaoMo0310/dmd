package com.dmd.mall.model.domain;

import com.dmd.core.mybatis.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import javax.persistence.Column;
import javax.persistence.Table;


/**
 * <p>
 * 收货人信息表
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "oms_shipping")
@Alias(value = "OmsShipping")
@ApiModel
public class OmsShipping extends BaseEntity {

private static final long serialVersionUID = 1L;


    @ApiModelProperty("版本号")
    private Integer version;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

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
