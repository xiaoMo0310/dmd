package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.vo.PmsCourseListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程商品表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Mapper
@Component
public interface PmsCourseProductMapper extends MyMapper<PmsCourseProduct> {

    /**
     * 根据商品类型查询商品信息
     * @param type
     * @return
     */
    List<PmsCourseListVo> selectCourseProductByType(Integer type);

    /**
     * 根据证书id查询商品的信息
     * @param certificateId
     * @return
     */
    List<Long> selectCoachIdByCertificateId(@Param("certificateId") Long certificateId, @Param("addressId") Long addressId, @Param("productType") Integer productType);

    /**
     * 根据教练id查询学证商品的信息
     * @param coachId
     * @return
     */
    PmsCourseProduct selectByCoachId(@Param("coachId") Long coachId, @Param("certificateId") Long certificateId, @Param("addressId") Long addressId);

    PmsCourseProduct selectCourseProductByIds(CertificateProductDto certificateProductDto);

    List<PmsCourseProduct> queryPowerNotesPage(PmsCourseProduct pmsCourseProduct);

    Integer queryPepleNum(@Param("id") Long id,@Param("userId") Long userId,@Param("productType") Integer productType);

    int selectCheckActivity(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("id") Long id);

    PmsCourseListVo selectCourseProductById(Long productId);

    List<PmsCourseProduct> queryPmsCourseProduct(@Param("content")String content);

    int selectByUserId(@Param("userId") Long userId, @Param("certificateId") Long certificateId, @Param("addressId") Long addressId);

    List<PmsCourseProduct> queryPmsCourseProductByType(@Param("content")String content);

    long countCertificateProductNum(@Param("productType") Integer productType, @Param("certificateId") Long certificateId);

    List<Map> countCertificateProductNumByAddrrss(@Param("productType") Integer productType, @Param("certificateId") Long certificateId);

    List<PmsCourseProduct> selectByStatus(Integer status);

    List<PmsCourseProduct> queryPowerNotesCoachPage(PmsCourseProduct pmsCourseProduct);

    List<PmsCourseProduct> queryPowerNotesCoachToMonth(Long userId);
}
