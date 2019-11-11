package com.dmd.mall.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/11/8 16:36
 * @Description 证书产品 vo
 */
@Data
public class CertificateProductVo extends PmsCourseProductVo{

    /**
     * 证书信息
     */
    private PmsCertificateVo pmsCertificateVo;

    /**
     * 教练信息
     */
    private List<UmsCoachVo> umsCoachVos;

}
