package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.model.domain.UmsUserFeedbackBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementService
 * @projectName dmd
 * @description:
 * @date 2019/12/211:04
 */
public interface UmsFeedbackManagementService {
    List<UmsProblemFeedbackBean> queryProblemFeedback(Integer pageNum, Integer pageSize);

    int saveOrUpdateStatus(UmsProblemFeedbackBean umsProblemFeedbackBean);

    int updateFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean);

    int addFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean);

    List<UmsUserFeedbackBean> queryUserFeedback(Integer pageNum, Integer pageSize, UmsUserFeedbackBean umsUserFeedbackBean);

    List<UmsProblemFeedbackBean> queryProblemFeedbackByName();

    int deleteUserFeedback(List<Long> ids);
}
