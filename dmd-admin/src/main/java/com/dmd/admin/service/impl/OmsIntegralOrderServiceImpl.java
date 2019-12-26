package com.dmd.admin.service.impl;

import com.dmd.admin.mapper.OmsIntegralOrderMapper;
import com.dmd.admin.mapper.OmsOrderOperateHistoryMapper;
import com.dmd.admin.model.domain.OmsIntegralOrder;
import com.dmd.admin.model.domain.OmsOrderOperateHistory;
import com.dmd.admin.model.dto.OmsOrderDeliveryParam;
import com.dmd.admin.model.dto.OmsOrderQueryParam;
import com.dmd.admin.model.dto.OmsReceiverInfoParam;
import com.dmd.admin.model.vo.IntegralOrderDetailVo;
import com.dmd.admin.service.OmsIntegralOrderService;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 积分好礼订单表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OmsIntegralOrderServiceImpl extends BaseService<OmsIntegralOrder> implements OmsIntegralOrderService {

    @Autowired
    private OmsIntegralOrderMapper omsIntegralOrderMapper;
    @Autowired
    private OmsOrderOperateHistoryMapper orderOperateHistoryMapper;

    @Override
    public List<OmsIntegralOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return omsIntegralOrderMapper.getList(queryParam);
    }

    @Override
    public int delivery(List<OmsOrderDeliveryParam> deliveryParamList) {
        //批量发货
        int count = omsIntegralOrderMapper.delivery(deliveryParamList);
        //添加操作记录
        List<OmsOrderOperateHistory> operateHistoryList = deliveryParamList.stream()
                .map(omsOrderDeliveryParam -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(omsOrderDeliveryParam.getOrderId());
                    history.setCreateTime(new Date());
                    history.setOperateMan("后台管理员");
                    history.setOrderStatus(2);
                    history.setNote("完成发货");
                    history.setOrderType(2);
                    return history;
                }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(operateHistoryList);
        return count;
    }

    @Override
    public int close(List<Long> ids, String note) {
        OmsIntegralOrder record = new OmsIntegralOrder();
        record.setStatus(4);
        Example example = new Example(OmsIntegralOrder.class);
        example.createCriteria().andEqualTo("delete_status",0).andIn("id", ids);
        int count = omsIntegralOrderMapper.updateByExampleSelective(record, example);
        List<OmsOrderOperateHistory> historyList = ids.stream().map(orderId -> {
            OmsOrderOperateHistory history = new OmsOrderOperateHistory();
            history.setOrderId(orderId);
            history.setCreateTime(new Date());
            history.setOperateMan("后台管理员");
            history.setOrderStatus(4);
            history.setOrderType(2);
            history.setNote("订单关闭:"+note);
            return history;
        }).collect(Collectors.toList());
        orderOperateHistoryMapper.insertList(historyList);
        return count;
    }

    @Override
    public int delete(List<Long> ids) {
        OmsIntegralOrder record = new OmsIntegralOrder();
        record.setDeleteStatus(1);
        Example example = new Example(OmsIntegralOrder.class);
        example.createCriteria().andEqualTo("delete_status",0).andIn("id", ids);
        return omsIntegralOrderMapper.updateByExampleSelective(record, example);
    }

    @Override
    public IntegralOrderDetailVo detail(Long id) {
        IntegralOrderDetailVo detail = omsIntegralOrderMapper.getDetail(id);
        return detail;
    }

    @Override
    public int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam) {
        OmsIntegralOrder order = new OmsIntegralOrder();
        order.setId(receiverInfoParam.getOrderId());
        order.setReceiverName(receiverInfoParam.getReceiverName());
        order.setReceiverMobileNo(receiverInfoParam.getReceiverMobileNo());
        order.setProvinceName(receiverInfoParam.getProvinceName());
        order.setCityName(receiverInfoParam.getCityName());
        order.setDistrictName(receiverInfoParam.getDistrictName());
        order.setReceiverZipCode(receiverInfoParam.getReceiverZipCode());
        order.setDetailAddress(receiverInfoParam.getDetailAddress());
        order.setUpdateTime(new Date());
        int count = omsIntegralOrderMapper.updateByPrimaryKeySelective(order);
        //插入操作记录
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(receiverInfoParam.getOrderId());
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(receiverInfoParam.getStatus());
        history.setNote("修改收货人信息");
        history.setOrderType(2);
        orderOperateHistoryMapper.insert(history);
        return count;
    }

    @Override
    public int updateNote(Long id, String note, Integer status) {
        OmsIntegralOrder order = new OmsIntegralOrder();
        order.setId(id);
        order.setRemark(note);
        order.setUpdateTime(new Date());
        int count = omsIntegralOrderMapper.updateByPrimaryKeySelective(order);
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(id);
        history.setCreateTime(new Date());
        history.setOperateMan("后台管理员");
        history.setOrderStatus(status);
        history.setNote("修改备注信息："+note);
        history.setOrderType(2);
        orderOperateHistoryMapper.insert(history);
        return count;
    }
}
