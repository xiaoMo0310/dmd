package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.UmsNoticeMapper;
import com.dmd.admin.model.domain.UmsNotice;
import com.dmd.admin.model.dto.MessageDto;
import com.dmd.admin.model.dto.MessageListDto;
import com.dmd.admin.model.vo.NoticeListVo;
import com.dmd.admin.service.UmsNoticeMarkService;
import com.dmd.admin.service.UmsNoticeService;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户通告表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeServiceImpl extends BaseService<UmsNotice> implements UmsNoticeService {

    @Autowired
    private UmsNoticeMapper umsNoticeMapper;
    @Autowired
    private UmsNoticeMarkService noticeMarkService;

    @Override
    public void addMessage(LoginAuthDto loginAuthDto, Long id, Integer userType, MessageDto messageDto) {
        UmsNotice umsNotice = saveUmsNotice(loginAuthDto, userType, 1, messageDto);
        //添加用户通知标志信息
        noticeMarkService.insertNoticeMarkMessage(umsNotice.getId(), id, userType, loginAuthDto);
    }

    @Override
    public void batchAddMessage(LoginAuthDto loginAuthDto, List<Long> ids, Integer userType, MessageDto messageDto) {
        UmsNotice umsNotice;
        if(ids.size() == 1){
            umsNotice = saveUmsNotice(loginAuthDto, userType, 1, messageDto);
        }else {
            umsNotice = saveUmsNotice(loginAuthDto, userType, 2, messageDto);
        }
        for (Long id : ids) {
            noticeMarkService.insertNoticeMarkMessage(umsNotice.getId(), id, userType, loginAuthDto);
        }
    }

    @Override
    public void addAllMessage(LoginAuthDto loginAuthDto, Integer userType, MessageDto messageDto) {
        UmsNotice umsNotice = saveUmsNotice(loginAuthDto, userType, 3, messageDto);
    }

    @Override
    public PageInfo<NoticeListVo> findNoticeMessageByPage(MessageListDto messageListDto) {
        PageHelper.startPage(messageListDto.getPageNum(), messageListDto.getPageSize());
        List<NoticeListVo> noticeListVos = umsNoticeMapper.findNoticeMessage(messageListDto);
        return null;
    }

    public UmsNotice saveUmsNotice(LoginAuthDto loginAuthDto, Integer userType, Integer type, MessageDto messageDto) {
        UmsNotice umsNotice = new UmsNotice();
        BeanUtils.copyProperties(messageDto, umsNotice);
        umsNotice.setIsCancel(1);
        umsNotice.setIsDelete(1);
        umsNotice.setType(1);
        umsNotice.setMessageType(1);
        umsNotice.setUserType(userType);
        umsNotice.setUpdateInfo(loginAuthDto);
        umsNoticeMapper.insertSelective(umsNotice);
        return umsNotice;
    }
}
