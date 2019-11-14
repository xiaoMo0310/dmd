package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.vo.CertificateProductVo;
import com.dmd.mall.model.vo.DivingProductVo;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 课程商品表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-26
 */
public interface PmsCourseProductService extends IService<PmsCourseProduct> {

    /**
     * 编辑课程产品的信息
     * @param loginAuthDto
     * @param courseProduct
     * @return
     */
    int saveCourseProductMessage(LoginAuthDto loginAuthDto, PmsCourseProduct courseProduct);

    /**
     *根据id查询详细的信息
     * @param id
     * @return
     */
    DivingProductVo findCourseProductById(Long id);

    /**
     * 分页查询产品列表信息
     * @param baseQuery
     * @param type
     * @return
     */
    PageInfo<PmsCourseListVo> findCourseProductListByType(BaseQuery baseQuery, Integer type);

    /**
     * 查询学证产品的详细信息
     * @param certificateId
     * @return
     */
    CertificateProductVo findCertificateProduct(LoginAuthDto loginAuthDto, Long certificateId);

    /**
     * 根据证书id,卖家id,地址id查询商品信息
     * @param certificateProductDto
     * @return
     */
    PmsCourseProduct findCourseProductByIds(CertificateProductDto certificateProductDto);

    /**
     * 根据教练id查询商品信息
     * @param coachId
     * @return
     */
    PmsCourseProduct findCourseProductByCoachId(Long coachId);

    List<PmsCourseProduct> queryPowerNotesPage(Integer pageNum, Integer pageSize, Long userId, PmsCourseProduct pmsCourseProduct);
}
