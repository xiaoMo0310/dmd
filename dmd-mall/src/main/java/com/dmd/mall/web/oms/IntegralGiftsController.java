package com.dmd.mall.web.oms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.CommentBean;
import com.dmd.mall.model.domain.DynamicBean;
import com.dmd.mall.model.domain.IntegralGiftsBean;
import com.dmd.mall.model.domain.IntegralGiftsSpeBean;
import com.dmd.mall.service.IntegralGiftsService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/integralGifts")
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
    public CommonResult<PageInfo<IntegralGiftsBean>> queryIntegralGiftsPage(@RequestParam Integer pageNum,
                                                                            @RequestParam Integer pageSize) {
        List<IntegralGiftsBean> integralGiftsList = integralGiftsService.queryIntegralGiftsPage(pageNum,pageSize);
        return CommonResult.success(new PageInfo<>(integralGiftsList));
    }

    /**
     * 根据礼品id查询好礼详情
     * @param 礼品 id
     * @return
     */
    @ApiOperation("根据礼品id查询好礼详情")
    @RequestMapping(value = "/selectIntegralGiftsById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralGiftsBean>> queryIntegralGiftsById(@RequestParam Long id) {
        List<IntegralGiftsBean> integralGiftsList = integralGiftsService.queryIntegralGiftsById(id);
        return CommonResult.success(integralGiftsList);
    }

    /**
     * 添加/修改积分好礼
     * @param integralGiftsBean
     * @return
     */
    @ApiOperation("添加/修改积分好礼")
    @RequestMapping(value = "/addIntegralGifts",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addIntegralGifts(@RequestBody IntegralGiftsBean integralGiftsBean) {

        try {
            //有id修改,无id新增
            if(integralGiftsBean.getId()==null) {

                int count = integralGiftsService.addIntegralGifts(integralGiftsBean);
                if (count > 0) {
                    return CommonResult.success(count,"添加成功");
                }
                return CommonResult.failed("添加失败");
            }
            else{

                int count = integralGiftsService.updateIntegralGiftsById(integralGiftsBean);
                if (count > 0) {
                    return CommonResult.success(count,"修改成功");
                }
                return CommonResult.failed("修改失败");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return CommonResult.failed();
        }
    }



    /**
     * 回显积分好礼
     * @param 积分礼品 id
     * @return
     */
    @ApiOperation("回显积分好礼")
    @RequestMapping(value = "/findIntegralGiftsInfoById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntegralGiftsBean> findIntegralGiftsInfoById(@RequestParam Long id){
        IntegralGiftsBean integralGiftsBean = integralGiftsService.findIntegralGiftsInfoById(id);
        return CommonResult.success(integralGiftsBean);
    }

    /**
     * 删除积分好礼
     * @param id
     * @return
     */
    @ApiOperation("删除积分好礼")
    @RequestMapping(value = "/deleteIntegralGiftsById",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteIntegralGiftsById(@RequestParam Long id){
        int count = integralGiftsService.deleteIntegralGiftsById(id);
        if (count > 0) {
            return CommonResult.success(count,"删除成功");
        }
        return CommonResult.failed("删除失败");
    }

    /**
     * 根据礼品id查询礼品规格
     * @param id
     * @return
     */
    @ApiOperation("兑换查询产品规格")
    @RequestMapping(value = "/selectIntegralGiftsSpeById",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<IntegralGiftsSpeBean>> queryIntegralGiftsSpeById(@RequestParam Long id) {
        List<IntegralGiftsSpeBean> integralGiftsSpeList = integralGiftsService.queryIntegralGiftsSpeById(id);
        return CommonResult.success(integralGiftsSpeList);
    }

}
