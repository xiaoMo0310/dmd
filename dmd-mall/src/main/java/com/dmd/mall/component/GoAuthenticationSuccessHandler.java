package com.dmd.mall.component;

import com.dmd.base.result.CommonResult;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by macro on 2018/8/6.
 */
@Component
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header != null && header.toLowerCase().startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        String[] tokens = this.extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails=clientDetailsService.loadClientByClientId(clientId);
        if (clientDetails==null){
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在"+clientId);
        }else if (!passwordEncoder.matches(clientSecret,clientDetails.getClientSecret())){
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配"+clientId);
        }
        TokenRequest tokenRequest=new TokenRequest(MapUtils.EMPTY_MAP,clientId, clientDetails.getScope(),"custom");
        OAuth2Request oAuth2Request=tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication=new OAuth2Authentication(oAuth2Request,authentication);
        OAuth2AccessToken token=authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(JSONUtil.parse(CommonResult.success(token,"登录成功")));
        response.getWriter().flush();
    }
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");

        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException var7) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        } else {
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        }
    }
}
