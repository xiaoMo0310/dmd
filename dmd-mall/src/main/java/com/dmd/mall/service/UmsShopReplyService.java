package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsShopReply;
import com.dmd.core.support.IService;
import com.dmd.mall.model.dto.UmsShopReplyDto;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 教练店铺自动回复 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
public interface UmsShopReplyService extends IService<UmsShopReply> {

    /**
     * 分页查询所有的回复消息
     * @param loginAuthDto
     * @param baseQuery
     * @return
     */
    PageInfo<UmsShopReply> findReplyMessageByPage(LoginAuthDto loginAuthDto, BaseQuery baseQuery);

    /**
     * 添加或者编辑店铺回复消息
     * @param loginAuthDto
     * @param umsShopReplyDto
     * @return
     */
    int addOrEditReplyMessage(LoginAuthDto loginAuthDto, UmsShopReplyDto umsShopReplyDto);
}
