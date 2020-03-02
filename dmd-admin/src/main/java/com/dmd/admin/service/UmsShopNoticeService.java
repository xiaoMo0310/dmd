package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsShopNotice;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

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
     *保存店铺公告信息
     * @param loginAuthDto
     * @param umsShopNotice
     * @return
     */
    int saveShopNotice(LoginAuthDto loginAuthDto, UmsShopNotice umsShopNotice);

    /**
     * 分页查询店铺公告信息
     * @param loginAuthDto
     * @param umsShopNotice
     * @return
     */
    PageInfo<UmsShopNotice> selectShopNoticeByPage(LoginAuthDto loginAuthDto, UmsShopNotice umsShopNotice);

    /**
     * 批量删除店铺公告信息
     * @param loginAuthDto
     * @param ids
     * @return
     */
    int batchDeleteShopNotice(LoginAuthDto loginAuthDto, List<Long> ids);
}
