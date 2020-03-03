package com.dmd.admin.service.impl;

import com.dmd.admin.model.domain.UmsShopLevelExplain;
import com.dmd.admin.mapper.UmsShopLevelExplainMapper;
import com.dmd.admin.service.UmsShopLevelExplainService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 教练店铺店铺表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsShopLevelExplainServiceImpl extends BaseService<UmsShopLevelExplain> implements UmsShopLevelExplainService {

    @Autowired
    private UmsShopLevelExplainMapper umsShopLevelExplainMapper;

    @Override
    public int saveOrUpdateShopLevelExplain(LoginAuthDto loginAuthDto, UmsShopLevelExplain shopLevelExplain) {
        int result;
        shopLevelExplain.setUpdateInfo( loginAuthDto );
        if(shopLevelExplain.getId() == null){
            //添加
            result = umsShopLevelExplainMapper.insertSelective( shopLevelExplain );
        }else {
            //修改
            result = umsShopLevelExplainMapper.updateByPrimaryKey( shopLevelExplain );
        }
        return result;
    }
}
