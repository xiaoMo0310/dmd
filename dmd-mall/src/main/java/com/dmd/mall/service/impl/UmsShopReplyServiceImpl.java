package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsShopReplyMapper;
import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.mall.model.domain.UmsShopReply;
import com.dmd.mall.model.dto.UmsShopReplyDto;
import com.dmd.mall.service.UmsCoachShopService;
import com.dmd.mall.service.UmsShopReplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 教练店铺自动回复 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsShopReplyServiceImpl extends BaseService<UmsShopReply> implements UmsShopReplyService {

    @Autowired
    private UmsShopReplyMapper umsShopReplyMapper;
    @Autowired
    private UmsCoachShopService umsCoachShopService;

    @Override
    public PageInfo<UmsShopReply> findReplyMessageByPage(LoginAuthDto loginAuthDto, BaseQuery baseQuery) {
        if(loginAuthDto.getUserName().equals( "member" )){
            throw new UmsBizException( ErrorCodeEnum.GL99990403 );
        }
        PageHelper.startPage( baseQuery.getPageNum(), baseQuery.getPageSize(), baseQuery.getOrderBy());
        List<UmsShopReply> umsShopReplies = umsShopReplyMapper.selectReplyMessage(loginAuthDto.getUserId());
        return new PageInfo<>( umsShopReplies );
    }

    @Override
    public int addOrEditReplyMessage(LoginAuthDto loginAuthDto, UmsShopReplyDto umsShopReplyDto) {
        if(loginAuthDto.getUserName().equals( "member" )){
            throw new UmsBizException( ErrorCodeEnum.GL99990403 );
        }
        UmsShopReply umsShopReply = new UmsShopReply();
        BeanUtils.copyProperties( umsShopReplyDto, umsShopReply );
        int resultInt;
        umsShopReply.setUpdateInfo(loginAuthDto);
        if (umsShopReply.isNew()) {
            umsShopReply.setIsDelete( 1 );
            umsShopReply.setCoachId(loginAuthDto.getUserId());
            //查询店铺信息
            UmsCoachShop umsCoachShop = umsCoachShopService.findByCoachId(loginAuthDto.getUserId());
            umsShopReply.setShopId( umsCoachShop.getId() );
            resultInt = umsShopReplyMapper.insertSelective(umsShopReply);
        } else {
            resultInt = umsShopReplyMapper.updateByPrimaryKeySelective(umsShopReply);
        }
        return resultInt;
    }
}
