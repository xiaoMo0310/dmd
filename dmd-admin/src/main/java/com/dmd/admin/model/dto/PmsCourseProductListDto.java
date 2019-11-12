package com.dmd.admin.model.dto;

import com.dmd.base.dto.BaseQuery;
import lombok.Data;
/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/11 15:56
 * @Description 潜水学证商品 dto
 */
@Data
public class PmsCourseProductListDto extends BaseQuery {

    private Integer approvalStatus;

    private Integer productType;
}
