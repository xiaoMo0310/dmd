package com.dmd.mall.web.ums;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/12/16 10:31
 * @Description 教练登录操作
 */

@RestController
@RequestMapping("/sso")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachLoginController {

    @Autowired
    private UmsCoachService coachService;
    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public Wrapper updatePassword(@RequestBody FindPasswordDto findPasswordDto,
                                  LoginAuthDto loginAuthDto) {
        if (loginAuthDto.getUserName().equals(findPasswordDto.getTelephone())){
            return coachService.updatePassword(findPasswordDto.getTelephone(), findPasswordDto.getOldPassword(),findPasswordDto.getNewPassword(),findPasswordDto.getConfirmPassword());
        }else {
            return WrapMapper.error("无权修改他人的信息");
        }
    }
}
