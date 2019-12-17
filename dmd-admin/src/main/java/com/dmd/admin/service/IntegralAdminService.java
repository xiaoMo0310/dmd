package com.dmd.admin.service;

import com.dmd.admin.model.domain.IntegralGiftsBean;
import com.dmd.admin.model.domain.IntegralGiftsSpeBean;
import com.dmd.admin.model.domain.IntegralRuleBean;
import com.dmd.admin.model.domain.UmsIntegrationChangeHistory;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminService
 * @projectName dmd-masters
 * @description:
 * @date 2019/10/2214:20
 */
public interface IntegralAdminService {
    List<IntegralRuleBean> queryIntegralRule();

    int updateIntegralRule(IntegralRuleBean integralRuleBean);

    IntegralRuleBean findIntegralInfoById(Long id);

    List<UmsIntegrationChangeHistory> queryIntegralChangePage(Integer pageNum, Integer pageSize, UmsIntegrationChangeHistory umsIntegrationChangeHistory);

    int updateIntegration(UmsIntegrationChangeHistory umsIntegrationChangeHistory);

    int updateIntegrationReduce(UmsIntegrationChangeHistory umsIntegrationChangeHistory);

    Integer queryMemberNum(Long memberId);

    List<IntegralGiftsBean> queryIntegralGifts(Integer pageNum, Integer pageSize, IntegralGiftsBean integralGiftsBean);

    int updateIntegralGiftsById(IntegralGiftsBean integralGiftsBean);

    IntegralGiftsBean findIntegralGiftsInfoById(Long id);

    int addIntegralGifts(IntegralGiftsBean integralGiftsBean);

    int deleteIntegralGiftsById(List<Long> ids);

    int addIntegralGiftsSpe(List<IntegralGiftsSpeBean> list);

    List<IntegralGiftsSpeBean> queryIntegralGiftsSpeById(Long id);

    int updateIntegralGiftsPass(List<Long> ids);

    int updateIntegralGiftsNoPass(List<Long> ids);

    int addGiftsSpe(IntegralGiftsSpeBean integralGiftsSpeBean);

    int updateIntegralGiftsSpe(IntegralGiftsSpeBean integralGiftsSpeBean);

    IntegralGiftsSpeBean findIntegralGiftsSpeInfoById(Long id);

    int deleteIntegralGiftsSpeById(List<Long> ids);
}
