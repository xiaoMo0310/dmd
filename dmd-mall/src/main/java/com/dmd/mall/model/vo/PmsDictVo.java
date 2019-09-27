package com.dmd.mall.model.vo;

import lombok.Data;

/**
*@Author YangAnsheng
*@Date  2019/9/27 11:30
*@Describle 字典数据vo类
*/
@Data
public class PmsDictVo {

    private Long id;

    private String dictKey;

    private String dictValue;

    private String dictName;
}
