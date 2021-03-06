package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsCart;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 购物车表 Mapper 接口
 * </p>
 *
 * @author 王海成
 * @since 2019-10-12
 */
@Mapper
@Component
public interface OmsCartMapper extends MyMapper<OmsCart> {

    List<OmsCart> selectCartByUserIdAndCartId(@Param("userId") Long userId, @Param("shopId") Long shopId, @Param("cartIds") List<Long> cartIds);
    public List<OmsCart> findOmsCart(@Param("memberId") Integer memberId);
    public List<OmsCart> findOmsCart(@Param("memberId") Long memberId);
    public List<OmsCart> findOmsCartById(@Param("ids") List<Long> ids);
    public int addOmsCart(OmsCart omsCart);
    public int updateOmsCart(
            @Param("quantity") String quantity,
            @Param("deleteStatus") String deleteStatus,
            @Param("ids") List<Long> id,
            @Param("updateTime") String updateTime);
}
