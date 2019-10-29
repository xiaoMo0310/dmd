package com.dmd.admin.service;

import com.dmd.admin.model.domain.UmsNotice;
import com.dmd.admin.model.dto.MessageDto;
import com.dmd.admin.model.dto.MessageListDto;
import com.dmd.admin.model.vo.NoticeListVo;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 用户通告表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-29
 */
public interface UmsNoticeService extends IService<UmsNotice> {

    /**
     * 保存通知信息
     * @param loginAuthDto
     * @param id
     * @param messageDto
     */
    void addMessage(LoginAuthDto loginAuthDto, Long id, Integer userType, MessageDto messageDto);

    /**
     * 批量保存通知信息
     * @param loginAuthDto
     * @param ids
     * @param userType
     * @param messageDto
     */
    void batchAddMessage(LoginAuthDto loginAuthDto, List<Long> ids, Integer userType, MessageDto messageDto);

    /**
     * 保存所有用户通知信息
     * @param loginAuthDto
     * @param userType
     * @param messageDto
     */
    void addAllMessage(LoginAuthDto loginAuthDto, Integer userType, MessageDto messageDto);

    /**
     * 分页查询通知消息
     * @param messageListDto
     * @return
     */
    PageInfo<NoticeListVo> findNoticeMessageByPage(MessageListDto messageListDto);
}
