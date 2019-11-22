package com.dmd.mall.web.oms;


import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.domain.OmsCart;
import com.dmd.mall.service.OmsCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    @ApiOperation("根据用户id查找购物车，显示购物车时使用此接口")
    @RequestMapping(value = "/findOmsCart", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult findOmsCart(Long memberId){
        return CommonResult.success(omsCartService.findOmsCart(memberId));
    }
//    @ApiOperation("根据id查找购物车")
//    @RequestMapping(value = "/findOmsCartById", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResult findOmsCartById(@RequestBody List<Long> ids){
//        return CommonResult.success(omsCartService.findOmsCartById(ids));
//    }

    @ApiOperation("添加购物车")
    @RequestMapping(value = "/addOmsCart", method = RequestMethod.POST)
    @ResponseBody
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
    @ResponseBody
    public CommonResult updateOmsCart(@ApiParam(name="quantity",value = "购买数量，修改购物车购买数量时传入此参数，拼接在url里") String quantity, @ApiParam(name="deleteStatus",value = "删除状态（0：删除，1：删除），删除购物车时传入此参数，拼接在url里")String deleteStatus, @ApiParam(name="ids",value = "需要修改或者删除的购物id的集合，请求体里以json格式传入") List<Long> ids, String updateTime){
        return CommonResult.success(omsCartService.updateOmsCart(quantity,deleteStatus,ids,updateTime));
    }
    @ApiOperation("提交订单前的页面")
    @RequestMapping(value = "/beforeSubmitOrder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult beforeSubmitOrder(@ApiParam(name="ids",value="购物车id的集合，这个参数需要在请求体里以json格式传入")@RequestBody List<Long> ids,@ApiParam(name="productId",value="商品ID，在立即购买时需要传递此参数，这个参数需要拼接在url里") Long productId){
        Map<String,Object> map=new TreeMap<>();
        map=omsCartService.beforeSubmitOrder(ids, getLoginAuthDto(),productId);
        return CommonResult.success(map);
    }

}

