package com.dmd.mall.web.oms;


import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.service.OmsCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 *
 * @author 王海成
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/omsCart")
@Api(tags = "OmsCartController", description = "购物车相关接口", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsCartController extends BaseController {

    @Autowired
    private OmsCartService omsCartService;

    @ApiOperation("根据用户id查找购物车")
    @RequestMapping(value = "/findOmsCart", method = RequestMethod.POST)
    public CommonResult findOmsCart(Integer memberId){
        return CommonResult.success(omsCartService.findOmsCart(memberId));
    }
    @ApiOperation("根据id查找购物车")
    @RequestMapping(value = "/findOmsCartById", method = RequestMethod.POST)
    public CommonResult findOmsCartById(@RequestBody List<Integer> ids){
        return CommonResult.success(omsCartService.findOmsCartById(ids));
    }

    @ApiOperation("添加购物车")
    @RequestMapping(value = "/addOmsCart", method = RequestMethod.POST)
    public CommonResult addOmsCart(@RequestBody OmsCart omsCart){
        try {
            return CommonResult.success(omsCartService.addOmsCart(omsCart));
        }catch (Exception e){
            logger.error(e.getMessage());
            if (e.getMessage().contains("MySQLIntegrityConstraintViolationException")){
                return CommonResult.failed("添加失败，违反数据库完整性约束");
            }else{
                return CommonResult.failed("添加失败，未知异常");
            }
        }
    }
    @ApiOperation("更新购物车（包括购物车商品数量和购物车是否删除）")
    @RequestMapping(value = "/updateOmsCart", method = RequestMethod.POST)
    public CommonResult updateOmsCart(String quantity, String deleteStatus, Integer id, String updateTime){
        return CommonResult.success(omsCartService.updateOmsCart(quantity,deleteStatus,id,updateTime));
    }
    @ApiOperation("提交订单前的页面")
    @RequestMapping(value = "/beforeOrder", method = RequestMethod.POST)
    public CommonResult beforeOrder(Integer shopId,Integer memberId){
        return CommonResult.success("data");
    }

}

