package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsCourseProduct;
import com.dmd.admin.model.dto.PmsCourseProductDto;
import com.dmd.admin.model.dto.PmsCourseProductListDto;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 课程商品表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-11
 */
public interface PmsCourseProductService extends IService<PmsCourseProduct> {

    /**
     * 查询潜水学证的商品列表信息
     * @param courseProductListDto
     * @return
     */
    PageInfo<PmsCourseProduct> findCourseProductList(PmsCourseProductListDto courseProductListDto);

    /**
     * 修改商品的审核状态
     * @param loginAuthDto
     * @param courseProduct
     * @return
     */
    int updateProductApprovalStatus(LoginAuthDto loginAuthDto, PmsCourseProductDto courseProduct);

    Integer queryAudited();

    Integer queryAuditPass();

    Integer queryAuditFailed();

    Integer queryAllMerchandise();
}
