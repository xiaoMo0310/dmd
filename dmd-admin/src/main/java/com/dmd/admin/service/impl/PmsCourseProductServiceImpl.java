package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.PmsCourseProductMapper;
import com.dmd.admin.model.domain.PmsCourseProduct;
import com.dmd.admin.model.dto.PmsCourseProductDto;
import com.dmd.admin.model.dto.PmsCourseProductListDto;
import com.dmd.admin.service.PmsCourseProductService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 课程商品表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-11
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class
PmsCourseProductServiceImpl extends BaseService<PmsCourseProduct> implements PmsCourseProductService {

    @Autowired
    private PmsCourseProductMapper pmsCourseProductMapper;

    @Override
    public PageInfo<PmsCourseProduct> findCourseProductList(PmsCourseProductListDto courseProductListDto) {
        PageHelper.startPage(courseProductListDto.getPageNum(), courseProductListDto.getPageSize(), courseProductListDto.getOrderBy());
        List<PmsCourseProduct> courseProducts = pmsCourseProductMapper.selectCourseProductList(courseProductListDto);
        return new PageInfo<PmsCourseProduct>(courseProducts);
    }

    @Override
    public int updateProductApprovalStatus(LoginAuthDto loginAuthDto, PmsCourseProductDto courseProduct) {
        return pmsCourseProductMapper.updateProductApprovalStatus(loginAuthDto.getUserId(), loginAuthDto.getUserName(), courseProduct);
    }

    @Override
    public Integer queryAudited() {
        return pmsCourseProductMapper.queryAudited();
    }

    @Override
    public Integer queryAuditPass() {
        return pmsCourseProductMapper.queryAuditPass();
    }

    @Override
    public Integer queryAuditFailed() {
        return pmsCourseProductMapper.queryAuditFailed();
    }

    @Override
    public Integer queryAllMerchandise() {
        return pmsCourseProductMapper.queryAllMerchandise();
    }

    @Override
    public PmsCourseProduct findCourseProductAndPageNum(PmsCourseProductListDto courseProductListDto) {
        //查询商品的信息
        PmsCourseProduct pmsCourseProduct = pmsCourseProductMapper.selectByPrimaryKey(courseProductListDto.getProductId());
        //统计数量
        Long beforeNum = pmsCourseProductMapper.selectBeforeNumByStatus(pmsCourseProduct.getStatus());
        Long sameNum = pmsCourseProductMapper.selectSameNumByStatusAndId(pmsCourseProduct.getId(), pmsCourseProduct.getStatus());
        Long pageNum = ((beforeNum+sameNum)/courseProductListDto.getPageSize()) + 1;
        pmsCourseProduct.setPageNum(Integer.valueOf(pageNum + ""));
        return pmsCourseProduct;
    }
}
