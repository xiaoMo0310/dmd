package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.model.domain.UmsUserFeedbackBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementMapper
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/211:05
 */
public interface UmsFeedbackManagementMapper {
    List<UmsProblemFeedbackBean> queryProblemFeedback();

    int saveOrUpdateStatus(UmsProblemFeedbackBean umsProblemFeedbackBean);

    int updateFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean);

    int addFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean);

    List<UmsUserFeedbackBean> queryUserFeedback(UmsUserFeedbackBean umsUserFeedbackBean);

    List<UmsProblemFeedbackBean> queryProblemFeedbackByName();

    int deleteUserFeedback(List<Long> ids);
}
