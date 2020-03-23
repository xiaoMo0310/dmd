package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PmsTagVo implements Serializable{
    private static final long serialVersionUID = 7066924043423986556L;

    /**
     * 标签id
     */
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;
}
