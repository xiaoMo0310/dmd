package com.dmd.mall.web.oms;


import com.alibaba.fastjson.JSONObject;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.dto.PmsCourseOrderDto;
import com.dmd.mall.model.vo.CourseOrderDetailVo;
import com.dmd.mall.model.vo.OrderCreateResultVo;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.service.OmsOrderService;
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
 * 订单表 前端控制器
 * </p>
 * @author YangAnsheng
 * @since 2019-10-14
 */
@RestController
@RequestMapping("/oms")
@Api(tags = "OmsOrderController", description = "订单中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsOrderController extends BaseController {

    @Autowired
    private OmsOrderService omsOrderService;

    @PostMapping("/createOrder")
    @ApiOperation(httpMethod = "POST", value = "创建有购物车信息的订单")
    @ApiImplicitParam(name ="orderCreateVo", value = "创建订单需要的参数", dataType = "OrderCreateVo")
    public Wrapper<OrderCreateResultVo> createOrder(@RequestBody OrderCreateVo orderCreateVo) {
        OrderCreateResultVo orderVo = omsOrderService.createOrder(getLoginAuthDto(), orderCreateVo);
        return WrapMapper.ok(orderVo);
    }

    @PostMapping("/IntegralOrder/create")
    @ApiOperation(httpMethod = "POST", value = "创建积分好礼订单")
    @ApiImplicitParam(name ="orderParamDto", value = "创建订单需要的参数", dataType = "OrderParamDto")
    public Wrapper<OrderCreateResultVo> createIntegralProductOrder(@RequestBody OrderParamDto orderParamDto) {
        omsOrderService.createIntegralOrder(getLoginAuthDto(), orderParamDto);
        return WrapMapper.ok();
    }

    @PostMapping("/courseProductOrder/create")
    @ApiOperation(httpMethod = "POST", value = "创建潜水学证订单")
    @ApiImplicitParam(name ="courseProductDto", value = "创建订单需要的参数", dataType = "PmsCourseOrderDto", paramType = "body")
    public Wrapper createCourseProductOrder(@RequestBody PmsCourseOrderDto courseProductDto) {
        JSONObject jsonObject = omsOrderService.createCourseProductOrder(getLoginAuthDto(), courseProductDto);
        return WrapMapper.ok(jsonObject);
    }

    @GetMapping("cancelOrderDoc")
    @ApiOperation(httpMethod = "GET", value = "根据订单编号取消订单")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "path")
    public Wrapper cancelOrderDoc(@RequestParam("orderSn") String orderSn) {
        logger.info("cancelOrderDoc - 取消订单. orderSn={}", orderSn);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        logger.info("操作人信息. loginAuthDto={}", loginAuthDto);
        int result = omsOrderService.cancelOrderDoc(loginAuthDto, orderSn);
        return handleResult(result);
    }

    @GetMapping("userOrderDetail/query")
    @ApiOperation(httpMethod = "GET", value = "查询登陆人订单详情")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "query")
    public Wrapper queryUserOrderDetail(@RequestParam("orderSn") String orderSn) {
        CourseOrderDetailVo courseOrderDetailVo = omsOrderService.getUserOrderDetail(getLoginAuthDto(), orderSn);
        return WrapMapper.ok(courseOrderDetailVo);
    }

    @GetMapping("sellerOrderDetail/query")
    @ApiOperation(httpMethod = "GET", value = "查询卖家订单详情")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "query")
    public Wrapper querySellerOrderDetail(@RequestParam("orderSn") String orderSn) {
        CourseOrderDetailVo courseOrderDetailVo = omsOrderService.getSellerOrderDetail(getLoginAuthDto(), orderSn);
        return WrapMapper.ok(courseOrderDetailVo);
    }

    @GetMapping("userOrderList/findByStatus")
    @ApiOperation(httpMethod = "GET", value = "根据订单的状态查询用户订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name ="status", value = "订单状态：0->待付款；1->已付款；2->进行中；3->已完成；4->已关闭；5->售后 6:取消", dataType = "int", paramType = "query"),
                        @ApiImplicitParam(name ="pageNum", value = "页数", dataType = "int", paramType = "query"),
                        @ApiImplicitParam(name ="pageSize", value = "每页显示条数", dataType = "int", paramType = "query")})

    public Wrapper queryUserOrderListWithPage(@RequestParam("status") Integer status,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = omsOrderService.queryUserOrderList(getLoginAuthDto(), pageNum, pageSize, status);
        return WrapMapper.ok(pageInfo);
    }

    @GetMapping("sellerOrderList/findByStatus")
    @ApiOperation(httpMethod = "GET", value = "根据订单的状态查询卖家订单列表")
    @ApiImplicitParams({@ApiImplicitParam(name ="status", value = "订单状态：0->待付款；1->已付款；2->进行中；3->已完成；4->已关闭；5->售后 6:取消", dataType = "int", paramType = "query"),
                        @ApiImplicitParam(name ="pageNum", value = "页数", dataType = "int", paramType = "query"),
                        @ApiImplicitParam(name ="pageSize", value = "每页显示条数", dataType = "int", paramType = "query")})

    public Wrapper querySellerOrderListWithPage(@RequestParam("status") Integer status,
                                              @RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = omsOrderService.querySellerOrderListWithPage(getLoginAuthDto(), pageNum, pageSize, status);
        return WrapMapper.ok(pageInfo);
    }
}

