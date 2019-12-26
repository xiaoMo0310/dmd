package com.dmd.admin.web;


import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.OmsIntegralOrder;
import com.dmd.admin.model.dto.OmsOrderDeliveryParam;
import com.dmd.admin.model.dto.OmsOrderQueryParam;
import com.dmd.admin.model.dto.OmsReceiverInfoParam;
import com.dmd.admin.model.vo.IntegralOrderDetailVo;
import com.dmd.admin.service.OmsIntegralOrderService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import com.dmd.core.support.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 积分好礼订单表 前端控制器
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/integralOrder")
@Api(tags = "OmsIntegralOrderController", description = "积分订单中心", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OmsIntegralOrderController extends BaseController {

    @Autowired
    private OmsIntegralOrderService omsIntegralOrderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsIntegralOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsIntegralOrder> orderList = omsIntegralOrderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @OperationLog(content = "积分订单批量发货")
    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = omsIntegralOrderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "积分订单批量关闭订单")
    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String remark) {
        int count = omsIntegralOrderService.close(ids, remark);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "积分订单批量删除订单")
    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = omsIntegralOrderService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<IntegralOrderDetailVo> detail(@PathVariable Long id) {
        IntegralOrderDetailVo integralOrderDetailVo = omsIntegralOrderService.detail(id);
        return CommonResult.success(integralOrderDetailVo);
    }

    @OperationLog(content = "积分订单修改收货人信息")
    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = omsIntegralOrderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "积分订单备注")
    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("remark") String remark,
                                   @RequestParam("status") Integer status) {
        int count = omsIntegralOrderService.updateNote(id, remark, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

