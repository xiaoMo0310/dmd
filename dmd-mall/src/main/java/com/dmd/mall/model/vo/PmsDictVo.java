package com.dmd.mall.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
*@Author YangAnsheng
*@Date  2019/9/27 11:30
*@Describle 字典数据vo类
*/
@Data
public class PmsDictVo implements Serializable {

    private static final long serialVersionUID = 6933166137203007645L;
    private Long id;

    private String dictKey;

    private String dictValue;

    private String dictName;
}
