package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.vo.UmsCoachVo;

/**
 * <p>
 * 教练表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
public interface UmsCoachService extends IService<UmsCoach> {

    /**
     * 根据id查询教练的信息
     * @param id
     * @return
     */
    UmsCoachVo selectCoachMessage(Long id);

    UmsCoach selectByLoginAuthDto(LoginAuthDto loginAuthDto);
}
