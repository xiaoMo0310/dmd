package com.dmd.admin.web;


import com.dmd.admin.service.UmsMemberService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "UmsMemberController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsMemberController extends BaseController {

    @Autowired
    private UmsMemberService umsMemberService;

    @GetMapping("registerUser/countDay")
    @ApiOperation(httpMethod = "GET", value = "统计当日新注册的用户")
    public Wrapper cancelOrderDoc() {
        Long registerUsers = umsMemberService.countDayRegisterUser();
        return WrapMapper.ok(registerUsers);
    }

}

