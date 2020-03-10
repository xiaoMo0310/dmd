package com.dmd.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.domain.PowerNotesBean;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.dto.CourseProductDto;
import com.dmd.mall.model.vo.CertificateProductVo;
import com.dmd.mall.model.vo.DivingProductVo;
import com.dmd.mall.model.vo.PmsCertificateVo;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.github.pagehelper.PageInfo;

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
     * @param courseProductDto
     * @return
     */
    int saveCourseProductMessage(LoginAuthDto loginAuthDto, CourseProductDto courseProductDto);

    /**
     *根据id查询详细的信息
     * @param id
     * @return
     */
    DivingProductVo findCourseProductById(Long id);

    /**
     * 分页查询产品列表信息
     *
     * @param baseQuery
     * @param type
     * @return
     */
    PageInfo<PmsCourseListVo> findCourseProductListByType(BaseQuery baseQuery, Integer type);

    /**
     * 查询学证产品的详细信息
     * @param loginAuthDto
     * @param certificateId
     * @param addressId
     * @return
     */
    CertificateProductVo findCertificateProduct(LoginAuthDto loginAuthDto, Long certificateId, Long addressId);

    /**
     * 根据证书id,卖家id,地址id查询商品信息
     * @param certificateProductDto
     * @return
     */
    JSONObject findCourseProductByIds(CertificateProductDto certificateProductDto);

    /**
     * 根据教练id学证商品的信息
     * @param coachId
     * @return
     */
    PmsCourseProduct findCourseProductByCoachId(Long coachId, Long certificateId, Long addressId);

    List<PmsCourseProduct> queryPowerNotesPage(Integer pageNum, Integer pageSize, Long userId, PmsCourseProduct pmsCourseProduct);

    Integer queryPeopleNum(Long id, Long userId,Integer productType);

    /**
     * 封装订单详情数据
     * @param product
     * @return
     */
    OmsOrderItem createOrderItem(PmsCourseProduct product);

    /**
     * 结算商品
     * @param productId
     * @return
     */
    PmsCourseListVo settlementCourseProduct(LoginAuthDto loginAuthDto, Long productId);

    /**
     * 统计该证书商品的数量
     * @param productType
     * @param certificateId
     * @return
     */
    long findCertificateProductNum(Integer productType, Long certificateId);

    /**
     * 根据商品状态查询商品信息
     * @param status
     * @return
     */
    List<PmsCourseProduct> findCourseProductByStatus(Integer status);

    /**
     * 修改商品的状态
     * @param id
     * @param i
     * @return
     */
    int updateCourseProductStatus(Long id, Integer status);

    /**
     * 查询卖家潜水商品列表信息
     * @param loginAuthDto
     * @param baseQuery
     * @return
     */
    PageInfo<PmsCourseListVo> findSellerCourseProductListByType(LoginAuthDto loginAuthDto, BaseQuery baseQuery, Integer type);

    /**
     * 查询卖家所有的学证商品证书信息
     * @param loginAuthDto
     * @return
     */
    List<PmsCertificateVo> findSellerCertificateMessage(LoginAuthDto loginAuthDto);

    Long  countSellerProductNum(Long coachId);

    List<PowerNotesBean> queryPowerNotesCoachPage(Integer pageNum, Integer pageSize, Long userId, PmsCourseProduct pmsCourseProduct);

    List<PowerNotesBean> queryPowerNotesCoachToMonth(Long userId);
}
