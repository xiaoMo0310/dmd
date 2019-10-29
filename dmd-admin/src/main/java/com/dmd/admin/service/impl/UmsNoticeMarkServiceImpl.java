package com.dmd.admin.service.impl;

import com.dmd.admin.model.domain.UmsNoticeMark;
import com.dmd.admin.mapper.UmsNoticeMarkMapper;
import com.dmd.admin.service.UmsNoticeMarkService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户通知标记表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeMarkServiceImpl extends BaseService<UmsNoticeMark> implements UmsNoticeMarkService {

    @Autowired
    private UmsNoticeMarkMapper umsNoticeMarkMapper;

    @Override
    public void insertNoticeMarkMessage(Long noticeId, Long id, Integer userType, LoginAuthDto loginAuthDto) {
        UmsNoticeMark umsNoticeMark = new UmsNoticeMark();
        umsNoticeMark.setIsRead(0);
        umsNoticeMark.setNoticeId(noticeId);
        umsNoticeMark.setUserId(id);
        umsNoticeMark.setUserType(userType);
        umsNoticeMark.setUpdateInfo(loginAuthDto);
        umsNoticeMarkMapper.insertSelective(umsNoticeMark);
    }
}
