package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsPlayAddress;
import com.dmd.mall.model.vo.PmsPlayAddressVo;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-08
 */
public interface PmsPlayAddressService extends IService<PmsPlayAddress> {

    /**
     * 查询所有的游玩地址
     * @return
     */
    List<PmsPlayAddressVo> findAllPlayAddress();
}
