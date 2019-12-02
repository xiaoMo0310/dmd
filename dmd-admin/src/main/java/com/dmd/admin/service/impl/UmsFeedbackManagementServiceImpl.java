package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsFeedbackManagementMapper;
import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.service.UmsFeedbackManagementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementServiceImpl
 * @projectName dmd
 * @description: TODO
 * @date 2019/12/211:05
 */
@Service
public class UmsFeedbackManagementServiceImpl implements UmsFeedbackManagementService{

    @Autowired
    private UmsFeedbackManagementMapper umsFeedbackManagementMapper;

    @Override
    public List<UmsProblemFeedbackBean> queryProblemFeedback(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return umsFeedbackManagementMapper.queryProblemFeedback();
    }
}
