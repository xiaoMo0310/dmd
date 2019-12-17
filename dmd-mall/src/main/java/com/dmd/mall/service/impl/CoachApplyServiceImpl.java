package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.CoachApplyMapper;
import com.dmd.mall.model.domain.CoachApplyBean;
import com.dmd.mall.service.CoachApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ChenYanbing
 * @title: CoachApplyServiceImpl
 * @projectName dmd-master
 * @description: 教练申请
 * @date 2019/9/1915:08
 */
@Service
public class CoachApplyServiceImpl implements CoachApplyService{

    @Autowired
    private CoachApplyMapper coachApplyMapper;

    /**
     * 教练申请新增
     * @param coachApplyBean1
     * @return
     */
    @Override
    public int addCoachApply(CoachApplyBean coachApplyBean) {
        //申请时间为当前时间
        coachApplyBean.setCreateTime(new Date());
        //申请提交状态为审核中
        coachApplyBean.setApplyStatus(0);
        return coachApplyMapper.addCoachApply(coachApplyBean);
    }
}
