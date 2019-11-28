package com.dmd.mall.service.impl;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.PmsBizException;
import com.dmd.mall.mapper.PmsPlayAddressMapper;
import com.dmd.mall.model.domain.PmsPlayAddress;
import com.dmd.mall.model.vo.PmsPlayAddressVo;
import com.dmd.mall.service.PmsPlayAddressService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 潜水学习地址表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-08
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsPlayAddressServiceImpl extends BaseService<PmsPlayAddress> implements PmsPlayAddressService {

    @Autowired
    private PmsPlayAddressMapper pmsPlayAddressMapper;

    @Override
    public List<PmsPlayAddressVo> findAllPlayAddress() {
       List<PmsPlayAddress> pmsPlayAddresses = pmsPlayAddressMapper.selectAllPlayAddress();
       if(CollectionUtils.isEmpty(pmsPlayAddresses)){
           throw new PmsBizException(ErrorCodeEnum.PMS10021001);
       }

        return pmsPlayAddresses.stream().map(pmsPlayAddress -> {
            PmsPlayAddressVo playAddressVo = new PmsPlayAddressVo();
            BeanUtils.copyProperties(pmsPlayAddress, playAddressVo);
            return playAddressVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PmsPlayAddress selectDefaultAddress() {
        return pmsPlayAddressMapper.selectDefaultAddress();
    }
}
