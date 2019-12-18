package com.dmd.mall.web.ums;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.model.dto.UmsCoachDto;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/ums")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachLoginController extends BaseController {

    @Autowired
    private UmsCoachService coachService;


    @ApiOperation(httpMethod = "POST", value ="修改密码")
    @RequestMapping(value = "/coach/updatePassword")
    @ApiImplicitParam(name ="findPasswordDto", value = "修改密码需要的信息", dataType = "FindPasswordDto", paramType = "body")
    public Wrapper updatePassword(@RequestBody FindPasswordDto findPasswordDto,
                                  LoginAuthDto loginAuthDto) {
        if (loginAuthDto.getUserName().equals(findPasswordDto.getTelephone())){
            return coachService.updatePassword(findPasswordDto.getTelephone(), findPasswordDto.getOldPassword(),findPasswordDto.getNewPassword(),findPasswordDto.getConfirmPassword());
        }else {
            return WrapMapper.error("无权修改他");
        }
    }

    @ApiOperation(httpMethod = "POST", value ="修改教练个人信息")
    @PostMapping(value = "/coachMessage/update")
    @ApiImplicitParam(name ="umsCoachDto", value = "修改教练需要的信息", dataType = "UmsCoachDto", paramType = "body")
    public Wrapper updateCoachMessage(@RequestBody UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto) {
        int result = coachService.updateCoachMessage(umsCoachDto, loginAuthDto);
        return handleResult(result);
    }

    @ApiOperation("查询教练个人信息")
    @GetMapping(value = "/coachMessage/find")
    @ApiImplicitParam(name ="umsCoachDto", value = "查询教练需要的信息", dataType = "UmsCoachDto", paramType = "body")
    public Wrapper findCoachMessage(@RequestBody UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto) {
        int result = coachService.updateCoachMessage(umsCoachDto, loginAuthDto);
        return handleResult(result);
    }
}
