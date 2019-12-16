package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsProblemFeedbackBean;
import com.dmd.mall.model.domain.UmsUserFeedbackBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackMapper
 * @projectName dmd
 * @description:
 * @date 2019/12/210:12
 */
public interface UmsFeedbackMapper {
    List<UmsProblemFeedbackBean> queryProblemFeedback();

    int addFeedback(UmsUserFeedbackBean umsUserFeedbackBean);
}
