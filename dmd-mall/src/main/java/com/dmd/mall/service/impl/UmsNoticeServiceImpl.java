package com.dmd.mall.service.impl;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsNoticeMapper;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.mall.service.UmsNoticeService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    @Override
    public Map<Integer, List<UmsNoticeVo>> findLoginUserMessage(LoginAuthDto loginAuthDto) {
        List<UmsNoticeMark> umsNoticeMarks = noticeMarkService.selectByUserId(loginAuthDto.getUserId(), loginAuthDto.getUserType());
        if(CollectionUtils.isEmpty(umsNoticeMarks)){
            throw new UmsBizException("没有当前用户的通知");
        }
        List<UmsNoticeVo> umsNotices = umsNoticeMarks.stream().map(umsNoticeMark -> {
            //查询单个用户或多个用户的通知信息
            UmsNotice umsNotice = umsNoticeMapper.selectById(umsNoticeMark.getNoticeId());
            UmsNoticeVo umsNoticeVo = null;
            if(umsNotice != null){
                umsNoticeVo = new UmsNoticeVo();
                BeanUtils.copyProperties(umsNoticeMark, umsNoticeVo);
                BeanUtils.copyProperties(umsNotice, umsNoticeVo);
            }
            return umsNoticeVo;
        }).filter(umsNoticeVo -> umsNoticeVo != null).collect(Collectors.toList());
        //查询全部的通知信息
        List<UmsNotice> umsNoticeList = umsNoticeMapper.selectByType(3);
        List<UmsNoticeVo> umsNoticeVos = umsNoticeList.stream().map(umsNotice -> {
            UmsNoticeVo umsNoticeVo = new UmsNoticeVo();
            BeanUtils.copyProperties(umsNotice, umsNoticeVo);
            return umsNoticeVo;
        }).collect(Collectors.toList());
        umsNotices.addAll(umsNoticeVos);
        if(CollectionUtils.isEmpty(umsNotices)){
            throw new UmsBizException("没有当前用户的通知");
        }
        //排序
        Collections.sort(umsNotices, Comparator.comparing(UmsNoticeVo::getCreatedTime));
        //分组
        Map<Integer, List<UmsNoticeVo>> map = umsNotices.stream().collect(Collectors.groupingBy(UmsNoticeVo::getMessageType));
        return map;
    }
}
