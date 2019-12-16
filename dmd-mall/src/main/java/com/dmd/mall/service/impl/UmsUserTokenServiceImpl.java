package com.dmd.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.HttpHeader;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.service.UmsMemberLoginLogService;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.service.UmsUserTokenService;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/12 13:15
 * @Description token管理实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsUserTokenServiceImpl implements UmsUserTokenService {

    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private UmsMemberLoginLogService memberLoginLogService;
    @Value("${dmd.auth.refresh-token-url}")
    private String refreshTokenUrl;

    @Override
    public JSONObject refreshToken(String accessToken, String refreshToken, String loginType, HttpServletRequest request) throws HttpProcessException {
        Map<String, Object> map = new HashMap<>(2);
        map.put("grant_type", "refresh_token");
        map.put("refresh_token", refreshToken);
        map.put("loginType", loginType);
        map.put("client_id", "dmd");
        map.put("client_secret", "123456");
        //插件式配置请求参数（网址、请求参数、编码、client）
        Header[] headers = HttpHeader.custom().contentType(HttpHeader.Headers.APP_FORM_URLENCODED).authorization(request.getHeader(HttpHeaders.AUTHORIZATION)).build();
        HttpConfig config = HttpConfig.custom().headers(headers).url(refreshTokenUrl).map(map);
        String token = HttpClientUtil.post(config);
        JSONObject jsonObj = JSON.parseObject(token);
        String accessTokenNew = (String) jsonObj.get("access_token");
        String refreshTokenNew = (String) jsonObj.get("refresh_token");
        String loginName = (String) jsonObj.get("username");
        LoginAuthDto loginAuthDto = null;
        if(loginType.equals("member")){
            UmsMember umsMember = umsMemberService.selectByUserName(loginName);
            loginAuthDto = new LoginAuthDto(umsMember.getId(), umsMember.getUsername(), umsMember.getNickname(), "member");
        }
        //清除旧的token
        umsMemberService.deleteRedisToken(accessToken);
        // 创建刷新token
        memberLoginLogService.saveLoginUserLog(accessTokenNew, refreshTokenNew, loginAuthDto, request);
        return jsonObj;
    }

}
