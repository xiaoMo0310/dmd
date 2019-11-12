package com.dmd.admin.model.dto;

import lombok.Data;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/12 13:59
 * @Description 商品dto
 */
@Data
public class PmsCourseProductDto {

    private Long id;

    private Integer approvalStatus;

    private String failureReason;
}
