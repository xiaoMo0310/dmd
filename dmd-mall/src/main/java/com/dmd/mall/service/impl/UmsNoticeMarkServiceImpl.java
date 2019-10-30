package com.dmd.mall.service.impl;

import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.mapper.UmsNoticeMarkMapper;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.core.support.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户通知标记表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeMarkServiceImpl extends BaseService<UmsNoticeMark> implements UmsNoticeMarkService {

    @Autowired
    private UmsNoticeMarkMapper umsNoticeMarkMapper;

    @Override
    public List<UmsNoticeMark> selectByUserId(Long userId, Integer userType) {
        return umsNoticeMarkMapper.selectByUserId(userId, userType);
    }
}
