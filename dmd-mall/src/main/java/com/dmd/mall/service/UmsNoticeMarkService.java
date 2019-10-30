package com.dmd.mall.service;

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
    List<UmsNoticeMark> selectByUserId(Long userId, Integer userType);
}
