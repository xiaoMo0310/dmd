package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.PmsCourseProduct;
import com.dmd.admin.model.dto.PmsCourseProductDto;
import com.dmd.admin.model.dto.PmsCourseProductListDto;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 课程商品表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-11
 */
@Mapper
@Component
public interface PmsCourseProductMapper extends MyMapper<PmsCourseProduct> {
    List<PmsCourseProduct> selectCourseProductList(PmsCourseProductListDto courseProductListDto);

    int updateProductApprovalStatus(@Param("userId") Long userId,@Param("userName") String userName,@Param("courseProduct") PmsCourseProductDto courseProduct);

    Integer queryAudited();

    Integer queryAuditPass();

    Integer queryAuditFailed();

    Integer queryAllMerchandise();
}
