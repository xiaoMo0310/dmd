package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.admin.mapper.PmsCourseProductTemplateMapper;
import com.dmd.admin.model.domain.PmsCourseProductTemplate;
import com.dmd.admin.service.PmsCourseProductTemplateService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 潜水产品模板表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductTemplateServiceImpl extends BaseService<PmsCourseProductTemplate> implements PmsCourseProductTemplateService {

    @Autowired
    private PmsCourseProductTemplateMapper courseProductTemplateMapper;

    @Override
    public int createOrUpdateTemplate(LoginAuthDto loginAuthDto, PmsCourseProductTemplate courseProductTemplate) {
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
            courseProductTemplate.setUserId( 0L );
            courseProductTemplate.setShopId( 0L );
            result = courseProductTemplateMapper.insertSelective( courseProductTemplate );
        }else {
            result = courseProductTemplateMapper.updateByPrimaryKeySelective( courseProductTemplate );
        }

        return result;
    }


    @Override
    public PageInfo<PmsCourseProductTemplate> getTemplateList(BaseQuery baseQuery) {
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy() );
        List<PmsCourseProductTemplate> courseProductTemplates = courseProductTemplateMapper.selectByUserId(0L);
        return new PageInfo<>( courseProductTemplates );
    }

    @Override
    public int deleteCourseProductTemplate(Long id) {
        return courseProductTemplateMapper.deleteByPrimaryKey( id );
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsCourseProductTemplate productTemplate = new PmsCourseProductTemplate();
        productTemplate.setShowStatus(showStatus);
        Example example = new Example( PmsCourseProductTemplate.class );
        example.createCriteria().andIn( "id", ids );
        return courseProductTemplateMapper.updateByExampleSelective(productTemplate, example);
    }
}
