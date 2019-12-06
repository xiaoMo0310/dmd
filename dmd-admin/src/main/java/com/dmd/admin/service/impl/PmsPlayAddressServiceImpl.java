package com.dmd.admin.service.impl;

import com.dmd.BeanUtils;
import com.dmd.admin.mapper.PmsPlayAddressMapper;
import com.dmd.admin.model.domain.PmsPlayAddress;
import com.dmd.admin.model.dto.PmsPlayAddressDto;
import com.dmd.admin.service.PmsPlayAddressService;
import com.dmd.base.dto.BaseQuery;
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
 * 潜水学习地址表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PmsPlayAddressServiceImpl extends BaseService<PmsPlayAddress> implements PmsPlayAddressService {

    @Autowired
    private PmsPlayAddressMapper pmsPlayAddressMapper;

    @Override
    public PageInfo<PmsPlayAddress> findPlayAddressList(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<PmsPlayAddress> pmsPlayAddresses = pmsPlayAddressMapper.selectAll();
        return new PageInfo<>(pmsPlayAddresses);
    }

    @Override
    public int updateIsDefaultById(LoginAuthDto loginAuthDto, Long id) {
        //查询默认地址
        PmsPlayAddress pmsPlayAddress = pmsPlayAddressMapper.selectIsDefaultAddress();
        if(pmsPlayAddress == null){
            updateIsDefaultPlayAddress(id, 1, loginAuthDto);
        }else {
            if(!pmsPlayAddress.getId().equals(id)){
                updateIsDefaultPlayAddress(pmsPlayAddress.getId(), 0, loginAuthDto);
                updateIsDefaultPlayAddress(id, 1, loginAuthDto);
            }
        }
        return 1;
    }

    @Override
    public int saveOrUpdate(LoginAuthDto loginAuthDto, PmsPlayAddressDto pmsPlayAddressDto) {
        PmsPlayAddress pmsPlayAddress = new PmsPlayAddress();
        BeanUtils.copyProperties(pmsPlayAddressDto, pmsPlayAddress);
        //查询当前用户是否有地址信息
        int resultInt;
        pmsPlayAddress.setUpdateInfo(loginAuthDto);
        if (pmsPlayAddress.isNew()) {
            //查询默认地址
            PmsPlayAddress playAddress = pmsPlayAddressMapper.selectIsDefaultAddress();
            if(pmsPlayAddress.getIsDefault() == 1){
                if(playAddress != null){
                    this.updateIsDefaultPlayAddress(playAddress.getId(), 0, loginAuthDto);
                }
            }
            resultInt = pmsPlayAddressMapper.insertSelective(pmsPlayAddress);
        } else {
            if(pmsPlayAddress.getIsDefault() == 1){
                //查询默认地址
                PmsPlayAddress playAddress = pmsPlayAddressMapper.selectIsDefaultAddress();
                if(playAddress != null){
                    if (!pmsPlayAddress.getId().equals(playAddress.getId())) {
                        this.updateIsDefaultPlayAddress(playAddress.getId(), 0, loginAuthDto);
                    }
                }
            }
            resultInt = pmsPlayAddressMapper.updateByPrimaryKeySelective(pmsPlayAddress);
        }
        return resultInt;
    }

    @Override
    public PmsPlayAddress findPlayAddressById(Long id) {
        return pmsPlayAddressMapper.selectPlayAddressById(id);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return pmsPlayAddressMapper.deleteByIds(ids);
    }

    /**
     * 修改默认地址
     * @param id
     */
    public void updateIsDefaultPlayAddress(Long id, Integer isDefault, LoginAuthDto loginAuthDto) {
        PmsPlayAddress updatePlayAddress = new PmsPlayAddress();
        updatePlayAddress.setId(id);
        updatePlayAddress.setIsDefault(isDefault);
        updatePlayAddress.setUpdateInfo(loginAuthDto);
        pmsPlayAddressMapper.updateByPrimaryKeySelective(updatePlayAddress);
    }
}
