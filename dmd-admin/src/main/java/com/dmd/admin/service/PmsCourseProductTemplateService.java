package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsCourseProductTemplate;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 潜水产品模板表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
public interface PmsCourseProductTemplateService extends IService<PmsCourseProductTemplate> {

    /**
     * 添加或修改模板数据
     * @param loginAuthDto
     * @param courseProductTemplate
     * @return
     */
    int createOrUpdateTemplate(LoginAuthDto loginAuthDto, PmsCourseProductTemplate courseProductTemplate);

    /**
     * 分页查询模板信息
     * @param baseQuery
     * @return
     */
    PageInfo<PmsCourseProductTemplate> getTemplateList(BaseQuery baseQuery);

    /**
     * 删除模板
     * @param id
     * @return
     */
    int deleteCourseProductTemplate(Long id);

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);
}
