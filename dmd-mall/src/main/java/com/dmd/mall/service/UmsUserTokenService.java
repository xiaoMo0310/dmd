package com.dmd.mall.service;

import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.exception.HttpProcessException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/12 13:14
 * @Description token管理
 */
public interface UmsUserTokenService {

    /**
     * 刷新token
     * @param accessToken
     * @param refreshToken
     * @param request
     * @return
     * @throws HttpProcessException
     */
    JSONObject refreshToken(String accessToken, String refreshToken, String loginType, HttpServletRequest request) throws HttpProcessException;
}
