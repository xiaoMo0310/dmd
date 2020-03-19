package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.DmdIntegralGiftMapper;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.model.vo.IntegralProductVo;
import com.dmd.mall.service.DmdIntegralGiftService;
import com.dmd.mall.service.DmdIntegralGiftSpeService;
import com.dmd.mall.service.UmsMemberService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 积分好礼兑换表，兑换物品由后台管理员编辑。 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DmdIntegralGiftServiceImpl extends BaseService<DmdIntegralGift> implements DmdIntegralGiftService {

    @Autowired
    private DmdIntegralGiftMapper dmdIntegralGiftMapper;
    @Autowired
    private DmdIntegralGiftSpeService integralGiftSpeService;
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public List<IntegralGiftsBean> queryIntegralGifts() {
        return dmdIntegralGiftMapper.queryIntegralGifts();
    }

    @Override
    public List<IntegralGiftsBean> queryIntegralGiftsPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return dmdIntegralGiftMapper.queryIntegralGifts();
    }

    @Override
    public List<IntegralGiftsBean> queryIntegralGiftsById(Long id) {
        return dmdIntegralGiftMapper.queryIntegralGiftsById(id);
    }

    @Override
    public int addIntegralGifts(IntegralGiftsBean integralGiftsBean) {
        //添加时间为当前时间
        integralGiftsBean.setCreateTime(new Date());
        integralGiftsBean.setUpdateTime(null);
        return dmdIntegralGiftMapper.addIntegralGifts(integralGiftsBean);
    }

    @Override
    public IntegralGiftsBean findIntegralGiftsInfoById(Long id) {
        return dmdIntegralGiftMapper.findIntegralGiftsInfoById(id);
    }

    @Override
    public int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean) {
        integralGiftsBean.setUpdateTime(new Date());
        return dmdIntegralGiftMapper.updateIntegralGiftsById(integralGiftsBean);
    }

    @Override
    public int deleteIntegralGiftsById(Long id) {
        return dmdIntegralGiftMapper.deleteIntegralGiftsById(id);
    }

    @Override
    public List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id) {
        return integralGiftSpeService.queryIntegralGiftsSpeById(id);
    }

    @Override
    public Integer selectIntegralGiftsSpecStock(Long id, String size, String color) {
        return integralGiftSpeService.selectIntegralGiftsSpecStock(id,size,color);
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
        orderDetail.setProductPrice(new BigDecimal(integralGift.getIntegral()));
        orderDetail.setTotalPrice(new BigDecimal(integralGift.getIntegral() * quantity));
        orderDetail.setRealAmount(new BigDecimal(integralGift.getIntegral() * quantity));
        orderDetail.setProductId(integralGift.getId());
        orderDetail.setProductPic(integralGift.getPicture());
        orderDetail.setProductName(integralGift.getName());
        orderDetail.setProductTitle(integralGift.getName());
        orderDetail.setProductType(4);
        orderDetail.setProductQuantity(quantity);
        //封装商品sku数据
        /*orderDetail.setProductSkuId(dmdIntegralGiftSpe.getId());
        List<Map> list = this.packageSpecMessage(dmdIntegralGiftSpe);
        orderDetail.setProductAttr(JSONArray.toJSONString(list));*/
        return orderDetail;
    }

    @Override
    public IntegralProductVo settlementIntegralProduct(LoginAuthDto loginAuthDto, Long productSkuId, Integer productQuantity) {
        //查询积分商品sku数据
        DmdIntegralGiftSpe integralGiftSpe = integralGiftSpeService.selectByKey(productSkuId);
        //查询积分好礼商品
        DmdIntegralGift dmdIntegralGift = dmdIntegralGiftMapper.selectById(integralGiftSpe.getGiftId());
        IntegralProductVo integralProductVo = new IntegralProductVo();
        integralProductVo.setProductId(dmdIntegralGift.getId());
        integralProductVo.setProductIntegral(dmdIntegralGift.getIntegral());
        integralProductVo.setProductName(dmdIntegralGift.getName());
        integralProductVo.setProductPic(dmdIntegralGift.getPicture().split(",")[0]);
        integralProductVo.setProductQuantity(productQuantity);
        Integer totalIntegral = dmdIntegralGift.getIntegral() * productQuantity;
        //查询当前用户可用积分数量
        UmsMember umsMember = umsMemberService.selectByLoginAuthDto(loginAuthDto);
        if(totalIntegral > umsMember.getIntegration()){
            throw new OmsBizException(ErrorCodeEnum.OMS10031021);
        }
        integralProductVo.setTotalIntegral(totalIntegral);
        integralProductVo.setAvailableIntegral(umsMember.getIntegration());
        integralProductVo.setProductSpec(packageSpecMessage(integralGiftSpe));
        return integralProductVo;
    }


    @Override
    public List<Map> packageSpecMessage(DmdIntegralGiftSpe dmdIntegralGiftSpe) {
        List<Map> list = new ArrayList<>();
        if(!StringUtils.isEmpty(dmdIntegralGiftSpe.getSize())){
            Map mapA = new HashMap(0);
            mapA.put("key","尺码");
            mapA.put("value", dmdIntegralGiftSpe.getSize());
            list.add(mapA);
        }
        if(!StringUtils.isEmpty(dmdIntegralGiftSpe.getColor())){
            Map mapB = new HashMap(0);
            mapB.put("key","颜色");
            mapB.put("value", dmdIntegralGiftSpe.getColor());
            list.add(mapB);
        }
        return list;
    }

    @Override
    public Integer selectIntegralGiftsSpeNum(Long id) {
        return integralGiftSpeService.selectIntegralGiftsSpeNum(id);
    }
}
