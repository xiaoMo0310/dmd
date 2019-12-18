package com.dmd.admin.mapper;


import com.dmd.admin.model.domain.DynamicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicAmdinMappper
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/1716:40
 */
public interface DynamicAmdinMappper {
    List<DynamicBean> queryDynamicPage(DynamicBean dynamicBean);

    List<Long> queryDynamicById(List<Long> ids);

    int updateDynamicDelflag(List<Long> ids);

    DynamicBean selectDynamicById(Long id);

    DynamicBean selectDynamicByIdComment(Long forDynamicId);

    List<DynamicBean> queryDynamicPageByCoach(DynamicBean dynamicBean);

    DynamicBean selectDynamicByIdByCoach(Long id);

    //List<DynamicBean> queryDynamicById(String[] ids);
}
