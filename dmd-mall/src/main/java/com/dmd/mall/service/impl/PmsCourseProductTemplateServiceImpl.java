package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCourseProductTemplateMapper;
import com.dmd.mall.model.domain.PmsCourseProductTemplate;
import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.mall.model.vo.PmsCourseProductTemplateVo;
import com.dmd.mall.service.PmsCourseProductTemplateService;
import com.dmd.mall.service.UmsCoachShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 潜水产品模板表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductTemplateServiceImpl extends BaseService<PmsCourseProductTemplate> implements PmsCourseProductTemplateService {

    @Autowired
    private PmsCourseProductTemplateMapper courseProductTemplateMapper;
    @Autowired
    private UmsCoachShopService coachShopService;

    @Override
    public PageInfo<PmsCourseProductTemplateVo> findTemplateByShopId(Long shopId, BaseQuery baseQuery) {
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize() );
        List<PmsCourseProductTemplateVo> courseProductTemplates = courseProductTemplateMapper.selectTemplateByShopId(shopId);
        courseProductTemplates.forEach( courseProductTemplateVo -> {
            List<Map> relatedProduct = (List<Map>) JSONArray.parse( courseProductTemplateVo.getRelatedProduct() );
            courseProductTemplateVo.setRelatedProductList( relatedProduct );
        });
        return new PageInfo<>( courseProductTemplates );
    }

    @Override
    public int createOrUpdateTemplate(LoginAuthDto loginAuthDto, PmsCourseProductTemplate courseProductTemplate) {
        Preconditions.checkArgument( !loginAuthDto.getUserType().equals( "coach" ), ErrorCodeEnum.UMS10011023.msg() );
        List<Map> relatedProducts = (List<Map>) JSONArray.parse( courseProductTemplate.getRelatedProduct() );
        //其它装备价格
        BigDecimal totalRelatedProductPrice = new BigDecimal("0.00");
        for (Map relatedProduct : relatedProducts) {
            System.out.println(relatedProduct);
            System.out.println(Double.valueOf( (String) relatedProduct.get( "price" ) ) );
            totalRelatedProductPrice = totalRelatedProductPrice.add( BigDecimal.valueOf( Double.valueOf( (String) relatedProduct.get( "price" ) ) ) );
        }
        courseProductTemplate.setEquipmentPrice(totalRelatedProductPrice);
        courseProductTemplate.setTotalPrice( courseProductTemplate.getProductPrice().add( totalRelatedProductPrice ) );
        courseProductTemplate.setUpdateInfo( loginAuthDto );
        int result;
        if(courseProductTemplate.isNew()){
            //查询店铺信息
            UmsCoachShop coachShop = coachShopService.findByCoachId( loginAuthDto.getUserId() );
            courseProductTemplate.setUserId( loginAuthDto.getUserId() );
            courseProductTemplate.setShopId( coachShop.getId() );
            result = courseProductTemplateMapper.insertSelective( courseProductTemplate );
        }else {
            result = courseProductTemplateMapper.updateByPrimaryKeySelective( courseProductTemplate );
        }
        return result;
    }
}
