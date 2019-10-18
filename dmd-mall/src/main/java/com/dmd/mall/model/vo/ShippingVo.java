package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/17 10:12
 * @Description 收获地址vo类
 */
@Data
public class ShippingVo implements Serializable {
	private static final long serialVersionUID = 1650395734521093800L;
	private String receiverName;

	private String receiverPhone;

	private String receiverMobile;

	private String receiverProvince;

	private String receiverCity;

	private String receiverDistrict;

	private String receiverAddress;

	private String receiverZip;
}
