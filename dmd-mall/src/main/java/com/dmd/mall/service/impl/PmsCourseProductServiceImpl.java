package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.PmsCourseProductMapper;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.vo.PmsCourseListVo;
import com.dmd.mall.model.vo.PmsCourseProductVo;
import com.dmd.mall.model.vo.PmsDictVo;
import com.dmd.mall.service.PmsCourseProductService;
import com.dmd.mall.service.PmsDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程商品表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsCourseProductServiceImpl extends BaseService<PmsCourseProduct> implements PmsCourseProductService {

    @Autowired
    private PmsCourseProductMapper pmsCourseProductMapper;
    @Autowired
    private PmsDictService pmsDictService;

    @Override
    public int saveCourseProductMessage(LoginAuthDto loginAuthDto, PmsCourseProduct courseProduct) {
        int resultInt;
        courseProduct.setUpdateInfo(loginAuthDto);
        if (courseProduct.isNew()) {
            courseProduct.setStatus(2);
            courseProduct.setApprovalStatus(1);
            resultInt = pmsCourseProductMapper.insertSelective(courseProduct);
        } else {
            resultInt = pmsCourseProductMapper.updateByPrimaryKeySelective(courseProduct);
        }
        return resultInt;
    }

    @Override
    public List<PageInfo> findcourseProduct(BaseQuery baseQuery, LoginAuthDto loginAuthDto) {
        List<PmsDictVo> courseTypes = pmsDictService.findAllProcessingType("course_type");
        return courseTypes.stream().map(courseType -> {
            //根据类型id查询过商品的信息
            PageInfo<PmsCourseListVo> courseProductByType = findCourseProductByType(baseQuery.getPageNum(), baseQuery.getPageSize(), courseType.getDictKey());
            return courseProductByType;
        }).collect(Collectors.toList());
    }

    @Override
    public PmsCourseProductVo findcourseProductById(LoginAuthDto loginAuthDto, Long id) {
        PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectByPrimaryKey(id);
        PmsCourseProductVo courseProductVo = new PmsCourseProductVo();
        BeanUtils.copyProperties(pmsCourseProduct, courseProductVo);
        return courseProductVo;
    }

    public PageInfo<PmsCourseListVo> findCourseProductByType(Integer pageNum, Integer pageSize, String type){
        PmsCourseProduct pmsCourseProduct = new PmsCourseProduct();
        pmsCourseProduct.setProductType(type);
        pmsCourseProduct.setStatus(1);
        pmsCourseProduct.setApprovalStatus(2);
        PageHelper.startPage(pageNum, pageSize);
        List<PmsCourseProduct> courseProducts = pmsCourseProductMapper.select(pmsCourseProduct);
        List<PmsCourseListVo> courseProductVos = courseProducts.stream().map(courseProduct -> {
            PmsCourseListVo courseProductVo = new PmsCourseListVo();
            BeanUtils.copyProperties(courseProduct, courseProductVo);
            return courseProductVo;
        }).collect(Collectors.toList());
        return new PageInfo<>(courseProductVos);
    }

}
