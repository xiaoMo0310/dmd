package com.dmd.mall.service;

import java.util.Map;

public interface WXservice {


    //统一下单
    Map dounifiedOrder(String user_id, String total_fee, String oid) throws Exception;

    //回调
    String payBack(String notifyData);

    //支付接口
    Map recharge(String user_id, String total_fee) throws Exception;

    //支付回调
    String wxPayNotifyApp(String notifyData);
}
