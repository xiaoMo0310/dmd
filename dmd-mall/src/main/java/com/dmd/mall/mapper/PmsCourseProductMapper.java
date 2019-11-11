package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.dto.CertificateProductDto;
import com.dmd.mall.model.vo.PmsCourseListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

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
    List<Long> selectCoachIdByCertificateId(Long certificateId);

    /**
     * 根据教练id查询商品的信息
     * @param coachId
     * @return
     */
    PmsCourseProduct selectByCoachId(Long coachId);

    PmsCourseProduct selectCourseProductByIds(CertificateProductDto certificateProductDto);
}
