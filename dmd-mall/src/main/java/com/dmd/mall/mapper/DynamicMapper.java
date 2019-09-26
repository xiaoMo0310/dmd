package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.DynamicBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: DynamicMapper
 * @projectName dmd-master
 * @description: TODO
 * @date 2019/9/239:44
 */
public interface DynamicMapper {
    List<DynamicBean> queryDynamic(Long userId);

    Integer queryPraise(Long id);

    Integer queryShare(Long id);

    int updateLikePraise(Long id);

    int updateCancelPraise(Long id);

    int updateDynamicrShare(Long id);

    int updateDynamicDelflag(Long id);
}
