package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.mapper.UmsNoticeMapper;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.model.dto.MessageDto;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.mall.service.UmsNoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户通告表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsNoticeServiceImpl extends BaseService<UmsNotice> implements UmsNoticeService {

    @Autowired
    private UmsNoticeMapper umsNoticeMapper;
    @Autowired
    UmsNoticeMarkService noticeMarkService;
    @Autowired
    private UmsMemberService umsMemberService;

    @Override
    public List<UmsNoticeVo> findLoginUserMessage(LoginAuthDto loginAuthDto, Integer messageType) {
        List<UmsNoticeMark> umsNoticeMarks = noticeMarkService.selectByUserId(loginAuthDto.getUserId(), loginAuthDto.getUserType());
        if(CollectionUtils.isEmpty(umsNoticeMarks)){
            //查询全部通知消息是否存在
            List<UmsNoticeVo> umsNoticeVos = getAllUmsNoticeVos(loginAuthDto, messageType);
            /*if(CollectionUtils.isEmpty(umsNoticeVos)){
                throw new UmsBizException("没有当前用户的通知");
            }*/
            return umsNoticeVos;
        }
        List<UmsNoticeVo> umsNotices = umsNoticeMarks.stream().map(umsNoticeMark -> {
            //查询单个用户或多个用户的通知信息
            UmsNotice umsNotice = getUmsNoticeById(umsNoticeMark.getNoticeId(), messageType);
            UmsNoticeVo umsNoticeVo = null;
            if(umsNotice != null){
                umsNoticeVo = new UmsNoticeVo();
                BeanUtils.copyProperties(umsNoticeMark, umsNoticeVo);
                BeanUtils.copyProperties(umsNotice, umsNoticeVo);
                if(umsNotice.getMessageType() == 2 || umsNotice.getMessageType() == 3){
                    //查询用户信息
                    UmsMember umsMember = umsMemberService.getById(umsNotice.getSourceUserId());
                    if(umsMember != null){
                        umsNoticeVo.setUserId(umsMember.getId());
                        umsNoticeVo.setUserName(umsMember.getUsername());
                        umsNoticeVo.setIcon(umsMember.getIcon());
                    }
                }
            }
            return umsNoticeVo;
        }).filter(umsNoticeVo -> umsNoticeVo != null).collect(Collectors.toList());
        //查询全部的通知信息
        List<UmsNoticeVo> umsNoticeVos = getAllUmsNoticeVos(loginAuthDto, messageType);
        umsNotices.addAll(umsNoticeVos);
        //排序
        Collections.sort(umsNotices, Comparator.comparing(UmsNoticeVo::getCreatedTime));
        return umsNotices;
    }

    @Override
    public UmsNotice getUmsNoticeById(Long noticeId, Integer messageType) {
        return umsNoticeMapper.selectById(noticeId, messageType);
    }

    @Override
    public List<UmsNoticeVo> getAllUmsNoticeVos(LoginAuthDto loginAuthDto, Integer messageType) {
        List<UmsNotice> umsNoticeList = umsNoticeMapper.selectByType(3, messageType);
        return umsNoticeList.stream().map(umsNotice -> {
            UmsNoticeVo umsNoticeVo = new UmsNoticeVo();
            BeanUtils.copyProperties(umsNotice, umsNoticeVo);
            //查询用户消息是否阅读
            UmsNoticeMark umsNoticeMark = noticeMarkService.selectNoticeMarkMessageByNoticeId(umsNotice.getId(), loginAuthDto.getUserId(), loginAuthDto.getUserType());
            if(umsNoticeMark == null){
                umsNoticeVo.setIsRead(0);
            }else {
                umsNoticeVo.setIsRead(umsNoticeMark.getIsRead());
            }
            return umsNoticeVo;
        }).collect(Collectors.toList());
    }

    @Override
    public PageInfo<UmsNoticeVo> findLoginUserMessageByPage(LoginAuthDto loginAuthDto, Integer messageType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UmsNoticeVo> loginUserMessage = this.findLoginUserMessage(loginAuthDto, messageType);
        return new PageInfo<>(loginUserMessage);
    }

    @Override
    public void saveNoticeMessage(LoginAuthDto loginAuthDto, Long userId, String userType, MessageDto messageDto) {
        UmsNotice umsNotice = saveUmsNotice(loginAuthDto, userType, 1, messageDto);
        //添加用户通知标志信息
        noticeMarkService.insertNoticeMarkMessage(umsNotice.getId(), userId, userType, loginAuthDto);
    }

    public UmsNotice saveUmsNotice(LoginAuthDto loginAuthDto, String userType, Integer type, MessageDto messageDto) {
        UmsNotice umsNotice = new UmsNotice();
        BeanUtils.copyProperties(messageDto, umsNotice);
        umsNotice.setIsCancel(1);
        umsNotice.setIsDelete(1);
        umsNotice.setSourceUserId(loginAuthDto.getUserId());
        umsNotice.setType(type);
        umsNotice.setMessageType(1);
        umsNotice.setUserType(userType);
        umsNotice.setUpdateInfo(loginAuthDto);
        umsNoticeMapper.insertSelective(umsNotice);
        return umsNotice;
    }
}
