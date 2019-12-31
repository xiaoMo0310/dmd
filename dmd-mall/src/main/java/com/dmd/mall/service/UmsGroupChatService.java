package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsGroupChat;
import com.dmd.mall.model.vo.UmsGroupChatVo;

import java.util.List;

/**
 * <p>
 * 教练群聊创建  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-30
 */
public interface UmsGroupChatService extends IService<UmsGroupChat> {

    /**
     * 查询当前登录人需要创建的群聊或者群聊需要添加的人
     * @param loginAuthDto
     * @return
     */
    UmsGroupChatVo findCoachNeedGroupChart(LoginAuthDto loginAuthDto);

    /**
     * 添加需要创建群聊的信息
     * @param founderAccount
     * @param memberAccount
     * @param isFirst
     * @return
     */
    Integer createGroupChartMessage(String founderAccount, String memberAccount, Integer isFirst);

    /**
     * 修改群聊状态为已创建
     * @param loginAuthDto
     * @return
     */
    int updateGroupChartStatus(LoginAuthDto loginAuthDto, List<Long> ids);
}
