package com.dmd.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.domain.PmsCourseProductCategory;
import com.dmd.admin.model.dto.PmsCourseProductCategoryDto;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品分类 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-03
 */
public interface PmsCourseProductCategoryService extends IService<PmsCourseProductCategory> {

    /**
     * 添加潜水学证产品分类
     *
     * @param loginAuthDto
     * @param courseProductCategoryDto
     * @return
     */
    int createCategory(LoginAuthDto loginAuthDto, PmsCourseProductCategoryDto courseProductCategoryDto);

    /**
     * 修改商品分类
     *
     * @param loginAuthDto
     * @param id
     * @param courseProductCategoryDto
     * @return
     */
    int updateCategory(LoginAuthDto loginAuthDto, Long id, PmsCourseProductCategoryDto courseProductCategoryDto);

    /**
     * 修改商分页查询商品分类品分类
     * @param parentId
     * @param baseQuery
     * @return
     */
    JSONObject getList(Long parentId, BaseQuery baseQuery);

    /**
     *删除分类如果有下级分类则都删除
     * @param id
     * @return
     */
    int deleteCourseProductCategory(Long id);

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 根据id查询父id
     * @param ids
     * @return
     */
    List<Map> findParentIdsById(List<Long> ids);
}
