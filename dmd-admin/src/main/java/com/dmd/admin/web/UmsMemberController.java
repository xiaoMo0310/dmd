package com.dmd.admin.web;


import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.service.UmsMemberService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

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
    public Wrapper countYesterdayVisitUser() throws ParseException {
        Long visitUser = umsMemberService.countYesterdayVisitUser();
        System.out.println(visitUser);
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
    public Wrapper countSevenDayRetentionRate(@PathVariable Integer day) throws ParseException {
        JSONObject object = umsMemberService.countRetentionRate(day);
        return WrapMapper.ok(object);
    }

}

