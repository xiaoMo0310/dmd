package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsShopNotice;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 教练店铺公告 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-27
 */
public interface UmsShopNoticeService extends IService<UmsShopNotice> {

    /**
     * 分页查询店铺公告信息
     * @param loginAuthDto
     * @param baseQuery
     * @return
     */
    PageInfo<UmsShopNotice> selectShopNoticeByPage(LoginAuthDto loginAuthDto, BaseQuery baseQuery);
}
