package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.PmsCourseProductCategory;
import com.dmd.mall.mapper.PmsCourseProductCategoryMapper;
import com.dmd.mall.service.PmsCourseProductCategoryService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductCategoryServiceImpl extends BaseService<PmsCourseProductCategory> implements PmsCourseProductCategoryService {

    @Autowired
    private PmsCourseProductCategoryMapper courseProductCategoryMapper;

    @Override
    public List<PmsCourseProductCategory> getList(Long parentId) {
        return courseProductCategoryMapper.selectCategoryListByParentId(parentId);
    }
}
