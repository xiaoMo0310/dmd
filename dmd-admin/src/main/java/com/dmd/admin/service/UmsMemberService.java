package com.dmd.admin.service;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-21
 */
public interface UmsMemberService{

    /**
     * 统计当日新注册的用户
     * @return
     */
    Long countDayRegisterUser();
}
