package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.UmsGroupChatVo;
import com.dmd.mall.service.UmsGroupChatService;
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
 * 教练群聊创建  前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-30
 */
@RestController
@RequestMapping("/umsGroupChat")
@Api(tags = "UmsGroupChatController", description = "群聊中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsGroupChatController extends BaseController {

    @Autowired
    private UmsGroupChatService umsGroupChatService;

    @GetMapping("/groupChart/find")
    @ApiOperation(httpMethod = "GET", value = "查询当前登录人需要创建的群聊或者群聊需要添加的人")
    public Wrapper findCoachNeedGroupChart() {
        UmsGroupChatVo groupChatVo = umsGroupChatService.findCoachNeedGroupChart(getLoginAuthDto());
        return WrapMapper.ok(groupChatVo);
    }

    @PostMapping("/groupChart/status/update")
    @ApiOperation(httpMethod = "POST", value = "修改创建的群聊状态为已创建")
    @ApiImplicitParam(name ="ids", value = "修改群聊id集合", paramType = "body", dataType = "List")
    public Wrapper updateGroupChartStatus(@RequestBody List<Long> ids) {
        int result = umsGroupChatService.updateGroupChartStatus(getLoginAuthDto(), ids);
        return handleResult(result);
    }
}

