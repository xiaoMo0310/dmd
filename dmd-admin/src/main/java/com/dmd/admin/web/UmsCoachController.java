package com.dmd.admin.web;


import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.dto.UmsCoachDto;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsCoachVo;
import com.dmd.admin.service.UmsCoachService;
import com.dmd.base.dto.BaseQuery;
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
 * 教练表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsCoachController", description = "教练中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsCoachController extends BaseController {

    @Autowired
    private UmsCoachService umsCoachService;

    @GetMapping("/registerCoach/countDay")
    @ApiOperation(httpMethod = "GET", value = "统计当日新注册的教练")
    public Wrapper countDayRegisterCoach() {
        JSONObject registerUsers = umsCoachService.countDayRegisterCoach();
        return WrapMapper.ok(registerUsers);
    }

    @GetMapping("/visitCoach/countYesterday")
    @ApiOperation(httpMethod = "GET", value = "统计昨日访问教练的数量")
    public Wrapper countYesterdayVisitCoach(){
        Long visitUser = umsCoachService.countYesterdayVisitCoach();
        return WrapMapper.ok(visitUser);
    }

    @GetMapping("/totalCoach/count")
    @ApiOperation(httpMethod = "GET", value = "统计平台的总教练量")
    public Wrapper countTotalCoach() {
        Long totalUser = umsCoachService.countTotalCoach();
        return WrapMapper.ok(totalUser);
    }

    @GetMapping("/coach/retentionRate/count/{day}")
    @ApiOperation(httpMethod = "GET", value = "统计教练七日留存率")
    public Wrapper countCoachSevenDayRetentionRate(@PathVariable Integer day){
        JSONObject object = umsCoachService.countCoachRetentionRate(day);
        return WrapMapper.ok(object);
    }
    @GetMapping("/coach/retentionRate/countThirty/{day}")
    @ApiOperation(httpMethod = "GET", value = "统计教练三十日留存率")
    public Wrapper countCoachThirtyDayRetentionRate(@PathVariable Integer day){
        JSONObject object = umsCoachService.countCoachThirtyDayRetentionRate(day);
        return WrapMapper.ok(object);
    }
    
    @PostMapping("/coachList/findPage")
    @ApiOperation(httpMethod = "POST", value = "查询用户列表信息")
    @ApiImplicitParam(name ="userQueryParam", value = "查询需要的数据", dataType = "UmsUserQueryParam")
    public Wrapper<PageInfo> selectUserList(@RequestBody UmsUserQueryParam userQueryParam) {
        PageInfo pageInfo = umsCoachService.selectCoachList(userQueryParam);
        return WrapMapper.ok(pageInfo);
    }

    @GetMapping("/coach/editStatus")
    @ApiOperation(httpMethod = "GET", value = "修改用户的状态信息")
    @ApiImplicitParams({@ApiImplicitParam(name ="id", value = "用户id", paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name ="status", value = "用户状态(0:禁用 1:启用)", paramType = "query", dataType = "Integer")})
    public Wrapper selectUserList(@RequestParam("id") Long id, @RequestParam("status") Integer status) {
        int result = umsCoachService.updateCoachStatus(id, status);
        return handleResult(result);
    }

    @GetMapping("/coach/findById/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据id查询用户信息")
    @ApiImplicitParam(name ="id", value = "用户id", paramType = "path", dataType = "Long")
    public Wrapper selectUserById(@PathVariable Long id) {
        UmsCoachVo umsCoachVo = umsCoachService.selectCoachById(id);
        return WrapMapper.ok(umsCoachVo);
    }

    @ApiOperation("批量修改用户的状态")
    @PostMapping(value = "/coach/status/batchUpdate")
    @ApiImplicitParams({@ApiImplicitParam(name ="ids", value = "用户id集合", paramType = "query", dataType = "List"),
            @ApiImplicitParam(name ="status", value = "用户状态(0:禁用 1:启用)", paramType = "query", dataType = "Integer")})
    public Wrapper updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                       @RequestParam("status") Integer status) {
        int count = umsCoachService.batchUpdateCoachStatus(ids, status);
        return handleResult(count);
    }

    @PostMapping("/coach/findByStatus")
    @ApiOperation(httpMethod = "POST", value = "查询审核的教练信息")
    @ApiImplicitParam(name ="baseQuery", value = "分页所需要的数据", dataType = "BaseQuery", paramType = "body")
    public Wrapper selectCoachByStatus(@RequestBody BaseQuery baseQuery) {
        PageInfo pageInfo = umsCoachService.selectCoachByStatus(baseQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("/coach/updateById")
    @ApiOperation(httpMethod = "POST", value = "修改教练的审核信息")
    @ApiImplicitParam(name ="umsCoachDto", value = "修改的数据", dataType = "UmsCoachDto", paramType = "body")
    public Wrapper updateCoachMessageById(@RequestBody UmsCoachDto umsCoachDto) {
        int result = umsCoachService.updateCoachMessageById(umsCoachDto, getLoginAuthDto());
        return handleResult(result);
    }
}

