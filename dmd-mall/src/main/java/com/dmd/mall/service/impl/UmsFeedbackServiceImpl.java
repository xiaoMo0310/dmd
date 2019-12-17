package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.UmsFeedbackMapper;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.UmsProblemFeedbackBean;
import com.dmd.mall.model.domain.UmsUserFeedbackBean;
import com.dmd.mall.service.UmsFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        DynamicBean dynamicBean = new DynamicBean();
        List objects = new ArrayList<>();
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //登陆角色
        String userTypes = loginAuthDto.getUserType();
        if(userTypes.equals("member")){
            umsUserFeedbackBean.setCreateTime(new Date());
            int i = umsFeedbackMapper.addFeedback(umsUserFeedbackBean);
        }else if(userTypes.equals("coach")){
            umsUserFeedbackBean.setCreateTime(new Date());
            int i = umsFeedbackMapper.addFeedbackCoach(umsUserFeedbackBean);

        }
        return 1;
    }
}
