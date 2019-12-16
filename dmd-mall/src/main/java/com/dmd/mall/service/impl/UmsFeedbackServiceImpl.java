package com.dmd.mall.service.impl;

import com.dmd.mall.mapper.UmsFeedbackMapper;
import com.dmd.mall.model.domain.UmsProblemFeedbackBean;
import com.dmd.mall.model.domain.UmsUserFeedbackBean;
import com.dmd.mall.service.UmsFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackServiceImpl
 * @projectName dmd
 * @description:
 * @date 2019/12/210:11
 */
@Service
public class UmsFeedbackServiceImpl implements UmsFeedbackService{
    @Autowired
    private UmsFeedbackMapper umsFeedbackMapper;

    @Override
    public List<UmsProblemFeedbackBean> queryProblemFeedback() {
        return umsFeedbackMapper.queryProblemFeedback();
    }

    @Override
    public int addFeedback(UmsUserFeedbackBean umsUserFeedbackBean) {
        umsUserFeedbackBean.setCreateTime(new Date());
        return umsFeedbackMapper.addFeedback(umsUserFeedbackBean);
    }
}
