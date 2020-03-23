package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.SmsActivityApply;
import com.dmd.core.support.IService;
import com.dmd.mall.model.dto.SmsActivityApplyDto;

/**
 * <p>
 * 活动报名表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
public interface SmsActivityApplyService extends IService<SmsActivityApply> {

    /**
     * 添加活动报名信息
     *
     * @param loginAuthDto
     * @param activityApplyDto
     * @return
     */
    int addActivityApplyMessage(LoginAuthDto loginAuthDto, SmsActivityApplyDto activityApplyDto);
}
