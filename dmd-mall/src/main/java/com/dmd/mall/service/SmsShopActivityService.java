package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.mall.model.domain.SmsShopActivity;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
public interface SmsShopActivityService extends IService<SmsShopActivity> {

    /**
     * 分页查询店铺活动
     * @param baseQuery
     * @return
     */
    PageInfo<SmsShopActivity> findShopActivityByPage(BaseQuery baseQuery);
}
