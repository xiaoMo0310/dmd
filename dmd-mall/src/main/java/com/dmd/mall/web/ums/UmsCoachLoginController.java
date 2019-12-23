package com.dmd.mall.web.ums;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.FindPasswordDto;
import com.dmd.mall.model.dto.UmsCoachDto;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.mall.model.vo.UmsMemberVo;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
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
@Api(tags = "UmsCoachLoginController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachLoginController extends BaseController {

    @Autowired
    private UmsCoachService coachService;


    @ApiOperation(httpMethod = "POST", value ="修改密码")
    @RequestMapping(value = "/coach/updatePassword")
    @ApiImplicitParam(name ="findPasswordDto", value = "修改密码需要的信息", dataType = "FindPasswordDto", paramType = "body")
    public Wrapper updatePassword(@RequestBody FindPasswordDto findPasswordDto) {
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        if (loginAuthDto.getUserName().equals(findPasswordDto.getTelephone())){
            return coachService.updatePassword(findPasswordDto.getTelephone(), findPasswordDto.getOldPassword(),findPasswordDto.getNewPassword(),findPasswordDto.getConfirmPassword());
        }else {
            return WrapMapper.error("无权修改他");
        }
    }

    @ApiOperation(httpMethod = "POST", value ="修改教练个人信息")
    @PostMapping(value = "/coachMessage/update")
    @ApiImplicitParam(name ="umsCoachDto", value = "修改教练需要的信息", dataType = "UmsCoachDto", paramType = "body")
    public Wrapper updateCoachMessage(@RequestBody UmsCoachDto umsCoachDto) {
        int result = coachService.updateCoachMessage(umsCoachDto, getLoginAuthDto());
        return handleResult(result);
    }

    @ApiOperation(httpMethod = "GET",value ="查询教练个人信息")
    @GetMapping(value = "/coachMessage/find")
    public Wrapper findCoachMessage() {
        UmsCoachVo umsCoachVo = coachService.findUmsCoachByCoachId(getLoginAuthDto());
        return WrapMapper.ok(umsCoachVo);
    }

    @ApiOperation(httpMethod = "GET",value = "查询我的中心下的教练个人信息并统计数据")
    @GetMapping(value = "/coachMessage/findAndCount")
    public Wrapper findCoachMessageAndCountNum() {
        UmsCoachVo umsCoachVo = coachService.findCoachMessageAndCountNum(getLoginAuthDto());
        return WrapMapper.ok(umsCoachVo);
    }

    @ApiOperation(httpMethod = "POST", value = "获取教练邀请的用户")
    @PostMapping(value = "/coachInviteUser/find")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper findCoachMessageAndCountNum(@RequestBody BaseQuery baseQuery) {
        PageInfo<UmsMemberVo> pageInfo = coachService.findCoachInviteUserMessage(baseQuery, getLoginAuthDto());
        return WrapMapper.ok(pageInfo);
    }
}
