package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.OmsShippingMapper;
import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.mapper.OmsCartMapper;
import com.dmd.mall.service.OmsCartService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
<<<<<<< HEAD
 * @author 王海成
=======
 * @author YangAnsheng
>>>>>>> origin/master
 * @since 2019-10-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsCartServiceImpl extends BaseService<OmsCart> implements OmsCartService {
    @Autowired
    private OmsShippingMapper omsShippingMapper;
    @Autowired
    private OmsCartMapper omsCartMapper;

    @Override
    public List<OmsCart> findOmsCart(Integer memberId) {
        return omsCartMapper.findOmsCart(memberId);
    }

    @Override
    public List<OmsCart> findOmsCartById(List<Integer> ids) {
        return omsCartMapper.findOmsCartById(ids);
    }

    @Override
    public int addOmsCart(OmsCart omsCart) {
        return omsCartMapper.addOmsCart(omsCart);
    }

    @Override
    public int updateOmsCart(String quantity, String deleteStatus, Integer id, String updateTime) {
        return omsCartMapper.updateOmsCart(quantity,deleteStatus,id,updateTime);
    }

    @Override
    public Map<String, Object> beforeSubmitOrder(List<Integer> ids, Integer memberId) {
        findOmsCartById(ids);
        return null;
    }
}
