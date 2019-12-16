package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsMemberLoginLog;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员登录记录 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-13
 */
public interface UmsMemberLoginLogService extends IService<UmsMemberLoginLog> {

    void saveLoginUserLog(String accessToken, String refreshToken, LoginAuthDto loginAuthDto, HttpServletRequest request);

}
