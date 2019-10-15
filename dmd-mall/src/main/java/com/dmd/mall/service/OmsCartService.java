package com.dmd.mall.service;

import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.model.vo.OrderCreateVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 服务类
 * </p>
 *
 * @author 王海成
 * @since 2019-10-12
 */
public interface OmsCartService extends IService<OmsCart> {
        public List<OmsCart> findOmsCart(Integer memberId);//查询购物车信息
        public List<OmsCart> findOmsCartById(List<Integer> ids);//根据id查询购物车信息
        public int addOmsCart(OmsCart omsCart);//添加购物车信息
        public int updateOmsCart(String quantity, String deleteStatus, Integer id, String updateTime);
        public Map<String,Object> beforeSubmitOrder(List<Integer> ids,Integer memberId);

        /**
         * 根据用户id和购物车id查询购物车的信息
         * @param userId
         * @param cartList
         * @return
         */
        List<OmsOrderItem> getCartOrderItem(Long userId, List<OmsCart> cartList);

        /**
         * 结算所购选的购物车
         * @param cartIds
         * @return
         */
        OrderCreateVo settlementById(Long[] cartIds);
}
