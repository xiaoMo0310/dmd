package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.UmsNotice;
import com.dmd.mall.service.UmsNoticeService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户通告表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/ums")
@Api(tags = "UmsNoticeController", description = "通知信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeController extends BaseController {

    @Autowired
    private UmsNoticeService umsNoticeService;

    @GetMapping("/message/find/{userType}")
    @ApiOperation(httpMethod = "GET", value = "查询当前登录人的通知信息")
    @ApiImplicitParam(name ="userType", value = "用户类型(1:普通用户 2:教练)", paramType = "path", dataType = "int")
    public Wrapper findLoginUserMessage(@PathVariable Integer userType) {
        List<UmsNotice> list = umsNoticeService.findLoginUserMessage(getLoginAuthDto(), userType);
        return WrapMapper.ok(list);
    }

}

