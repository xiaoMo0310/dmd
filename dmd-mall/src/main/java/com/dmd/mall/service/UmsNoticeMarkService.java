package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsNoticeMark;
import com.dmd.core.support.IService;

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
     * @param noticeIds
     * @return
     */
    int updateIsRead(LoginAuthDto loginAuthDto, List<Long> noticeIds);
}
