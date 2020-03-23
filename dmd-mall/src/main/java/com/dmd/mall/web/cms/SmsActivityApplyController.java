package com.dmd.mall.web.cms;

import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.SmsActivityApplyDto;
import com.dmd.mall.service.SmsActivityApplyService;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 活动报名表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@RestController
@RequestMapping("/sms")
@Api(tags = "SmsActivityApplyController", description = "活动报名中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SmsActivityApplyController extends BaseController {

    @Autowired
    private SmsActivityApplyService activityApplyService;

    @PostMapping("/activity/apply")
    @ApiOperation(httpMethod = "POST", value = "添加报名活动信息")
    @ApiImplicitParam(name ="activityApplyDto", value = "报名信息", dataType = "SmsActivityApplyDto", paramType = "body")
    public Wrapper getList(@RequestBody SmsActivityApplyDto activityApplyDto) {
        int result = activityApplyService.addActivityApplyMessage(getLoginAuthDto(), activityApplyDto);
        return handleResult( result );
    }

}

