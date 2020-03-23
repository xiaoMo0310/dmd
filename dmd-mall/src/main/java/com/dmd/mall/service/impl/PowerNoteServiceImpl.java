package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.mapper.PowerNoteMapper;
import com.dmd.mall.model.domain.PowerNotesBean;
import com.dmd.mall.service.PowerNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerNoteServiceImpl implements PowerNoteService {
    @Autowired
    private PowerNoteMapper powerNoteMapper;

    @Override
    public PowerNotesBean selectPowerNotesById(Long id) {
        return powerNoteMapper.selectPowerNotesById(id);
    }

    @Override
    public int updatePowerNotesById(PowerNotesBean powerNotesBean) {
        return powerNoteMapper.updatePowerNotesById(powerNotesBean);
    }

    @Override
    public int addPowerNote(PowerNotesBean powerNotesBean) {
        //登陆信息
        LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
        //当前登录人
        Long userId = loginAuthDto.getUserId();
        powerNotesBean.setUserId(userId);
        return powerNoteMapper.addPowerNote(powerNotesBean);
    }
}
