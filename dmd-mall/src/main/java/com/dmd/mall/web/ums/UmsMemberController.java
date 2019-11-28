package com.dmd.mall.web.ums;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
