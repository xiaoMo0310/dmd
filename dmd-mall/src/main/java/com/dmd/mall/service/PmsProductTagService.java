package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsProductTag;

import java.util.List;

/**
 * <p>
 * 产品标签关系表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-17
 */
public interface PmsProductTagService extends IService<PmsProductTag> {

    int batchSaveProductTag(Long productId, List<Long> tagIds);

    int batchUpdateProductTag(Long productId, List<Long> tagIds);
}
