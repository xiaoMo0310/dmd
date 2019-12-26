package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.model.vo.OrderCreateResultVo;
import com.dmd.mall.service.OmsIntegralOrderService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 积分好礼订单表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/omsIntegralOrder")
@Api(tags = "OmsIntegralOrderController", description = "XXXX", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsIntegralOrderController extends BaseController {

    @Autowired
    private OmsIntegralOrderService omsIntegralOrderService;

    @PostMapping("/IntegralOrder/create")
    @ApiOperation(httpMethod = "POST", value = "创建积分好礼订单")
    @ApiImplicitParam(name ="orderParamDto", value = "创建订单需要的参数", dataType = "OrderParamDto")
    public Wrapper<OrderCreateResultVo> createIntegralProductOrder(@RequestBody OrderParamDto orderParamDto) {
        omsIntegralOrderService.createIntegralOrder(getLoginAuthDto(), orderParamDto);
        return WrapMapper.ok();
    }

    @GetMapping("/integralOrderList/findByStatus")
    @ApiOperation(httpMethod = "GET", value = "根据订单的状态查询用户积分订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name ="status", value = "订单状态：2->进行中；3->已完成", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name ="pageNum", value = "页数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name ="pageSize", value = "每页显示条数", dataType = "int", paramType = "query")})
    public Wrapper queryIntegralOrderListWithPage(@RequestParam("status") Integer status,
                                                  @RequestParam("pageNum") Integer pageNum,
                                                  @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = omsIntegralOrderService.queryIntegralOrderListWithPage(getLoginAuthDto(), pageNum, pageSize, status);
        return WrapMapper.ok(pageInfo);
    }

    @GetMapping("/orderDetail/userIntegral/query")
    @ApiOperation(httpMethod = "GET", value = "查询用户积分订单详情")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "query")
    public Wrapper queryUserIntegralOrderDetail(@RequestParam("orderSn") String orderSn) {
        CourseOrderDetailVo courseOrderDetailVo = omsIntegralOrderService.getUserIntegralOrderDetail(getLoginAuthDto(), orderSn);
        return WrapMapper.ok(courseOrderDetailVo);
    }

    @GetMapping("/orderDetail/sellerIntegral/query")
    @ApiOperation(httpMethod = "GET", value = "查询卖家积分订单详情")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "query")
    public Wrapper querySellerIntegralOrderDetail(@RequestParam("orderSn") String orderSn) {
        CourseOrderDetailVo courseOrderDetailVo = omsIntegralOrderService.getSellerIntegralOrderDetail(getLoginAuthDto(), orderSn);
        return WrapMapper.ok(courseOrderDetailVo);
    }

}

