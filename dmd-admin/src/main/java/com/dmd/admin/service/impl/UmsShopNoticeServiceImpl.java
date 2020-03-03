package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsShopNoticeMapper;
import com.dmd.admin.model.domain.UmsShopNotice;
import com.dmd.admin.service.UmsShopNoticeService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>
 * 教练店铺公告 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsShopNoticeServiceImpl extends BaseService<UmsShopNotice> implements UmsShopNoticeService {

    @Autowired
    private UmsShopNoticeMapper umsShopNoticeMapper;

    @Override
    public int saveShopNotice(LoginAuthDto loginAuthDto, UmsShopNotice umsShopNotice) {
        umsShopNotice.setUpdateInfo( loginAuthDto );
        return umsShopNoticeMapper.insert( umsShopNotice );
    }

    @Override
    public PageInfo<UmsShopNotice> selectShopNoticeByPage(LoginAuthDto loginAuthDto, UmsShopNotice umsShopNotice) {
        PageHelper.startPage( umsShopNotice.getPageNum(), umsShopNotice.getPageSize(), umsShopNotice.getOrderBy() );
        List<UmsShopNotice> umsShopNotices = umsShopNoticeMapper.selectByList( umsShopNotice );
        return new PageInfo<>( umsShopNotices );
    }

    @Override
    public int batchDeleteShopNotice(LoginAuthDto loginAuthDto, List<Long> ids) {
        UmsShopNotice shopNotice = new UmsShopNotice();
        Example example = new Example( UmsShopNotice.class );
        shopNotice.setUpdateInfo( loginAuthDto );
        shopNotice.setIsDelete( 0 );
        example.createCriteria().andIn( "id", ids );
        return umsShopNoticeMapper.updateByExampleSelective(shopNotice, example);
    }
}
