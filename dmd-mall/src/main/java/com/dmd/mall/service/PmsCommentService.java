package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.mall.model.domain.PmsComment;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 商品评价表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
public interface PmsCommentService extends IService<PmsComment> {

    PageInfo<PmsComment> findCommentMessge(BaseQuery baseQuery, Long productId);
}
