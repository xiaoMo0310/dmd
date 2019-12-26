package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.OmsOrderMapper;
import com.dmd.admin.mapper.OmsOrderOperateHistoryMapper;
import com.dmd.admin.model.domain.OmsOrder;
import com.dmd.admin.model.domain.OmsOrderOperateHistory;
import com.dmd.admin.model.dto.*;
import com.dmd.admin.model.vo.SetTimeoutVo;
import com.dmd.admin.service.OmsOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单管理Service实现类
 * Created by macro on 2018/10/11.
 */
@Service
public class OmsOrderServiceImpl implements OmsOrderService {
    @Autowired
    private OmsOrderMapper orderMapper;
    @Autowired
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;

    @Override
    public List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return orderMapper.getList(queryParam);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = orderMapper.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    history.setOrderType(1);
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsOrder record = new OmsOrder();
        record.setStatus(4);
        Example example = new Example(OmsOrder.class);
        example.createCriteria().andEqualTo("delete_status",0).andIn("id", ids);
        int count = orderMapper.updateByExampleSelective(record, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setNote("订单关闭:"+note);
            history.setOrderType(1);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsOrder record = new OmsOrder();
        record.setDeleteStatus(1);
        Example example = new Example(OmsOrder.class);
        example.createCriteria().andEqualTo("delete_status",0).andIn("id", ids);
        return orderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public OmsOrderDetail detail(Long id) {
        OmsOrderDetail detail = orderMapper.getDetail(id);
        return detail;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverMobileNo(receiverInfoParam.getReceiverMobileNo());
        order.setProvinceName(receiverInfoParam.getProvinceName());
        order.setCityName(receiverInfoParam.getCityName());
        order.setDistrictName(receiverInfoParam.getDistrictName());
        order.setReceiverZipCode(receiverInfoParam.getReceiverZipCode());
        order.setDetailAddress(receiverInfoParam.getDetailAddress());
        order.setUpdateTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        history.setOrderType(1);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam) {
        OmsOrder order = new OmsOrder();
        order.setId(moneyInfoParam.getOrderId());
        order.setFreightAmount(moneyInfoParam.getFreightAmount());
        order.setDiscountAmount(moneyInfoParam.getDiscountAmount());
        order.setUpdateTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(moneyInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(moneyInfoParam.getStatus());
        history.setNote("修改费用信息");
        history.setOrderType(1);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsOrder order = new OmsOrder();
        order.setId(id);
        order.setRemark(note);
        order.setUpdateTime(new Date());
        int count = orderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        history.setOrderType(1);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public OmsOrder selectByOrderSn(String orderSn) {
        return orderMapper.selectByOrderSn(orderSn);
    }

    @Override
    public Integer queryOrderNumtoDay() {
        return orderMapper.queryOrderNumtoDay();
    }

    @Override
    public BigDecimal queryOrderMoneyToDay() {
        return orderMapper.queryOrderMoneyToDay();
    }

    @Override
    public BigDecimal queryOrderMoneyToYesterday() {
        return orderMapper.queryOrderMoneyToYesterday();
    }

    @Override
    public BigDecimal queryOrderMoneyToSeven() {
        return orderMapper.queryOrderMoneyToSeven();
    }

    @Override
    public Integer querySubstitutePayment() {
        return orderMapper.querySubstitutePayment();
    }

    @Override
    public Integer queryCompleted() {
        return orderMapper.queryCompleted();
    }

    @Override
    public Integer queryReceiptConfirmed() {
        return orderMapper.queryReceiptConfirmed();
    }

    @Override
    public Integer queryShipped() {
        return orderMapper.queryShipped();
    }

    @Override
    public Integer queryAfterSale() {
        return orderMapper.queryAfterSale();
    }

    @Override
    public Integer queryConfirmReceipt() {
        return orderMapper.queryConfirmReceipt();
    }

    @Override
    public Integer queryOrderMonthNum() {
        return orderMapper.queryOrderMonthNum();
    }

    @Override
    public Integer queryOrderPercentage() {
        //上月订单量
        return orderMapper.queryOrderLastMonthNum();
    }

    @Override
    public Integer queryOrderWeek() {
        return orderMapper.queryOrderWeek();
    }

    @Override
    public Integer queryOrderWeekPercentage() {
        //上周订单量
        return orderMapper.queryOrderLastWeek();
    }

    @Override
    public BigDecimal querySalesMonth() {
        return orderMapper.querySalesMonth();
    }

    @Override
    public BigDecimal querySalesLastMonth() {
        return orderMapper.querySalesLastMonth();
    }

    @Override
    public BigDecimal querySalesWeek() {
        return orderMapper.querySalesWeek();
    }

    @Override
    public BigDecimal querySalesLastWeek() {
        return orderMapper.querySalesLastWeek();
    }

    @Override
    public List<SetTimeoutVo> setTimeout(SetTimeoutVo setTimeoutVo) {
        List<SetTimeoutVo> setTimeoutVos = orderMapper.setTimeout(setTimeoutVo);
        /*for (int i = 0; i < setTimeoutVos.size(); i++) {
            String date = setTimeoutVos.get(i).getDate();
            String dates = "";
            dates = date + " 00:00:00";
            setTimeoutVos.get(i).setDate(dates);
        }*/
        return setTimeoutVos;
    }
}
