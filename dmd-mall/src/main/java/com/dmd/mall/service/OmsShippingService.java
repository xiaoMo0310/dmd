package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsShipping;
import com.dmd.mall.model.dto.OmsShippingDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 收货人信息表 服务类
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-20
 */
public interface OmsShippingService extends IService<OmsShipping> {
    
    /**
     * 编辑收货人地址.
     *
     * @param loginAuthDto the login auth dto
     * @param shipping     the shipping
     * @return the int
     */
    int saveShipping(LoginAuthDto loginAuthDto, OmsShippingDto shipping);

    /**
     * 删除收货人地址
     * @param loginAuthDto
     * @param shippingId
     * @return
     */
    int deleteShipping(LoginAuthDto loginAuthDto, Long shippingId);

    /**
     * 查询用户默认地址
     * @param loginAuthDto
     * @return
     */
    OmsShipping selectByShippingIdUserId(LoginAuthDto loginAuthDto);

    /**
     * 分页查询当前用户收货人地址列表
     * @param loginAuthDto
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo queryListWithPageByUserId(LoginAuthDto loginAuthDto, int pageNum, int pageSize);

    /**
     * 分页查询当前用户收货人地址列表
     * @param loginAuthDto
     * @return
     */
    List<OmsShipping> selectByUserId(LoginAuthDto loginAuthDto);

    /**
     * 设置默认收货地址.
     * @param loginAuthDto the login auth dto
     * @param shippingId    the shipping id
     * @return the default address
     */
    int setDefaultAddress(LoginAuthDto loginAuthDto, Long shippingId);
}
