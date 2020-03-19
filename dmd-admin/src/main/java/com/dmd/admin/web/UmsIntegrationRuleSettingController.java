package com.dmd.admin.web;

import com.dmd.admin.model.domain.UmsIntegrationRuleSetting;
import com.dmd.admin.service.UmsIntegrationRuleSettingService;
import com.dmd.core.support.BaseController;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 积分消费设置 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-18
 */
@RestController
@RequestMapping("/integrationRuleSetting")
@Api(tags = "UmsIntegrationRuleSettingController", description = "积分规则配置中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsIntegrationRuleSettingController extends BaseController {

    @Autowired
    private UmsIntegrationRuleSettingService integrationRuleSettingService;

    @GetMapping("/{id}")
    @ApiOperation(httpMethod = "GET", value = "获取指定积分规则设置")
    @ApiImplicitParam(name ="id", value = "规则id", dataType = "long", paramType = "path")
    public Wrapper getItem(@PathVariable Long id) {
        UmsIntegrationRuleSetting integrationRuleSetting = integrationRuleSettingService.selectByKey(id);
        return WrapMapper.ok(integrationRuleSetting);
    }

    @PostMapping("/update")
    @ApiOperation(httpMethod = "POST", value = "修改积分规则")
    @ApiImplicitParam(name ="umsIntegrationRuleSetting", value = "要修改的数据", dataType = "UmsIntegrationRuleSetting", paramType = "body")
    public Wrapper update(@RequestBody UmsIntegrationRuleSetting umsIntegrationRuleSetting) {
        umsIntegrationRuleSetting.setUpdateInfo( getLoginAuthDto() );
        int count = integrationRuleSettingService.update(umsIntegrationRuleSetting);
        return handleResult( count );
    }

}

