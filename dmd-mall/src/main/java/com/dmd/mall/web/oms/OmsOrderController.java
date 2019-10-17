package com.dmd.mall.web.oms;


import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseController;
import com.dmd.mall.model.dto.OrderPageQueryDto;
import com.dmd.mall.model.dto.OrderParamDto;
import com.dmd.mall.model.vo.OrderCreateResultVo;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.model.vo.OrderVo;
import com.dmd.mall.service.OmsOrderService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @PostMapping("/createCourseOrIntegralOrder")
    @ApiOperation(httpMethod = "POST", value = "创建课程或积分订单")
    @ApiImplicitParam(name ="orderParamDto", value = "创建订单需要的参数", dataType = "OrderParamDto")
    public Wrapper<OrderCreateResultVo> createCourseOrIntegralOrder(@RequestBody OrderParamDto orderParamDto) {
        OrderCreateResultVo orderVo = omsOrderService.createCourseOrIntegralOrder(getLoginAuthDto(), orderParamDto);
        return WrapMapper.ok(orderVo);
    }

    @GetMapping("cancelOrderDoc/{orderSn}")
    @ApiOperation(httpMethod = "GET", value = "根据订单编号取消订单")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "path")
    public Wrapper cancelOrderDoc(@PathVariable String orderSn) {
        logger.info("cancelOrderDoc - 取消订单. orderSn={}", orderSn);
        LoginAuthDto loginAuthDto = getLoginAuthDto();
        logger.info("操作人信息. loginAuthDto={}", loginAuthDto);
        int result = omsOrderService.cancelOrderDoc(loginAuthDto, orderSn);
        return handleResult(result);
    }

    @GetMapping("queryUserOrderDetailList/{orderSn}")
    @ApiOperation(httpMethod = "GET", value = "查询登陆人订单详情")
    @ApiImplicitParam(name ="orderSn", value = "订单编号", dataType = "Long", paramType = "path")
    public Wrapper queryUserOrderDetailList(@PathVariable String orderSn) {
        logger.info("queryUserOrderDetailList - 查询用户订单明细. orderSn={}", orderSn);
        Long userId = getLoginAuthDto().getUserId();
        logger.info("操作人信息. userId={}", userId);
        OrderVo orderVo = omsOrderService.getOrderDetail(userId, orderSn);
        return WrapMapper.ok(orderVo);
    }

    @PostMapping("queryUserOrderListWithPage")
    @ApiOperation(httpMethod = "POST", value = "查询用户全部订单列表")
    @ApiImplicitParam(name ="baseQuery", value = "分页数据", dataType = "BaseQuery")
    public Wrapper queryUserOrderListWithPage(@RequestBody BaseQuery baseQuery) {
        logger.info("queryUserOrderListWithPage - 查询用户订单集合. baseQuery={}", baseQuery);
        Long userId = getLoginAuthDto().getUserId();
        logger.info("操作人信息. userId={}", userId);
        PageInfo pageInfo = omsOrderService.queryUserOrderListWithPage(userId, baseQuery);
        return WrapMapper.ok(pageInfo);
    }

    @PostMapping("queryOrderListWithPage")
    @ApiOperation(httpMethod = "POST", value = "根据订单的状态查询用户订单列表")
    @ApiImplicitParam(name ="orderPageQuery", value = "查询需要的数据", dataType = "OrderPageQueryDto")
    public Wrapper queryOrderListWithPage(@RequestBody OrderPageQueryDto orderPageQuery) {
        logger.info("queryOrderListWithPage - 查询订单集合. orderPageQuery={}", orderPageQuery);
        PageInfo pageInfo = omsOrderService.queryOrderListWithPage(orderPageQuery);
        return WrapMapper.ok(pageInfo);
    }
}

