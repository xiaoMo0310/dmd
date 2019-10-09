package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.service.IntegralGiftsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ChenYanbing
 * @title: IntegralGiftsController
 * @projectName dmd-master
 * @description: TODO 首页-积分好礼
 * @date 2019/10/910:30
 */
@Controller
@Api(tags = "IntegralGiftsController", description = "首页-积分好礼")
@RequestMapping("/dynamic")
public class IntegralGiftsController {

    @Autowired
    private IntegralGiftsService integralGiftsService;

    /**
     * 查询全部积分好礼
     * @return
     */
    @ApiOperation("查询全部积分好礼")
    @RequestMapping(value = "/selectIntegralGifts",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGifts() {
        List<IntegralGiftsBean> integralGiftsList = integralGiftsService.queryIntegralGifts();
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 分页查询全部积分好礼
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation("分页查询全部积分好礼")
    @RequestMapping(value = "/selectIntegralGiftsPage",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGiftsPage(@RequestParam Integer pageNum,
                                                                        @RequestParam Integer pageSize) {
        List<IntegralGiftsBean> integralGiftsList = integralGiftsService.queryIntegralGiftsPage(pageNum,pageSize);
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 根据礼品id查询好礼详情
     * @param 礼品id
     * @return
     */
    @ApiOperation("根据礼品id查询好礼详情")
    @RequestMapping(value = "/selectIntegralGiftsById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGiftsById(Long id) {
        List<IntegralGiftsBean> integralGiftsList = integralGiftsService.queryIntegralGiftsById(id);
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 添加积分好礼
     * @param integralGiftsBean
     * @return
     */
    @ApiOperation("添加积分好礼")
    @RequestMapping(value = "/addIntegralGifts",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addIntegralGifts(@RequestBody IntegralGiftsBean integralGiftsBean) {
        int count = integralGiftsService.addIntegralGifts(integralGiftsBean);
        if (count > 0) {
            return CommonResult.success(count,"添加成功");
        }
        return CommonResult.failed("添加失败");
    }

}
