package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.mall.model.vo.UmsNoticeReadVo;

import java.util.List;

/**
 * <p>
 * 用户通知标记表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
public interface UmsNoticeMarkService extends IService<UmsNoticeMark> {

    /**
     * 根据用户id查询用户标记信息
     * @param userId
     * @return
     */
    List<UmsNoticeMark> selectByUserId(Long userId, String userType);

    /**
     * 修改当前登录人的通知标记未已读
     * @param loginAuthDto
     * @param noticeId
     * @return
     */
    int updateIsRead(LoginAuthDto loginAuthDto, Long noticeId);

    /**
     * 根据消息id查询标记信息
     * @param noticeId
     * @param userId
     * @param userType
     * @return
     */
    UmsNoticeMark selectNoticeMarkMessageByNoticeId(Long noticeId, Long userId, String userType);

    /**
     * 保存订单标记消息
     * @param noticeId
     * @param loginAuthDto
     * @return
     */
    int insertNoticeMarkMessage(Long noticeId, Long userId, String userType, LoginAuthDto loginAuthDto);

    /**
     * 查询当前登录人信息是否有未读
     * @param loginAuthDto
     * @return
     */
    List<UmsNoticeReadVo> findNoticeRead(LoginAuthDto loginAuthDto);
}
