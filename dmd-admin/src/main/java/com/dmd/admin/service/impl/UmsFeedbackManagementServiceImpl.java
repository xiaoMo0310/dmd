package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsFeedbackManagementMapper;
import com.dmd.admin.model.domain.UmsProblemFeedbackBean;
import com.dmd.admin.model.domain.UmsUserFeedbackBean;
import com.dmd.admin.service.UmsFeedbackManagementService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsFeedbackManagementServiceImpl
 * @projectName dmd
 * @description:
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

    @Override
    public int saveOrUpdateStatus(UmsProblemFeedbackBean umsProblemFeedbackBean) {
        return umsFeedbackManagementMapper.saveOrUpdateStatus(umsProblemFeedbackBean);
    }

    @Override
    public int updateFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean) {
        return umsFeedbackManagementMapper.updateFeedback(umsProblemFeedbackBean);
    }

    @Override
    public int addFeedback(UmsProblemFeedbackBean umsProblemFeedbackBean) {
        return umsFeedbackManagementMapper.addFeedback(umsProblemFeedbackBean);
    }

    @Override
    public List<UmsUserFeedbackBean> queryUserFeedback(Integer pageNum, Integer pageSize, UmsUserFeedbackBean umsUserFeedbackBean) {
        PageHelper.startPage(pageNum, pageSize);
        return umsFeedbackManagementMapper.queryUserFeedback(umsUserFeedbackBean);
    }

    @Override
    public List<UmsProblemFeedbackBean> queryProblemFeedbackByName() {
        return umsFeedbackManagementMapper.queryProblemFeedbackByName();
    }

    @Override
    public int deleteUserFeedback(List<Long> ids) {
        return umsFeedbackManagementMapper.deleteUserFeedback(ids);
    }
}
