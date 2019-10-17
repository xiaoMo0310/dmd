package com.dmd.mall.web.oms;


import com.dmd.core.support.BaseController;
import com.dmd.mall.model.vo.OrderCreateVo;
import com.dmd.mall.model.vo.OrderVo;
import com.dmd.mall.service.OmsOrderService;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
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
    @ApiOperation(httpMethod = "POST", value = "创建订单")
    @ApiImplicitParam(name ="orderCreateVo", value = "创建订单需要的参数", dataType = "OrderCreateVo")
    public Wrapper<OrderVo> createOrder(@RequestBody OrderCreateVo orderCreateVo) {
        OrderVo orderVo = omsOrderService.createOrder(getLoginAuthDto(), orderCreateVo);
        return WrapMapper.ok(orderVo);
    }
}

