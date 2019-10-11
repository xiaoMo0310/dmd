package com.dmd.mall.web.ums;

import com.dmd.FileUtil;
import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.service.UmsMemberService;
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
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String oldPassword,
                                       @RequestParam String confirmPassword,
                                       @RequestParam String newPassword,
                                       Authentication authentication) {
        if (authentication.getPrincipal().equals(telephone)){
            return memberService.updatePassword(telephone, oldPassword,newPassword,confirmPassword);
        }else {
            return CommonResult.failed("无权修改他人的信息");
        }
    }

    @ApiOperation("修改个人信心")
    @RequestMapping(value = "/updatePersonalData", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePersonalData(@RequestBody UmsMember umsMember, MultipartFile file, Authentication authentication, HttpServletRequest request) {
        if (file!=null){
            String icon=FileUtil.FileUpload(file,request);
            umsMember.setIcon(icon);
        }
        umsMember.setUsername(authentication.getPrincipal().toString());
        return memberService.updatePersonalData(umsMember);

    }
}
