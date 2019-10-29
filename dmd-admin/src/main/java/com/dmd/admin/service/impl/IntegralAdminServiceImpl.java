package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.IntegralRuleMapper;
import com.dmd.admin.mapper.UmsIntegrationChangeHistoryMapper;
import com.dmd.admin.mapper.UmsMemberMapper;
import com.dmd.admin.model.domain.IntegralRuleBean;
import com.dmd.admin.model.domain.UmsIntegrationChangeHistory;
import com.dmd.admin.model.domain.UmsMember;
import com.dmd.admin.service.IntegralAdminService;
import com.dmd.base.result.CommonResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminServiceImpl
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2214:20
 */
@Service
public class IntegralAdminServiceImpl implements IntegralAdminService{

    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    @Autowired
    private UmsIntegrationChangeHistoryMapper umsIntegrationChangeHistoryMapper;

    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Override
    public List<IntegralRuleBean> queryIntegralRule() {
        return integralRuleMapper.queryIntegralRule();
    }

    @Override
    public int updateIntegralRule(IntegralRuleBean integralRuleBean) {
        integralRuleBean.setCreateTime(new Date());
        return integralRuleMapper.updateIntegralRule(integralRuleBean);
    }

    @Override
    public IntegralRuleBean findIntegralInfoById(Long id) {
        return integralRuleMapper.findIntegralInfoById(id);
    }

    @Override
    public List<UmsIntegrationChangeHistory> queryIntegralChangePage(Integer pageNum, Integer pageSize, UmsIntegrationChangeHistory umsIntegrationChangeHistory) {
        PageHelper.startPage(pageNum, pageSize);
        return umsIntegrationChangeHistoryMapper.queryIntegralChangePage(umsIntegrationChangeHistory);
    }

    @Override
    public int updateIntegration(UmsIntegrationChangeHistory umsIntegrationChangeHistory) {
        //获取到增加的数量
        Integer changeCount = umsIntegrationChangeHistory.getChangeCount();
        //获取到用户id
        Long memberId = umsIntegrationChangeHistory.getMemberId();
        //去改变用户积分数量
        umsMemberMapper.updateIntegrationForUser(changeCount,memberId);
        //收入
        umsIntegrationChangeHistory.setChangeType(0);
        //积分来源
        umsIntegrationChangeHistory.setSourceType(2);
        //创建时间
        umsIntegrationChangeHistory.setCreateTime(new Date());
        return umsIntegrationChangeHistoryMapper.updateIntegration(umsIntegrationChangeHistory);
    }

    @Override
    public int updateIntegrationReduce(UmsIntegrationChangeHistory umsIntegrationChangeHistory) {
        //获取到减少的数量
        Integer changeCount = umsIntegrationChangeHistory.getChangeCount();
        //获取到用户id
        Long memberId = umsIntegrationChangeHistory.getMemberId();
        //去改变用户积分数量
        int count = umsMemberMapper.updateIntegrationForUserReduce(changeCount,memberId);
        if (count > 0) {
            //支出
            umsIntegrationChangeHistory.setChangeType(1);
            //积分来源
            umsIntegrationChangeHistory.setSourceType(2);
            //创建时间
            umsIntegrationChangeHistory.setCreateTime(new Date());
            umsIntegrationChangeHistoryMapper.updateIntegration(umsIntegrationChangeHistory);
        }
        return count;
    }
}
