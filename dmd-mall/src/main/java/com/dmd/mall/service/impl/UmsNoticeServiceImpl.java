package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.mapper.UmsNoticeMapper;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.mall.service.UmsNoticeService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户通告表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeServiceImpl extends BaseService<UmsNotice> implements UmsNoticeService {

    @Autowired
    private UmsNoticeMapper umsNoticeMapper;
    @Autowired
    UmsNoticeMarkService noticeMarkService;

    @Override
    public List<UmsNotice> findLoginUserMessage(LoginAuthDto loginAuthDto, Integer userType) {
        List<UmsNoticeMark> umsNoticeMarks = noticeMarkService.selectByUserId(loginAuthDto.getUserId(), userType);
        List<UmsNotice> umsNotices = umsNoticeMarks.stream().map(umsNoticeMark -> {
            //查询单个用户或多个用户的通知信息
            UmsNotice umsNotice = umsNoticeMapper.selectById(umsNoticeMark.getNoticeId());

            return umsNotice;
        }).collect(Collectors.toList());
        //查询全部的通知信息
        List<UmsNotice> umsNoticeList = umsNoticeMapper.selectByType(3);
        umsNotices.addAll(umsNoticeList);
        return umsNotices;
    }
}
