package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsMemberMapper;
import com.dmd.admin.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public Long countDayRegisterUser() {
        return umsMemberMapper.countDayRegisterUser();
    }
}
