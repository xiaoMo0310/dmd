package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsCoachRebateMapper;
import com.dmd.mall.model.domain.*;
import com.dmd.mall.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 教练返佣表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsCoachRebateServiceImpl extends BaseService<UmsCoachRebate> implements UmsCoachRebateService {

    @Autowired
    private UmsCoachRebateMapper umsCoachRebateMapper;
    @Autowired
    private OmsOrderService orderService;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private UmsCoachService umsCoachService;
    @Autowired
    private OmsFinanceSettingService financeSettingService;

    @Override
    public int addCoachRebateMessage(LoginAuthDto loginAuthDto, String orderSn) {
        OmsOrder order = orderService.getOmsOrderByOrderSn( orderSn );
        UmsMember umsMember = umsMemberService.getById( order.getMemberId() );
        int result = 0;
        if(StringUtils.isEmpty( umsMember.getInvitationCode() )){
            return result;
        }
        UmsCoach umsCoach = umsCoachService.selectCoachMessageByInvitationCode(umsMember.getInvitationCode());
        if(order.getSellerId() != umsCoach.getId()){
            //计算佣金保存信息
            OmsFinanceSetting financeSetting = financeSettingService.selectByKey( 1L );
            BigDecimal rebateAmount = order.getPayAmount().multiply( BigDecimal.valueOf( financeSetting.getCoachCommissionRatio()/100 ) );
            UmsCoachRebate umsCoachRebate = new UmsCoachRebate();
            umsCoachRebate.setCoachId( umsCoach.getId() );
            umsCoachRebate.setOrderSn( order.getOrderSn() );
            umsCoachRebate.setPayAmount( order.getPayAmount() );
            umsCoachRebate.setUserId( umsMember.getId() );
            umsCoachRebate.setRebateAmount( rebateAmount );
            umsCoachRebate.setRebateRatio( financeSetting.getCoachCommissionRatio() );
            umsCoachRebate.setUpdateInfo( loginAuthDto );
            umsCoachRebateMapper.insertSelective( umsCoachRebate );
        }
        //todo 将佣金钱添加到教练账户待做
        return result;
    }

    @Override
    public List<UmsCoachRebate> selectByCoachAndUserId(Long userId, Long coachId) {
        return umsCoachRebateMapper.selectByCoachAndUserId(userId, coachId);
    }

    @Override
    public BigDecimal countCoachRebateByCoachId(Long coachId) {
        return umsCoachRebateMapper.countCoachRebateByCoachId(coachId);
    }

}
