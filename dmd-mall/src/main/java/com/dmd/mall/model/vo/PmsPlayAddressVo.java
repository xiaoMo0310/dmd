package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/8 10:05
 * @Description 潜水学习地址 vo
 */
@Data
public class PmsPlayAddressVo implements Serializable {

    private static final long serialVersionUID = 7173692708914584801L;
    /**
     * id
     */
    private Long id;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否默认(0-非默认 1-默认)
     */
    private Integer isDefault;
}
