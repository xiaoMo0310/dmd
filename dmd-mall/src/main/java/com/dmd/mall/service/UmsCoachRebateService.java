package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsCoachRebate;
import com.dmd.core.support.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 教练返佣表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
public interface UmsCoachRebateService extends IService<UmsCoachRebate> {

    /**
     * 添加教练的提成数据
     *
     * @param loginAuthDto
     * @param orderSn
     * @return
     */
    int addCoachRebateMessage(LoginAuthDto loginAuthDto, String orderSn);

    /**
     * 根据用户id和教练id查询教练佣金信息
     * @param userId
     * @param id
     * @return
     */
    List<UmsCoachRebate> selectByCoachAndUserId(Long userId, Long coachId);

    /**
     * 统计教练所有的佣金
     * @param coachId
     * @return
     */
    BigDecimal countCoachRebateByCoachId(Long coachId);
}
