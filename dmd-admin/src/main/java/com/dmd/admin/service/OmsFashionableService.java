package com.dmd.admin.service;

import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.admin.model.vo.FashionableAndOrderVo;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 分账表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
public interface OmsFashionableService extends IService<OmsFashionable> {

    /**
     * 根据分帐单编号查询信息
     * @param orderSn
     * @return
     */
    OmsFashionable selectByCollectingNo(String orderSn);

    /**
     * 查询待分账的账单
     * @param billingDetailDto
     * @return
     */
    PageInfo<OmsFashionable> findFashionableList(BillingDetailDto billingDetailDto);

    /**
     * 根据分账单号修改分账状态为异常待处理
     * @param collectingNo
     * @return
     */
    int updateFashionableStatus(LoginAuthDto loginAuthDto, String collectingNo, Integer status);

    /**
     * 根据分账id查询分账信息及订单数据
     * @param fashionableId
     * @return
     */
    FashionableAndOrderVo selectOrderAndFashionableById(Long fashionableId);
}
