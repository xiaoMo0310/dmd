package com.dmd.mall.web.ums;


import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 教练表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@RestController
@RequestMapping("/sso")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachController extends BaseController {

    @Autowired
    private UmsCoachService umsCoachService;

    @ApiOperation("教练注册")
    @PostMapping(value = "/coach/register")
    public CommonResult register(@RequestParam(required = true) String username,
                                 @RequestParam(required = true) String password,
                                 @RequestParam(required = true) String authCode,
                                 @RequestParam(required = false) String invitationCode,
                                 HttpServletRequest request) {
        //return umsCoachService.register(username, password, invitationCode, authCode,request);
        return  null;
    }

    @ApiOperation("教练端找回密码")
    @PostMapping(value = "/coach/findPassword")
    @ApiImplicitParam(name ="findPasswordDto", value = "找回密码信息", dataType = "FindPasswordDto", paramType = "body")
    public Wrapper findPassword(@RequestBody FindPasswordDto findPasswordDto, HttpServletRequest request) {
        return umsCoachService.findPassword(findPasswordDto.getTelephone(), findPasswordDto.getPassword(),findPasswordDto.getConfirmPassword(), findPasswordDto.getAuthCode(),request);
    }

}

