package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.PmsCourseProductTemplate;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 潜水产品模板表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@Mapper
@Component
public interface PmsCourseProductTemplateMapper extends MyMapper<PmsCourseProductTemplate> {

    List<PmsCourseProductTemplate> selectTemplateByShopId(Long shopId);
}
