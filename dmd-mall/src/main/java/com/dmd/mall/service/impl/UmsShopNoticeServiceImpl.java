package com.dmd.mall.service.impl;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsShopNoticeMapper;
import com.dmd.mall.model.domain.UmsShopNotice;
import com.dmd.mall.service.UmsShopNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public PageInfo<UmsShopNotice> selectShopNoticeByPage(LoginAuthDto loginAuthDto, BaseQuery baseQuery) {
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy() );
        List<UmsShopNotice> umsShopNotices = umsShopNoticeMapper.selectAll();
        return new PageInfo<>( umsShopNotices );
    }
}
