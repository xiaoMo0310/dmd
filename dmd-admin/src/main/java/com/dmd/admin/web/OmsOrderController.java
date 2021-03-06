package com.dmd.admin.web;

import com.dmd.admin.annotation.OperationLog;
import com.dmd.admin.model.domain.OmsOrder;
import com.dmd.admin.model.dto.*;
import com.dmd.admin.model.vo.SetTimeoutVo;
import com.dmd.admin.service.OmsOrderService;
import com.dmd.base.result.CommonPage;
import com.dmd.base.result.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单管理Controller
 *
 * @author macro
 * @date 2018/10/11
 */
@RestController
@Api(tags = "OmsOrderController", description = "订单管理")
@RequestMapping("/order")
public class OmsOrderController {
    @Autowired
    private OmsOrderService orderService;

    @ApiOperation("查询订单")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<OmsOrder>> list(OmsOrderQueryParam queryParam,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<OmsOrder> orderList = orderService.list(queryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(orderList));
    }

    @OperationLog(content = "批量发货")
    @ApiOperation("批量发货")
    @RequestMapping(value = "/update/delivery", method = RequestMethod.POST)
    public CommonResult delivery(@RequestBody List<OmsOrderDeliveryParam> deliveryParamList) {
        int count = orderService.delivery(deliveryParamList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "批量关闭订单")
    @ApiOperation("批量关闭订单")
    @RequestMapping(value = "/update/close", method = RequestMethod.POST)
    public CommonResult close(@RequestParam("ids") List<Long> ids, @RequestParam String remark) {
        int count = orderService.close(ids, remark);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
    @OperationLog(content = "批量删除订单")
    @ApiOperation("批量删除订单")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = orderService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取订单详情:订单信息、商品信息、操作记录")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<OmsOrderDetail> detail(@PathVariable Long id) {
        OmsOrderDetail orderDetailResult = orderService.detail(id);
        return CommonResult.success(orderDetailResult);
    }

    @OperationLog(content = "修改收货人信息")
    @ApiOperation("修改收货人信息")
    @RequestMapping(value = "/update/receiverInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsReceiverInfoParam receiverInfoParam) {
        int count = orderService.updateReceiverInfo(receiverInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "修改订单费用信息")
    @ApiOperation("修改订单费用信息")
    @RequestMapping(value = "/update/moneyInfo", method = RequestMethod.POST)
    public CommonResult updateReceiverInfo(@RequestBody OmsMoneyInfoParam moneyInfoParam) {
        int count = orderService.updateMoneyInfo(moneyInfoParam);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @OperationLog(content = "备注订单")
    @ApiOperation("备注订单")
    @RequestMapping(value = "/update/note", method = RequestMethod.POST)
    public CommonResult updateNote(@RequestParam("id") Long id,
                                   @RequestParam("remark") String remark,
                                   @RequestParam("status") Integer status) {
        int count = orderService.updateNote(id, remark, status);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    /**
     * 查询今日订单总数
     * @return
     */
    @ApiOperation("查询今日订单总数")
    @RequestMapping(value = "/queryOrderNumtoDay", method = RequestMethod.GET)
    public CommonResult<Integer> queryOrderNumtoDay() {
        Integer orderNumtoDay = orderService.queryOrderNumtoDay();
        return CommonResult.success(orderNumtoDay);
    }

    /**
     * 查询今日销售总额
     * @return
     */
    @ApiOperation("查询今日销售总额")
    @RequestMapping(value = "/queryOrderMoneyToDay", method = RequestMethod.GET)
    public CommonResult<BigDecimal> queryOrderMoneyToDay() {
        BigDecimal orderMoneyToDay = orderService.queryOrderMoneyToDay();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 查询昨日销售总额
     * @return
     */
    @ApiOperation("查询昨日销售总额")
    @RequestMapping(value = "/queryOrderMoneyToYesterday", method = RequestMethod.GET)
    public CommonResult<BigDecimal> queryOrderMoneyToYesterday() {
        BigDecimal orderMoneyToDay = orderService.queryOrderMoneyToYesterday();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 查询昨日销售总额
     * @return
     */
    @ApiOperation("近7天销售总额")
    @RequestMapping(value = "/queryOrderMoneyToSeven", method = RequestMethod.GET)
    public CommonResult<BigDecimal> queryOrderMoneyToSeven() {
        BigDecimal orderMoneyToDay = orderService.queryOrderMoneyToSeven();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 查询待付款订单
     * @return
     */
    @ApiOperation("查询待付款订单")
    @RequestMapping(value = "/querySubstitutePayment", method = RequestMethod.GET)
    public CommonResult<Integer> querySubstitutePayment() {
        Integer orderMoneyToDay = orderService.querySubstitutePayment();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 查询已完成订单
     * @return
     */
    @ApiOperation("查询已完成订单")
    @RequestMapping(value = "/queryCompleted", method = RequestMethod.GET)
    public CommonResult<Integer> queryCompleted() {
        Integer orderMoneyToDay = orderService.queryCompleted();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 待确认收货订单
     * @return
     */
    @ApiOperation("待确认收货订单")
    @RequestMapping(value = "/queryReceiptConfirmed", method = RequestMethod.GET)
    public CommonResult<Integer> queryReceiptConfirmed() {
        Integer orderMoneyToDay = orderService.queryReceiptConfirmed();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 待发货订单
     * @return
     */
    @ApiOperation("待发货订单")
    @RequestMapping(value = "/queryShipped", method = RequestMethod.GET)
    public CommonResult<Integer> queryShipped() {
        Integer orderMoneyToDay = orderService.queryShipped();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 售后申请
     * @return
     */
    @ApiOperation("售后申请")
    @RequestMapping(value = "/queryAfterSale", method = RequestMethod.GET)
    public CommonResult<Integer> queryAfterSale() {
        Integer orderMoneyToDay = orderService.queryAfterSale();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 已确认收货订单
     * @return
     */
    @ApiOperation("已确认收货订单")
    @RequestMapping(value = "/queryConfirmReceipt", method = RequestMethod.GET)
    public CommonResult<Integer> queryConfirmReceipt() {
        Integer orderMoneyToDay = orderService.queryConfirmReceipt();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 本月订单总数
     * @return
     */
    @ApiOperation("本月订单总数")
    @RequestMapping(value = "/queryOrderMonthNum", method = RequestMethod.GET)
    public CommonResult<Integer> queryOrderMonthNum() {
        Integer orderMoneyToDay = orderService.queryOrderMonthNum();
        return CommonResult.success(orderMoneyToDay);
    }


    /**
     * 上月订单总数
     * @return
     */
    @ApiOperation("上月订单总数")
    @RequestMapping(value = "/queryOrderPercentage", method = RequestMethod.GET)
    public CommonResult<Integer> queryOrderPercentage() {
        Integer orderMoneyToDay = orderService.queryOrderPercentage();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 本周订单总数
     * @return
     */
    @ApiOperation("本周订单总数")
    @RequestMapping(value = "/queryOrderWeek", method = RequestMethod.GET)
    public CommonResult<Integer> queryOrderWeek() {
        Integer orderMoneyToDay = orderService.queryOrderWeek();
        return CommonResult.success(orderMoneyToDay);
    }


    /**
     * 上周订单量
     * @return
     */
    @ApiOperation("上周订单总数")
    @RequestMapping(value = "/queryOrderWeekPercentage", method = RequestMethod.GET)
    public CommonResult<Integer> queryOrderWeekPercentage() {
        Integer orderMoneyToDay = orderService.queryOrderWeekPercentage();
        return CommonResult.success(orderMoneyToDay);
    }


    /**
     * 本月销售总额
     * @return
     */
    @ApiOperation("本月销售总额")
    @RequestMapping(value = "/querySalesMonth", method = RequestMethod.GET)
    public CommonResult<BigDecimal> querySalesMonth() {
        BigDecimal orderMoneyToDay = orderService.querySalesMonth();
        return CommonResult.success(orderMoneyToDay);
    }


    /**
     * 上月销售总额
     * @return
     */
    @ApiOperation("上月销售总额")
    @RequestMapping(value = "/querySalesLastMonth", method = RequestMethod.GET)
    public CommonResult<BigDecimal> querySalesLastMonth() {
        BigDecimal orderMoneyToDay = orderService.querySalesLastMonth();
        return CommonResult.success(orderMoneyToDay);
    }
    /**
     * 本周销售总额
     * @return
     */
    @ApiOperation("本周销售总额")
    @RequestMapping(value = "/querySalesWeek", method = RequestMethod.GET)
    public CommonResult<BigDecimal> querySalesWeek() {
        BigDecimal orderMoneyToDay = orderService.querySalesWeek();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 上周销售总额
     * @return
     */
    @ApiOperation("上周销售总额")
    @RequestMapping(value = "/querySalesLastWeek", method = RequestMethod.GET)
    public CommonResult<BigDecimal> querySalesLastWeek() {
        BigDecimal orderMoneyToDay = orderService.querySalesLastWeek();
        return CommonResult.success(orderMoneyToDay);
    }

    /**
     * 首页订单线型图
     * @return
     */
    @ApiOperation("首页订单线型图")
    @RequestMapping(value = "/setTimeout", method = RequestMethod.GET)
    public CommonResult<List<SetTimeoutVo>> setTimeout(SetTimeoutVo setTimeoutVo) {
        List<SetTimeoutVo> setTimeout = orderService.setTimeout(setTimeoutVo);
        return CommonResult.success(setTimeout);
    }

}
