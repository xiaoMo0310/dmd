package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsProblemFeedbackBean;

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
}
