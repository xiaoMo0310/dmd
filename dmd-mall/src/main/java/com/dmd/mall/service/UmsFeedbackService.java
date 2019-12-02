package com.dmd.mall.service;

import com.dmd.mall.model.domain.UmsProblemFeedbackBean;
import com.dmd.mall.model.domain.UmsUserFeedbackBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackService
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/210:11
 */
public interface UmsFeedbackService {
    List<UmsProblemFeedbackBean> queryProblemFeedback();

    int addFeedback(UmsUserFeedbackBean umsUserFeedbackBean);
}
