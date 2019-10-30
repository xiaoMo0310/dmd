package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsNoticeMark;
import com.dmd.admin.model.vo.NoticeMarkVo;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;

import java.util.List;

/**
 * <p>
 * 用户通知标记表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
public interface UmsNoticeMarkService extends IService<UmsNoticeMark> {

    /**
     * 添加用户通知标志信息
     * @param noticeId
     * @param id
     * @param userType
     * @param loginAuthDto
     */
    void insertNoticeMarkMessage(Long noticeId, Long id, Integer userType, LoginAuthDto loginAuthDto);

    /**
     * 根据通知id查询所有的通知标记信息
     * @param noticeId
     * @param userType
     * @return
     */
    List<NoticeMarkVo> selectByNoticeId(Long noticeId, Integer userType);
}
