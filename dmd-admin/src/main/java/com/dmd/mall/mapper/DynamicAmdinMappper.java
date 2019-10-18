package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DynamicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicAmdinMappper
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/1716:40
 */
public interface DynamicAmdinMappper {
    List<DynamicBean> queryDynamicPage(DynamicBean dynamicBean);
}
