package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsNoticeMarkMapper;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.model.vo.UmsNoticeReadVo;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.mall.service.UmsNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户通知标记表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeMarkServiceImpl extends BaseService<UmsNoticeMark> implements UmsNoticeMarkService {

    @Autowired
    private UmsNoticeMarkMapper umsNoticeMarkMapper;
    @Autowired
    private UmsNoticeService umsNoticeService;

    @Override
    public List<UmsNoticeMark> selectByUserId(Long userId, String userType) {
        return umsNoticeMarkMapper.selectByUserId(userId, userType);
    }

    @Override
    public int updateIsRead(LoginAuthDto loginAuthDto, Long noticeId) {
        //查询消息
        UmsNotice umsNotice = umsNoticeService.selectByKey(noticeId);
        int result;
        if(umsNotice.getType() == 3){
            result = insertNoticeMarkMessage(noticeId, loginAuthDto.getUserId(), loginAuthDto.getUserType(), loginAuthDto);
        }else {
            result = umsNoticeMarkMapper.updateIsRead(loginAuthDto.getUserId(), loginAuthDto.getUserName(), noticeId, loginAuthDto.getUserType(), 1);
        }
        return result;
    }

    @Override
    public UmsNoticeMark selectNoticeMarkMessageByNoticeId(Long noticeId, Long userId, String userType) {
        return umsNoticeMarkMapper.selectNoticeMarkMessageByNoticeId(noticeId, userId, userType);
    }

    @Override
    public int insertNoticeMarkMessage(Long noticeId, Long userId, String userType, LoginAuthDto loginAuthDto) {
        UmsNoticeMark umsNoticeMark = new UmsNoticeMark();
        umsNoticeMark.setIsRead(0);
        umsNoticeMark.setNoticeId(noticeId);
        umsNoticeMark.setUserId(userId);
        umsNoticeMark.setUserType(userType);
        umsNoticeMark.setReadTime("0");
        umsNoticeMark.setUpdateInfo(loginAuthDto);
        return umsNoticeMarkMapper.insertSelective(umsNoticeMark);
    }

    @Override
    public List<UmsNoticeReadVo> findNoticeRead(LoginAuthDto loginAuthDto) {
        List<UmsNoticeReadVo> umsNoticeReadVos = new ArrayList<>();
        //点赞
        UmsNoticeReadVo noReadMessageA = findNoReadMessage(loginAuthDto, 1);
        //评论
        UmsNoticeReadVo noReadMessageB = findNoReadMessage(loginAuthDto, 2);
        //系统
        UmsNoticeReadVo noReadMessageC = findNoReadMessage(loginAuthDto, 3);
        umsNoticeReadVos.add(noReadMessageA);
        umsNoticeReadVos.add(noReadMessageB);
        umsNoticeReadVos.add(noReadMessageC);
        return umsNoticeReadVos;
    }

    public UmsNoticeReadVo findNoReadMessage(LoginAuthDto loginAuthDto, Integer messageType) {
        List<UmsNoticeVo> umsNoticeVos = umsNoticeService.findLoginUserMessage(loginAuthDto, messageType);
        long count = umsNoticeVos.stream().filter(umsNoticeVo -> umsNoticeVo.getIsRead() == 0).count();
        UmsNoticeReadVo umsNoticeReadVo = new UmsNoticeReadVo();
        if(count == 0){
            umsNoticeReadVo.setIsRead(0);
            umsNoticeReadVo.setCount(0L);
        }else {
            umsNoticeReadVo.setIsRead(1);
        }
        umsNoticeReadVo.setCount(count);
        umsNoticeReadVo.setMessageType(messageType);
        return umsNoticeReadVo;
    }
}
