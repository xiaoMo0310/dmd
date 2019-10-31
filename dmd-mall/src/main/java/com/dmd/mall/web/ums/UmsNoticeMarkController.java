package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.wrapper.Wrapper;
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
 * 用户通知标记表  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-30
 */
@RestController
@RequestMapping("/umsNoticeMark")
@Api(tags = "UmsNoticeMarkController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeMarkController extends BaseController {

    @Autowired
    private UmsNoticeMarkService umsNoticeMarkService;

    @PostMapping("/noticeMark/updateIsRead/{userType}")
    @ApiOperation(httpMethod = "POST", value = "修改当前登录人的通知标记未已读")
    @ApiImplicitParams({ @ApiImplicitParam(name ="noticeIds", value = "通告id集合", paramType = "query", dataType = "Long", allowMultiple = true),
                        @ApiImplicitParam(name ="userType", value = "用户类型(1:普通用户 2:教练)", paramType = "path", dataType = "int")})
    public Wrapper findLoginUserMessage(@RequestParam List<Long> noticeIds, @PathVariable Integer userType) {
        int result = umsNoticeMarkService.updateIsRead(getLoginAuthDto(), noticeIds, userType);
        return handleResult(result);
    }
}

