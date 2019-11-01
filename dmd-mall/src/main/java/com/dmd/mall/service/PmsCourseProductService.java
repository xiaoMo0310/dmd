package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.PmsCourseProduct;
import com.dmd.mall.model.vo.PmsCourseProductVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 课程商品表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-09-26
 */
public interface PmsCourseProductService extends IService<PmsCourseProduct> {

    /**
     * 编辑课程产品的信息
     * @param loginAuthDto
     * @param courseProduct
     * @return
     */
    int saveCourseProductMessage(LoginAuthDto loginAuthDto, PmsCourseProduct courseProduct);

    /**
     * 分页查询所有课程产品的信息
     * @param loginAuthDto
     * @return
     */
    List<PageInfo> findCourseProduct(BaseQuery baseQuery, LoginAuthDto loginAuthDto);

    /**
     *根据id查询详细的信息
     * @param loginAuthDto
     * @param id
     * @return
     */
    PmsCourseProductVo findcourseProductById(LoginAuthDto loginAuthDto, Long id);
}
