package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.model.dto.MessageDto;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 用户通告表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
public interface UmsNoticeService extends IService<UmsNotice> {

    /**
     * 查询当前登录人的通知信息
     * @param loginAuthDto
     * @return
     */
    List<UmsNoticeVo> findLoginUserMessage(LoginAuthDto loginAuthDto, Integer messageType);

    /**
     * 根据id和消息类型查询
     * @param noticeId
     * @param messageType
     * @return
     */
    UmsNotice getUmsNoticeById(Long noticeId, Integer messageType);

    /**
     * 查询全部通知消息
     * @param loginAuthDto
     * @param messageType
     * @return
     */
    List<UmsNoticeVo> getAllUmsNoticeVos(LoginAuthDto loginAuthDto, Integer messageType);

    /**
     * 保存通知消息
     * @param loginAuthDto
     * @param userId
     * @param userType
     * @param messageDto
     */
    void saveNoticeMessage(LoginAuthDto loginAuthDto, Long userId, String userType, MessageDto messageDto);

    PageInfo<UmsNoticeVo> findLoginUserMessageByPage(LoginAuthDto loginAuthDto, Integer messageType, Integer pageNum, Integer pageSize);
}
