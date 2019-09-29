package com.dmd.mall.web.ums;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员登录注册管理Controller
 * Created by macro on 2018/8/3.
 */
@Controller
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode,
                                 HttpServletRequest request) {
        return memberService.register(username, password, telephone, authCode,request);
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
    public CommonResult findPassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String confirmPassword,
                                       @RequestParam String authCode,
                                       HttpServletRequest request) {
        return memberService.findPassword(telephone, password,confirmPassword, authCode,request);
    }
}
