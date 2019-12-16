package com.dmd.mall.web.ums;

import com.alibaba.fastjson.JSONObject;
import com.dmd.base.result.CommonResult;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.model.dto.RefreshTokenDto;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.service.UmsUserTokenService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.google.common.base.Preconditions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 会员登录注册管理Controller
 * Created by macro on 2018/8/3.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UmsMemberService memberService;
    @Resource
    private ClientDetailsService clientDetailsService;
    @Resource
    private UmsUserTokenService umsUserTokenService;

    private static final String BEARER_TOKEN_TYPE = "Basic ";

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam(required = true) String username,
                                 @RequestParam(required = true) String password,
                                 @RequestParam(required = true) String authCode,
                                 @RequestParam(required = false) String invitationCode,
                                 HttpServletRequest request) {
        return memberService.register(username, password, invitationCode, authCode,request);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String mobile, HttpServletRequest request) {
        return memberService.generateAuthCode(mobile,request);
    }

    @ApiOperation("找回密码")
    @RequestMapping(value = "/findPassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult findPassword(@RequestBody FindPasswordDto findPasswordDto, HttpServletRequest request) {
        return memberService.findPassword(findPasswordDto.getTelephone(), findPasswordDto.getPassword(),findPasswordDto.getConfirmPassword(), findPasswordDto.getAuthCode(),request);
    }

    @PostMapping(value = "/auth/user/refreshToken")
    @ApiOperation(httpMethod = "POST", value = "刷新token")
    @ResponseBody
    public Wrapper refreshToken(@RequestBody RefreshTokenDto refreshTokenDto, HttpServletRequest request) {
        System.out.println("刷新进来了");
        String accessToken = refreshTokenDto.getAccessToken();
        String refreshToken = refreshTokenDto.getRefreshToken();
        String loginType = refreshTokenDto.getLoginType();
        JSONObject token;
        try {
            Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(accessToken), "accessToken is null");
            Preconditions.checkArgument(org.apache.commons.lang3.StringUtils.isNotEmpty(refreshToken), "refreshToken is null");
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client信息");
            }
            String[] tokens = RequestUtil.extractAndDecodeHeader(header);
            assert tokens.length == 2;

            String clientId = tokens[0];
            String clientSecret = tokens[1];

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }

            token = umsUserTokenService.refreshToken(accessToken, refreshToken, loginType, request);
        } catch (Exception e) {
            logger.error("refreshToken={}", e.getMessage(), e);
            return WrapMapper.error();
        }
        return WrapMapper.ok(token);
    }
}
