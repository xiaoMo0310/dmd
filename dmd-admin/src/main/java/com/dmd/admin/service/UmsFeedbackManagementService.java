package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsProblemFeedbackBean;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementService
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/211:04
 */
public interface UmsFeedbackManagementService {
    List<UmsProblemFeedbackBean> queryProblemFeedback(Integer pageNum, Integer pageSize);
}
