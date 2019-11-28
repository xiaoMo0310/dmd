package com.dmd.admin.web;


import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.service.UmsMemberService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsMemberController", description = "用户中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsMemberController extends BaseController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("registerUser/countDay")
    @ApiOperation(httpMethod = "GET", value = "统计当日新注册的用户")
    public Wrapper countDayRegisterUser() {
        JSONObject registerUsers = umsMemberService.countDayRegisterUser();
        return WrapMapper.ok(registerUsers);
    }

    @GetMapping("visitUser/countYesterday")
    @ApiOperation(httpMethod = "GET", value = "统计昨日访问用户的数量")
    public Wrapper countYesterdayVisitUser(){
        Long visitUser = umsMemberService.countYesterdayVisitUser();
        return WrapMapper.ok(visitUser);
    }

    @GetMapping("totalUser/count")
    @ApiOperation(httpMethod = "GET", value = "统计平台的总用户量")
    public Wrapper countTotalUser() {
        Long totalUser = umsMemberService.countTotalUser();
        return WrapMapper.ok(totalUser);
    }

    @GetMapping("retentionRate/count/{day}")
    @ApiOperation(httpMethod = "GET", value = "统计七日留存率")
    public Wrapper countSevenDayRetentionRate(@PathVariable Integer day){
        JSONObject object = umsMemberService.countRetentionRate(day);
        return WrapMapper.ok(object);
    }
    @GetMapping("retentionRate/countThirty/{day}")
    @ApiOperation(httpMethod = "GET", value = "统计三十日留存率")
    public Wrapper countThirtyDayRetentionRate(@PathVariable Integer day){
        JSONObject object = umsMemberService.countThirtyDayRetentionRate(day);
        return WrapMapper.ok(object);
    }

    @PostMapping("userList/findPage")
    @ApiOperation(httpMethod = "POST", value = "查询用户列表信息")
    @ApiImplicitParam(name ="userQueryParam", value = "查询需要的数据", dataType = "UmsUserQueryParam")
    public Wrapper<PageInfo> selectUserList(@RequestBody UmsUserQueryParam userQueryParam) {
        PageInfo pageInfo = umsMemberService.selectUserList(userQueryParam);
        return WrapMapper.ok(pageInfo);
    }

    @GetMapping("user/editStatus")
    @ApiOperation(httpMethod = "GET", value = "修改用户的状态信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="id", value = "用户id", paramType = "query", dataType = "Long"),
                        @ApiImplicitParam(name ="status", value = "用户状态(0:禁用 1:启用)", paramType = "query", dataType = "Integer")})
    public Wrapper selectUserList(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        int result = umsMemberService.updateUserStatus(id, status);
        return handleResult(result);
    }

    @ApiOperation("批量修改用户的状态")
    @PostMapping(value = "/status/batchUpdate")
    @ApiImplicitParams({@ApiImplicitParam(name ="ids", value = "用户id集合", paramType = "query", dataType = "List"),
            @ApiImplicitParam(name ="status", value = "用户状态(0:禁用 1:启用)", paramType = "query", dataType = "Integer")})
    public Wrapper updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                       @RequestParam("status") Integer status) {
        int count = umsMemberService.batchUpdateUserStatus(ids, status);
        return handleResult(count);
    }

}

