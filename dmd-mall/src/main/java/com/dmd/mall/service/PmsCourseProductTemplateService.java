package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.mall.model.domain.PmsCourseProductTemplate;
import com.dmd.core.support.IService;
import com.dmd.mall.model.vo.PmsCourseProductTemplateVo;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 潜水产品模板表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
public interface PmsCourseProductTemplateService extends IService<PmsCourseProductTemplate> {

    /**
     * 根据店铺Id分页查询产品模板
     * @param shopId
     * @param baseQuery
     * @return
     */
    PageInfo<PmsCourseProductTemplateVo> findTemplateByShopId(Long shopId, BaseQuery baseQuery);
}
