package com.dmd.mall.model.vo;

import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/8 16:55
 * @Description 潜水产品 vo类
 */
@Data
public class DivingProductVo extends PmsCourseProductVo{

    /**
     * 教练信息
     */
    private UmsCoachVo umsCoachVo;
}
