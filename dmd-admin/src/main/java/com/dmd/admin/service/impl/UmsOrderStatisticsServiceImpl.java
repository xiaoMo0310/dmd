package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsOrderStatisticsMapper;
import com.dmd.admin.model.vo.UmsOrderStatisticsVo;
import com.dmd.admin.service.UmsOrderStatisticsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: UmsOrderStatisticsServiceImpl
 * @projectName dmd
 * @description:
 * @date 2019/11/2515:52
 */
@Service
public class UmsOrderStatisticsServiceImpl implements UmsOrderStatisticsService{

    @Autowired
    private UmsOrderStatisticsMapper umsOrderStatisticsMapper;

    @Override
    public List<UmsOrderStatisticsVo> queryOrderStatisticsPage(Integer pageNum, Integer pageSize, UmsOrderStatisticsVo umsOrderStatisticsVo) {
        PageHelper.startPage(pageNum, pageSize);
        List<UmsOrderStatisticsVo> umsOrderStatisticsVos = umsOrderStatisticsMapper.queryOrderStatisticsPage(umsOrderStatisticsVo);

        for (int i = 0; i < umsOrderStatisticsVos.size(); i++) {
            if (umsOrderStatisticsVos.get(i).getUserType().equals("member")){
                umsOrderStatisticsVos.get(i).setUserName(umsOrderStatisticsVos.get(i).getUserName());
                umsOrderStatisticsVos.get(i).setPhone(umsOrderStatisticsVos.get(i).getPhone());
                umsOrderStatisticsVos.get(i).setUserCreateTime(umsOrderStatisticsVos.get(i).getUserCreateTime());
                umsOrderStatisticsVos.get(i).setInvitationCode(umsOrderStatisticsVos.get(i).getInvitationCode());
                umsOrderStatisticsVos.get(i).setCoachName("");
                umsOrderStatisticsVos.get(i).setPhoneCoach("");
                umsOrderStatisticsVos.get(i).setCoachCreateTime(null);
                umsOrderStatisticsVos.get(i).setInvitationCodeCoach("");
            }else if(umsOrderStatisticsVos.get(i).getUserType().equals("coach")){
                umsOrderStatisticsVos.get(i).setCoachName(umsOrderStatisticsVos.get(i).getCoachName());
                umsOrderStatisticsVos.get(i).setPhoneCoach(umsOrderStatisticsVos.get(i).getPhoneCoach());
                umsOrderStatisticsVos.get(i).setCoachCreateTime(umsOrderStatisticsVos.get(i).getCoachCreateTime());
                umsOrderStatisticsVos.get(i).setInvitationCodeCoach(umsOrderStatisticsVos.get(i).getInvitationCodeCoach());
                umsOrderStatisticsVos.get(i).setUserName("");
                umsOrderStatisticsVos.get(i).setPhone("");
                umsOrderStatisticsVos.get(i).setUserCreateTime(null);
                umsOrderStatisticsVos.get(i).setInvitationCode("");
            }
        }
        //教练订单
        return umsOrderStatisticsVos;
    }

    @Override
    public List<UmsOrderStatisticsVo> queryOrderStatistics(UmsOrderStatisticsVo umsOrderStatisticsVo) {
        //用户订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVos = umsOrderStatisticsMapper.queryOrderStatisticsPage(umsOrderStatisticsVo);
        //教练订单
        List<UmsOrderStatisticsVo> umsOrderStatisticsVosCoach = umsOrderStatisticsMapper.queryOrderStatisticsPageCoach(umsOrderStatisticsVo);
        //数据合并
        umsOrderStatisticsVos.addAll(umsOrderStatisticsVosCoach);
        umsOrderStatisticsVos.sort((o1, o2) -> o2.getOrderCreatedTime().compareTo(o1.getOrderCreatedTime()));
        return umsOrderStatisticsVos;
    }
}
