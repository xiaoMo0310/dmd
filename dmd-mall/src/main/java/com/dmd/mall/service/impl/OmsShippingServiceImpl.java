package com.dmd.mall.service.impl;

import com.dmd.PublicUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.constant.OmsApiConstant;
import com.dmd.mall.exceptions.OmsBizException;
import com.dmd.mall.mapper.OmsShippingMapper;
import com.dmd.mall.model.domain.OmsShipping;
import com.dmd.mall.service.OmsShippingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收货人信息表 服务实现类
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsShippingServiceImpl extends BaseService<OmsShipping> implements OmsShippingService {

    @Autowired
    private OmsShippingMapper omsShippingMapper;

    @Override
    public int saveShipping(LoginAuthDto loginAuthDto, OmsShipping shipping) {
        int resultInt;
        shipping.setUpdateInfo(loginAuthDto);
        if (shipping.isNew()) {
            resultInt = omsShippingMapper.insertSelective(shipping);
        } else {
            resultInt = omsShippingMapper.updateByPrimaryKeySelective(shipping);
        }
        return resultInt;
    }

    @Override
    public int deleteShipping(Long userId, Long shippingId) {
        return omsShippingMapper.deleteByShippingIdUserId(userId, shippingId);
    }

    @Override
    public OmsShipping selectByShippingIdUserId(Long userId, Long shippingId) {
        return omsShippingMapper.selectByShippingIdUserId(userId, shippingId);
    }

    @Override
    public PageInfo queryListWithPageByUserId(Long userId, int pageNum, int pageSize) {
        Preconditions.checkArgument(userId != null, ErrorCodeEnum.UMS10011001.msg());
        PageHelper.startPage(pageNum, pageSize);
        //TODO 写死 不维护收人列表
        List<OmsShipping> OmsShippingList = this.selectByUserId(1L);
        return new PageInfo<>(OmsShippingList);
    }

    @Override
    public List<OmsShipping> selectByUserId(Long userId) {
        Preconditions.checkArgument(userId != null, ErrorCodeEnum.UMS10011001.msg());
        return omsShippingMapper.selectByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int setDefaultAddress(LoginAuthDto loginAuthDto, Long shippingId) {
        Long userId = loginAuthDto.getUserId();
        Preconditions.checkArgument(shippingId != null, "地址ID不能为空");

        // 1. 查找当前默认地址
        OmsShipping OmsShipping = omsShippingMapper.selectDefaultAddressByUserId(userId);
        if (PublicUtil.isEmpty(OmsShipping)) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031007);
        }
        // 2. 判断默认地址和当前传入地址是否相同
        if (shippingId.equals(OmsShipping.getId())) {
            logger.info("所选地址和当前用户默认地址相同 userId={}, addressId={}", userId, shippingId);
            return 1;
        }
        // 3. 相同不处理不相同把当前改为非默认, 把当前地址改为默认地址
        setDefault(loginAuthDto, shippingId, OmsApiConstant.Shipping.DEFAULT);
        setDefault(loginAuthDto, OmsShipping.getId(), OmsApiConstant.Shipping.NOT_DEFAULT);

        return 1;
    }

    private void setDefault(LoginAuthDto loginAuthDto, Long shippingId, int isDefault) {
        int result;
        OmsShipping updateNotDefault = new OmsShipping();
        updateNotDefault.setDefaultAddress(isDefault);
        updateNotDefault.setUpdateInfo(loginAuthDto);
        updateNotDefault.setId(shippingId);
        result = omsShippingMapper.updateByPrimaryKeySelective(updateNotDefault);
        if (result < 1) {
            throw new OmsBizException(ErrorCodeEnum.OMS10031008, shippingId);
        }
    }
}
