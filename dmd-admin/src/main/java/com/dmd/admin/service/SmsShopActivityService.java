package com.dmd.admin.service;

import com.dmd.admin.model.domain.SmsShopActivity;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-10
 */
public interface SmsShopActivityService extends IService<SmsShopActivity> {

    /**
     * 添加或修改店铺活动
     * @param loginAuthDto
     * @param activityTemplate
     * @return
     */
    int createOrUpdateActivity(LoginAuthDto loginAuthDto, SmsShopActivity shopActivity);

    /**
     * 分页查询店铺活动
     * @param smsShopActivity
     * @return
     */
    PageInfo<SmsShopActivity> getActivityList(SmsShopActivity smsShopActivity);

    /**
     * 删除商品模板
     * @param ids
     * @return
     */
    int deleteActivity(List<Long> ids);

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus);
}
