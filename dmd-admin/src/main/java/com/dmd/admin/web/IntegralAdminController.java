package com.dmd.admin.web;

import com.dmd.admin.model.domain.IntegralRuleBean;
import com.dmd.admin.model.domain.TopicBean;
import com.dmd.admin.service.IntegralAdminService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralAdminController
 * @projectName dmd-masters
 * @description: TODO 后台--积分管理
 * @date 2019/10/2214:18
 */
@Controller
@Api(tags = "IntegralAdminController", description = "后台--积分管理")
@RequestMapping("/integral")
public class IntegralAdminController {

    @Autowired
    private IntegralAdminService integralAdminService;

    /**
     * 积分规则说明查询
     * @return
     */
    @ApiOperation("查询积分规则说明")
    @RequestMapping(value = "/selectIntegralRule",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<IntegralRuleBean>> queryIntegralRule(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<IntegralRuleBean> List = integralAdminService.queryIntegralRule();
        return CommonResult.success(CommonPage.restPage(List));
    }

    /**
     * 积分规则修改
     * @param integralRuleBean
     * @return
     */
    @ApiOperation("修改积分规则说明")
    @RequestMapping(value = "/updateIntegralRule",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateIntegralRule(@RequestBody IntegralRuleBean integralRuleBean) {
        int count = integralAdminService.updateIntegralRule(integralRuleBean);
        if (count > 0) {
            return CommonResult.success(count,"修改成功");
        }
        return CommonResult.failed("修改失败");

    }

    /**
     * 回显积分规则
     * @param id
     * @return
     */
    @ApiOperation("回显积分规则")
    @RequestMapping(value = "/findIntegralInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralRuleBean> findIntegralInfoById(@RequestParam Long id){
        IntegralRuleBean integralRuleBean = integralAdminService.findIntegralInfoById(id);
        return CommonResult.success(integralRuleBean);
    }
}
