package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.UmsNoticeReadVo;
import com.dmd.mall.service.UmsNoticeMarkService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
@Api(tags = "UmsNoticeMarkController", description = "通知用户中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsNoticeMarkController extends BaseController {

    @Autowired
    private UmsNoticeMarkService umsNoticeMarkService;

    @GetMapping("/noticeMark/updateIsRead")
    @ApiOperation(httpMethod = "GET", value = "修改当前登录人的通知标记为已读")
    @ApiImplicitParam(name ="noticeId", value = "通告id", paramType = "query", dataType = "Long")
    public Wrapper findLoginUserMessage(@RequestParam Long noticeId) {
        int result = umsNoticeMarkService.updateIsRead(getLoginAuthDto(), noticeId);
        return handleResult(result);
    }

    @PostMapping("/isRead/find")
    @ApiOperation(httpMethod = "POST", value = "查询当前登录人信息是否有未读")
    public Wrapper findLoginUserMessage() {
        List<UmsNoticeReadVo> umsNoticeReadVo = umsNoticeMarkService.findNoticeRead(getLoginAuthDto());
        return WrapMapper.ok(umsNoticeReadVo);
    }
}

