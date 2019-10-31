package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.service.MyIntegralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author ChenYanbing
 * @title: MyIntegralController
 * @projectName dmd-masters
 * @description: TODO
 * @date 2019/10/2215:22
 */
@Controller
@Api(tags = "MyIntegralController", description = "我的积分")
@RequestMapping("/myIntegral")
public class MyIntegralController {

    @Autowired
    private MyIntegralService myIntegralService;

    /**
     * 查询我的总积分
     * @param userId
     * @return
     */
    @ApiOperation("我的总积分")
    @RequestMapping(value = "/selectMyIntegral",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> queryMyIntegral(@RequestParam Long userId) {
        Integer integration = myIntegralService.queryMyIntegral(userId);
        return CommonResult.success(integration);
    }

    /**
     * 我的积分收入分页查询
     * @param userId
     * @return
     */
    @ApiOperation("我的积分收入分页查询")
    @RequestMapping(value = "/selectIntegralIncome",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsIntegrationChangeLog>> selectIntegralIncome(@RequestParam Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,
                                                                            @RequestParam Integer pageNum,
                                                                            @RequestParam Integer pageSize) {
        List<UmsIntegrationChangeLog> integration = myIntegralService.selectIntegralIncome(userId,umsIntegrationChangeLog,pageNum,pageSize);
        return CommonResult.success(integration);
    }

    /**
     * 我的积分支出查询
     * @param userId
     * @return
     */
    @ApiOperation("我的积分支出分页查询")
    @RequestMapping(value = "/selectIntegralExpend",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsIntegrationChangeLog>> selectIntegralExpend(@RequestParam Long userId,UmsIntegrationChangeLog umsIntegrationChangeLog,
                                                                            @RequestParam Integer pageNum,
                                                                            @RequestParam Integer pageSize) {
        List<UmsIntegrationChangeLog> integration = myIntegralService.selectIntegralExpend(userId,umsIntegrationChangeLog,pageNum,pageSize);
        return CommonResult.success(integration);
    }

    /**
     * 我的积分总收入数
     * @param userId
     * @return
     */
    @ApiOperation("我的积分总收入数")
    @RequestMapping(value = "/selectIntegralIncomeSum",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> selectIntegralIncomeSum(@RequestParam Long userId) {
        Integer integration = myIntegralService.selectIntegralIncomeSum(userId);
        return CommonResult.success(integration);
    }

    /**
     * 我的积分总支出数
     * @param userId
     * @return
     */
    @ApiOperation("我的积分总支出数")
    @RequestMapping(value = "/selectIntegralExpendSum",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Integer> selectIntegralExpendSum(@RequestParam Long userId) {
        Integer integration = myIntegralService.selectIntegralExpendSum(userId);
        return CommonResult.success(integration);
    }
}
