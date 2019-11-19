package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.OmsOrderItemMapper;
import com.dmd.mall.model.domain.DmdIntegralGift;
import com.dmd.mall.model.domain.DmdIntegralGiftSpe;
import com.dmd.mall.model.domain.OmsOrderItem;
import com.dmd.mall.service.DmdIntegralGiftSpeService;
import com.dmd.mall.service.OmsOrderItemService;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-14
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsOrderItemServiceImpl extends BaseService<OmsOrderItem> implements OmsOrderItemService {

    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;
    @Autowired
    private DmdIntegralGiftSpeService integralGiftSpeService;

    @Override
    public void batchInsertOrderDetail(List<OmsOrderItem> omcOrderDetailList) {
        omcOrderDetailList.forEach(omsOrderItem -> {
            int i = omsOrderItemMapper.insertSelective(omsOrderItem);
            if(i <= 0){
                throw new OmsBizException(ErrorCodeEnum.OMS10031009);
            }
        });
    }

    @Override
    public List<OmsOrderItem> getListByOrderNoUserId(String orderSn) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(orderSn), "订单号不能为空");
        return omsOrderItemMapper.getListByOrderNoUserId(orderSn);
    }

    @Override
    public OmsOrderItem createIntegralOrderItem(DmdIntegralGift integralGift, Long skuId, Integer quantity) {
        //查询积分商品规格数据
        DmdIntegralGiftSpe dmdIntegralGiftSpe = integralGiftSpeService.selectByKey(skuId);
        if(dmdIntegralGiftSpe == null){
            throw new PmsBizException(ErrorCodeEnum.PMS10021031, integralGift.getId());
        }
        if(integralGift.getStatus() == 1){
            logger.error("商品已下架, integralGift={}", integralGift.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021017, integralGift.getId());
        }
        //校验库存
        //查询商品的库存
        if (quantity > dmdIntegralGiftSpe.getSpecStock()) {
            logger.error("商品库存不足, productId={}", integralGift.getId());
            throw new OmsBizException(ErrorCodeEnum.PMS10021016, integralGift.getId());
        }
        OmsOrderItem orderDetail = new OmsOrderItem();
        //封装商品的信息
        orderDetail.setProductId(integralGift.getId());
        orderDetail.setProductPic(integralGift.getPicture());
        orderDetail.setProductName(integralGift.getName());
        orderDetail.setProductType(4);
        orderDetail.setProductQuantity(quantity);
        //封装商品sku数据
        orderDetail.setProductSkuId(dmdIntegralGiftSpe.getId());
        List<Map> list = new ArrayList<>();
        if(dmdIntegralGiftSpe.getSize() != null){
            Map mapA = new HashMap(0);
            mapA.put("key","尺码");
            mapA.put("value", dmdIntegralGiftSpe.getSize());
            list.add(mapA);
        }
        if(dmdIntegralGiftSpe.getColor() != null){
            Map mapB = new HashMap(0);
            mapB.put("key","颜色");
            mapB.put("value", dmdIntegralGiftSpe.getColor());
            list.add(mapB);
        }
        orderDetail.setProductAttr(JSONArray.toJSONString(list));
        return orderDetail;
    }
}
