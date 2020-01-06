package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsGroupChatMapper;
import com.dmd.mall.model.domain.UmsGroupChat;
import com.dmd.mall.model.vo.UmsGroupChatVo;
import com.dmd.mall.service.UmsGroupChatService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 教练群聊创建  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsGroupChatServiceImpl extends BaseService<UmsGroupChat> implements UmsGroupChatService {

    @Autowired
    private UmsGroupChatMapper umsGroupChatMapper;

    @Override
    public UmsGroupChatVo findCoachNeedGroupChart(LoginAuthDto loginAuthDto) {
        UmsGroupChatVo umsGroupChatVo = new UmsGroupChatVo();
        List<UmsGroupChat> umsGroupChats = umsGroupChatMapper.selectCoachNeedGroupChart(loginAuthDto.getPhone());
        //获取是否存在首位注册的
        if(!CollectionUtils.isEmpty(umsGroupChats)){
            long num = umsGroupChats.stream().filter(umsGroupChat -> umsGroupChat.getIsFirst() == 1).count();
            if(num != 0){
                umsGroupChatVo.setIsNewCreate(2);
            }else {
                umsGroupChatVo.setIsNewCreate(1);
            }
            umsGroupChatVo.setGroupChats(umsGroupChats);
        }
        return umsGroupChatVo;
    }

    @Override
    public Integer createGroupChartMessage(String founderAccount, String memberAccount, Integer isFirst) {
        UmsGroupChat umsGroupChat = new UmsGroupChat();
        umsGroupChat.setFounderAccount(founderAccount);
        umsGroupChat.setMemberAccount(memberAccount);
        umsGroupChat.setIsFirst(isFirst);
        umsGroupChat.setStatus(0);
        Date date = new Date();
        umsGroupChat.setUpdateTime(date);
        umsGroupChat.setCreatedTime(date);
        return umsGroupChatMapper.insertSelective(umsGroupChat);
    }

    @Override
    public int updateGroupChartStatus(LoginAuthDto loginAuthDto, List<Long> ids) {
        Example example = new Example(UmsGroupChat.class);
        example.createCriteria().andIn("id", ids);
        UmsGroupChat umsGroupChat = new UmsGroupChat();
        umsGroupChat.setStatus(1);
        umsGroupChat.setUpdateInfo(loginAuthDto);
        return umsGroupChatMapper.updateByExampleSelective(umsGroupChat, example);
    }
}
