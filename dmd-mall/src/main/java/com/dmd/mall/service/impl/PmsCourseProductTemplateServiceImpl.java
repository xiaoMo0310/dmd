package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.dmd.base.dto.BaseQuery;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCourseProductTemplateMapper;
import com.dmd.mall.model.domain.PmsCourseProductTemplate;
import com.dmd.mall.model.vo.PmsCourseProductTemplateVo;
import com.dmd.mall.service.PmsCourseProductTemplateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
