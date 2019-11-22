package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.OmsShipping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 收货人信息表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-20
 */
@Component
@Mapper
public interface OmsShippingMapper extends MyMapper<OmsShipping> {

    /**
     * Delete by shipping id user id int.
     *
     * @param userId     the user id
     * @param shippingId the shipping id
     *
     * @return the int
     */
    int deleteByShippingIdUserId(@Param("userId") Long userId, @Param("userType") String userType, @Param("shippingId") Long shippingId);

    /**
     * Select by shipping id user id omc shipping.
     *
     * @param userId     the user id
     * @param shippingId the shipping id
     *
     * @return the omc shipping
     */
    OmsShipping selectByShippingIdUserId(@Param("userId") Long userId, @Param("userType") String userType, @Param("shippingId") Long shippingId);

    /**
     * Select by user id list.
     *
     * @param userId the user id
     *
     * @return the list
     */
    List<OmsShipping> selectByUserId(@Param("userId") Long userId, @Param("userType") String userType);

    /**
     * Select default address by user id omc shipping.
     *
     * @param userId the user id
     *
     * @return the omc shipping
     */
    OmsShipping selectDefaultAddressByUserId(@Param("userId") Long userId, @Param("userType") String userType);
}
