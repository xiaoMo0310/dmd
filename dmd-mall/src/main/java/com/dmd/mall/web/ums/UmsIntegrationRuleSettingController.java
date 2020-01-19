package com.dmd.mall.web.ums;


import com.dmd.core.support.BaseController;
import com.dmd.mall.service.UmsIntegrationRuleSettingService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分消费设置 前端控制器
 * </p>
 * @author YangAnsheng
 * @since 2019-10-15
 */
@RestController
@RequestMapping("/umsIntegrationRuleSetting")
@Api(tags = "UmsIntegrationRuleSettingController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UmsIntegrationRuleSettingController extends BaseController {

    @Autowired
    private UmsIntegrationRuleSettingService umsIntegrationRuleSettingService;


}

