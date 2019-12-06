package com.dmd.admin.service;

import com.dmd.admin.model.domain.PmsPlayAddress;
import com.dmd.admin.model.dto.PmsPlayAddressDto;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 潜水学习地址表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-06
 */
public interface PmsPlayAddressService extends IService<PmsPlayAddress> {

    /**
     * 查询潜水地址集合
     * @param baseQuery
     * @return
     */
    PageInfo<PmsPlayAddress> findPlayAddressList(BaseQuery baseQuery);

    /**
     * 根据id修改默认地址
     * @param loginAuthDto
     * @param id
     * @return
     */
    int updateIsDefaultById(LoginAuthDto loginAuthDto, Long id);

    /**
     * 修改潜水地址信息
     * @param loginAuthDto
     * @param pmsPlayAddressDto
     * @return
     */
    int saveOrUpdate(LoginAuthDto loginAuthDto, PmsPlayAddressDto pmsPlayAddressDto);

    /**
     * 根据id查询潜水地址信息
     * @param id
     * @return
     */
    PmsPlayAddress findPlayAddressById(Long id);

    /**
     *删除潜水地址信息
     * @param ids
     * @return
     */
    int deleteByIds(List<Long> ids);
}
