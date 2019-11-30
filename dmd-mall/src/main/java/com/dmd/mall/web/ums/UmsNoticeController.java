package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.UmsNoticeVo;
import com.dmd.mall.service.UmsNoticeService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/message/find")
    @ApiOperation(httpMethod = "GET", value = "查询当前登录人的通知信息")
    public Wrapper findLoginUserMessage() {
        Map<Integer, List<UmsNoticeVo>> map = umsNoticeService.findLoginUserMessage(getLoginAuthDto());
        return WrapMapper.ok(map);
    }

}

