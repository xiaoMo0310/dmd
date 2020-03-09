package com.dmd.mall.service;

import com.dmd.mall.model.domain.PmsCourseProductCategory;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
public interface PmsCourseProductCategoryService extends IService<PmsCourseProductCategory> {

    /**
     * 根据父id查询分类信息
     * @param parentId
     * @return
     */
    List<PmsCourseProductCategory> getList(Long parentId);
}
