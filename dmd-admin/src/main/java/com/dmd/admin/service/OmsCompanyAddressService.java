package com.dmd.admin.service;

import com.dmd.admin.model.domain.OmsCompanyAddress;

import java.util.List;

/**
 * 收货地址管Service
 *
 * @author macro
 * @date 2018/10/18
 */
public interface OmsCompanyAddressService {
    /**
     * 获取全部收货地址
     * @return
     */
    List<OmsCompanyAddress> list();
}
