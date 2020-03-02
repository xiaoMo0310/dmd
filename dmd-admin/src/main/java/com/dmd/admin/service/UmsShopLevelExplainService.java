package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsShopLevelExplain;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;

/**
 * <p>
 * 教练店铺店铺表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
public interface UmsShopLevelExplainService extends IService<UmsShopLevelExplain> {

    /**
     * 修改店铺等级说明
     * @param loginAuthDto
     * @param shopLevelExplain
     * @return
     */
    int saveOrUpdateShopLevelExplain(LoginAuthDto loginAuthDto, UmsShopLevelExplain shopLevelExplain);
}
