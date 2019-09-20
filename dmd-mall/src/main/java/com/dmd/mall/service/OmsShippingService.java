package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsShipping;
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
     *
     * @return the int
     */
    int saveShipping(LoginAuthDto loginAuthDto, OmsShipping shipping);

    /**
     * 删除收货人地址.
     *
     * @param userId     the user id
     * @param shippingId the shipping id
     *
     * @return the int
     */
    int deleteShipping(Long userId, Integer shippingId);

    /**
     * 根据Id查询收货人地址.
     *
     * @param userId     the user id
     * @param shippingId the shipping id
     *
     * @return the omc shipping
     */
    OmsShipping selectByShippingIdUserId(Long userId, Long shippingId);

    /**
     * 分页查询当前用户收货人地址列表.
     *
     * @param userId   the user id
     * @param pageNum  the page num
     * @param pageSize the page size
     *
     * @return the page info
     */
    PageInfo queryListWithPageByUserId(Long userId, int pageNum, int pageSize);

    /**
     * Select by user id list.
     *
     * @param userId the user id
     *
     * @return the list
     */
    List<OmsShipping> selectByUserId(Long userId);

    /**
     * 设置默认收货地址.
     *
     * @param loginAuthDto the login auth dto
     * @param shippingId    the shipping id
     *
     * @return the default address
     */
    int setDefaultAddress(LoginAuthDto loginAuthDto, Long shippingId);
}
