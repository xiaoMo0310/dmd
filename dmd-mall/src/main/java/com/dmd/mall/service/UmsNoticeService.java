package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 用户通告表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
public interface UmsNoticeService extends IService<UmsNotice> {

    /**
     * 查询当前登录人的通知信息
     * @param loginAuthDto
     * @return
     */
    List<UmsNotice> findLoginUserMessage(LoginAuthDto loginAuthDto, Integer userType);
}
