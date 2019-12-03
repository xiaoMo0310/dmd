package com.dmd.mall.web.ums;

import com.dmd.FileUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.result.CommonResult;
import com.dmd.core.utils.RequestUtil;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@Api(tags = "UmsMemberAuthenticationController", description = "登陆后会员的操作接口")
@RequestMapping("/authenticationed/member")
public class UmsMemberAuthenticationController {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UmsMemberService memberService;
    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestBody FindPasswordDto findPasswordDto,
                                       Authentication authentication) {
        if (authentication.getPrincipal().equals(findPasswordDto.getTelephone())){
            return memberService.updatePassword(findPasswordDto.getTelephone(), findPasswordDto.getOldPassword(),findPasswordDto.getNewPassword(),findPasswordDto.getConfirmPassword());
        }else {
            return CommonResult.failed("无权修改他人的信息");
        }
    }

    @ApiOperation("修改个人信息")
    @RequestMapping(value = "/updatePersonalData", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePersonalData(@RequestBody UmsMember umsMember,HttpServletRequest request) throws UnsupportedEncodingException, JsonProcessingException {
        Claims claims=JwtUtil.getDate(request);
        umsMember.setUsername((String) claims.get("username"));
        return memberService.updatePersonalData(umsMember);

    }
    @ApiOperation("修改头像")
    @RequestMapping(value = "/updateIcon", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIcon(@RequestBody MultipartFile file,HttpServletRequest request) throws UnsupportedEncodingException, JsonProcessingException {
        UmsMember umsMember=new UmsMember();
        Claims claims=JwtUtil.getDate(request);
        umsMember.setUsername((String) claims.get("username"));
        if (file!=null){
            String icon=FileUtil.FileUpload(file,request);
            umsMember.setIcon(icon);
        }
        return memberService.updatePersonalData(umsMember);

    }

    @ApiOperation("更改手机号")
    @RequestMapping(value = "/updatePhone", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePhone(@RequestParam String telephone,
                                    @RequestParam String authCode,
                                    HttpServletRequest request) {
        return memberService.updatePhone(telephone,authCode,request);
    }

    @ApiOperation("获取用户信息")
    @RequestMapping(value = "memberMessage/find", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePhone() {
        LoginAuthDto loginUser = RequestUtil.getLoginUser();
        UmsMember umsMember = memberService.getById(loginUser.getUserId());
        return CommonResult.success(umsMember);
    }
}
