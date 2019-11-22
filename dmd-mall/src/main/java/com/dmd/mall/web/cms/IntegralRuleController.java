package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.IntegralRuleBean;
import com.dmd.mall.service.IntegralRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralRuleController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2213:51
 */
@Controller
@Api(tags = "IntegralRuleController", description = "积分规则说明")
@RequestMapping("/integralRule")
public class IntegralRuleController {

    @Autowired
    private IntegralRuleService integralRuleService;


    @ApiOperation("查询积分规则说明")
    @RequestMapping(value = "/selectIntegralRule",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralRuleBean>> queryIntegralRule() {
        List<IntegralRuleBean> List = integralRuleService.queryIntegralRule();
        return CommonResult.success(List);
    }
}
