package com.dmd.mall.service;

import com.dmd.mall.model.domain.CoachApplyBean;

/**
 * @author ChenYanbing
 * @title: CoachApplyService
 * @projectName dmd-master
 * @description: 教练申请
 * @date 2019/9/1915:08
 */
public interface CoachApplyService {
    /**
     * 教练申请新增
     * @param coachApplyBean
     * @return
     */
    int addCoachApply(CoachApplyBean coachApplyBean);
}
