package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.OmsFashionableMapper;
import com.dmd.admin.model.domain.OmsFashionable;
import com.dmd.admin.model.dto.BillingDetailDto;
import com.dmd.admin.service.OmsFashionableService;
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
 * 分账表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsFashionableServiceImpl extends BaseService<OmsFashionable> implements OmsFashionableService {

    @Autowired
    private OmsFashionableMapper omsFashionableMapper;

    @Override
    public OmsFashionable selectByCollectingNo(String orderSn) {
        return omsFashionableMapper.selectByCollectingNo(orderSn);
    }

    @Override
    public PageInfo<OmsFashionable> findFashionableList(BillingDetailDto billingDetailDto) {
        PageHelper.startPage(billingDetailDto.getPageNum(), billingDetailDto.getPageSize());
        List<OmsFashionable> fashionables = omsFashionableMapper.selectFashionableList(billingDetailDto);
        return new PageInfo<>(fashionables);
    }

    @Override
    public int updateFashionableStatus(LoginAuthDto loginAuthDto, String collectingNo, Integer status) {
        return omsFashionableMapper.updateFashionableStatus(loginAuthDto.getUserName(), loginAuthDto.getUserId(), collectingNo, status);
    }
}
