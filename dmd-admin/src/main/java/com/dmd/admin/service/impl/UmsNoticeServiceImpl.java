package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.mapper.UmsNoticeMapper;
import com.dmd.admin.model.domain.UmsNotice;
import com.dmd.admin.model.dto.MessageDto;
import com.dmd.admin.model.dto.MessageListDto;
import com.dmd.admin.model.vo.NoticeListVo;
import com.dmd.admin.model.vo.NoticeMarkVo;
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
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

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
    public void addMessage(LoginAuthDto loginAuthDto, Long id, String userType, MessageDto messageDto) {
        UmsNotice umsNotice = saveUmsNotice(loginAuthDto, userType, 1, messageDto);
        //添加用户通知标志信息
        noticeMarkService.insertNoticeMarkMessage(umsNotice.getId(), id, userType, loginAuthDto);
    }

    @Override
    public void batchAddMessage(LoginAuthDto loginAuthDto, List<Long> ids, String userType, MessageDto messageDto) {
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
    public void addAllMessage(LoginAuthDto loginAuthDto, String userType, MessageDto messageDto) {
        UmsNotice umsNotice = saveUmsNotice(loginAuthDto, userType, 3, messageDto);
    }

    @Override
    public JSONObject findNoticeMessageByPage(MessageListDto messageListDto) {
        PageHelper.startPage(messageListDto.getPageNum(), messageListDto.getPageSize());
        //查询所有的通知信息
        List<UmsNotice> umsNotices = umsNoticeMapper.selectByMessage(messageListDto);
        PageInfo<UmsNotice> noticePageInfo = new PageInfo<>(umsNotices);
        long total = noticePageInfo.getTotal();
        List<UmsNotice> noticePageInfoList = noticePageInfo.getList();
        List<NoticeListVo> noticeListVos = noticePageInfoList.stream().map(notice -> {
            NoticeListVo noticeListVo = new NoticeListVo();
            noticeListVo.setUmsNotice(notice);
            //查询通知标记信息
            if (notice.getType() != 3) {
                //todo 查询普通用户的信息, 教练待做
                List<NoticeMarkVo> umsNoticeMarks = null;
                if (notice.getUserType().equals("member")) {
                    umsNoticeMarks = noticeMarkService.selectByNoticeId(notice.getId(), notice.getUserType());
                }
                noticeListVo.setNoticeMarkVos(umsNoticeMarks);
            }
            return noticeListVo;
        }).collect(Collectors.toList());
        JSONObject object = new JSONObject();
        object.put("total", total);
        object.put("list", noticeListVos);
        return object;
    }

    @Override
    public int deleteNotice(List<Long> ids) {
        Example example =new Example(UmsNotice.class);
        example.createCriteria().andIn("id", ids);
        UmsNotice umsNotice = new UmsNotice();
        umsNotice.setIsDelete(0);
        return umsNoticeMapper.updateByExampleSelective(umsNotice, example);
    }

    @Override
    public int updateIsCancel(LoginAuthDto loginAuthDto, Long id, Integer status) {
        UmsNotice umsNotice = new UmsNotice();
        umsNotice.setId(id);
        umsNotice.setIsCancel(status);
        umsNotice.setUpdateInfo(loginAuthDto);
        return umsNoticeMapper.updateByPrimaryKeySelective(umsNotice);
    }

    public UmsNotice saveUmsNotice(LoginAuthDto loginAuthDto, String userType, Integer type, MessageDto messageDto) {
        UmsNotice umsNotice = new UmsNotice();
        BeanUtils.copyProperties(messageDto, umsNotice);
        umsNotice.setIsCancel(1);
        umsNotice.setIsDelete(1);
        umsNotice.setType(type);
        umsNotice.setMessageType(1);
        umsNotice.setUserType(userType);
        umsNotice.setUpdateInfo(loginAuthDto);
        umsNoticeMapper.insertSelective(umsNotice);
        return umsNotice;
    }
}
