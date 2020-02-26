package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.core.support.IService;
import com.dmd.mall.model.dto.UmsCoachShopDto;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 教练店铺店铺表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
public interface UmsCoachShopService extends IService<UmsCoachShop> {
    /**
     * 根据教练id查询店铺信息
     * @param userId
     * @return
     */
    UmsCoachShop findByCoachId(Long coachId);

    /**
     * 添加或修改店铺信息
     * @param loginAuthDto
     * @param umsCoachShopDto
     * @param request
     * @return
     */
    int saveOrEditShopMessage(LoginAuthDto loginAuthDto, UmsCoachShopDto umsCoachShopDto, HttpServletRequest request);

    /**
     * 查询店铺信息根据教练信息
     * @param loginAuthDto
     * @return
     */
    UmsCoachShop findShopMessage(LoginAuthDto loginAuthDto);
}
