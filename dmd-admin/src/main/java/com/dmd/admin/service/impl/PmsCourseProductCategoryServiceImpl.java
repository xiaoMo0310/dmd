package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.mapper.PmsCourseProductCategoryMapper;
import com.dmd.admin.model.domain.PmsCourseProductCategory;
import com.dmd.admin.model.dto.PmsCourseProductCategoryDto;
import com.dmd.admin.model.vo.PmsCourseProductCategoryVo;
import com.dmd.admin.service.PmsCourseProductCategoryService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 产品分类 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductCategoryServiceImpl extends BaseService<PmsCourseProductCategory> implements PmsCourseProductCategoryService {

    @Autowired
    private PmsCourseProductCategoryMapper courseProductCategoryMapper;

    @Override
    public int createCategory(LoginAuthDto loginAuthDto, PmsCourseProductCategoryDto courseProductCategoryDto) {
        PmsCourseProductCategory productCategory = new PmsCourseProductCategory();
        BeanUtils.copyProperties(courseProductCategoryDto, productCategory);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        productCategory.setUpdateInfo( loginAuthDto );
        int count = courseProductCategoryMapper.insertSelective(productCategory);
        return count;
    }

    @Override
    public int updateCategory(LoginAuthDto loginAuthDto, Long id, PmsCourseProductCategoryDto courseProductCategoryDto) {
        PmsCourseProductCategory productCategory = new PmsCourseProductCategory();
        productCategory.setId(id);
        BeanUtils.copyProperties(courseProductCategoryDto, productCategory);
        setCategoryLevel(productCategory);
        productCategory.setUpdateInfo( loginAuthDto );
        return courseProductCategoryMapper.updateByPrimaryKeySelective(productCategory);
    }

    @Override
    public JSONObject getList(Long parentId, BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy());
        List<PmsCourseProductCategory> courseProductCategories = courseProductCategoryMapper.selectByParentId( parentId );
        PageInfo<PmsCourseProductCategory> courseProductCategoryPageInfo = new PageInfo<>( courseProductCategories );
        List<PmsCourseProductCategory> productCategoryPageInfoList = courseProductCategoryPageInfo.getList();
        List<PmsCourseProductCategoryVo> courseProductCategoryVos = productCategoryPageInfoList.stream().map( pmsCourseProductCategory -> {
            PmsCourseProductCategoryVo courseProductCategoryVo = new PmsCourseProductCategoryVo();
            BeanUtils.copyProperties( pmsCourseProductCategory, courseProductCategoryVo );
            courseProductCategoryVo.setChildren( courseProductCategoryMapper.selectByParentId( pmsCourseProductCategory.getId() ) );
            return courseProductCategoryVo;
        } ).collect( Collectors.toList() );
        JSONObject object = new JSONObject();
        object.put( "list",  courseProductCategoryVos);
        object.put( "total",  courseProductCategoryPageInfo.getTotal());
        return object;
    }

    @Override
    public int deleteCourseProductCategory(Long id) {
        //查询是否有下级分类
         List<PmsCourseProductCategory> courseProductCategorys = courseProductCategoryMapper.selectByParentId( id );
         if(!CollectionUtils.isEmpty( courseProductCategorys )){
             List<Long>  ids = courseProductCategorys.stream().map( PmsCourseProductCategory::getId ).collect( Collectors.toList() );
             Example example = new Example( PmsCourseProductCategory.class );
             example.createCriteria().andIn( "id", ids );
             courseProductCategoryMapper.deleteByExample( example );
         }
        return courseProductCategoryMapper.deleteByPrimaryKey( id );
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsCourseProductCategory productCategory = new PmsCourseProductCategory();
        productCategory.setShowStatus(showStatus);
        Example example = new Example( PmsCourseProductCategory.class );
        example.createCriteria().andIn( "id", ids );
        return courseProductCategoryMapper.updateByExampleSelective(productCategory, example);
    }

    @Override
    public List<Map> findParentIdsById(List<Long> ids) {
        return courseProductCategoryMapper.selectParentIdsByIds(ids);
    }

    /**
     * 根据分类的parentId设置分类的level
     */
    private void setCategoryLevel(PmsCourseProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类时选择根据父分类level设置
            PmsCourseProductCategory parentCategory = courseProductCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (parentCategory != null) {
                productCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }
    }
}
