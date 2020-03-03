package com.dmd.mall.web.cms;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.dmd.base.result.CommonResult;
import com.dmd.mall.config.AlipayConfigs;
import com.dmd.mall.service.impl.ALiserviceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "AlipayController", description = "支付宝充值/支付/提现")
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    ALiserviceImpl aLiservice;

    /**
     * 支付
     * @param total_fee 价格
     * @param oid 订单id
     * @return
     * @throws Exception
     */
    @ApiOperation("支付宝支付")
    @PostMapping("/apppay")
    public CommonResult<Map> pay(@RequestParam(value = "total_fee") String total_fee, @RequestParam(value = "oid") String oid) throws Exception {
        return CommonResult.success(aLiservice.pay(total_fee,oid));
    }

    /**
     * 回调
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/alipayNotify")
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response) {
        aLiservice.paymentCallback(request,response);
    }


    /**
     * 充值
     * @param total_fee 价格
     * @return
     * @throws Exception
     */
    @ApiOperation("支付宝充值")
    @PostMapping("/recharge")
    public CommonResult<Map> pay(@RequestParam(value = "total_fee") String total_fee) throws Exception {
        Map recharge = aLiservice.recharge(total_fee);
        return CommonResult.success(recharge);
    }

    /**
     * 回调
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/alipayNotifyApp")
    public void alipayNotifyApp(HttpServletRequest request, HttpServletResponse response) {
        aLiservice.alipayNotifyApp(request,response);
    }


    /**
     *
     * 说明：单笔转账到支付宝账户
     * @param out_biz_no  商户转账唯一订单号
     * @param payee_type 收款方账户类型  (1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。)
     * @param payee_account 收款方账户
     * @param amount 转账金额
     * @param payer_show_name 付款方姓名
     * @param payee_real_name 收款方真实姓名
     * @param remark 转账备注
     * @author ChenYanbing
     * @time：2018年12月5日 上午10:14:35
     */
    @ApiOperation("支付宝提现")
    @RequestMapping("/transferAccounts")
    public CommonResult<String> transferAccounts(String out_biz_no,String payee_type,String payee_account,String amount,String payer_show_name,String payee_real_name,String remark) {

        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfigs.URL, AlipayConfigs.APPID,
                AlipayConfigs.RSA_PRIVATE_KEY, AlipayConfigs.FORMAT, AlipayConfigs.CHARSET, AlipayConfigs.ALIPAY_PUBLIC_KEY,
                AlipayConfigs.SIGNTYPE);
        AlipayFundTransToaccountTransferRequest  transferAccounts_request = new AlipayFundTransToaccountTransferRequest();

        String index = "";
        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
        model.setOutBizNo(out_biz_no);
        model.setPayeeType(payee_type);
        model.setPayeeAccount(payee_account);
        model.setAmount(amount);
        model.setPayerShowName(payer_show_name);
        model.setPayeeRealName(payee_real_name);
        model.setRemark(remark);
        transferAccounts_request.setBizModel(model);
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(transferAccounts_request);
            if(response.isSuccess()){
                System.out.println(response.getBody());
                index = "调用成功";
                //1.获取用户id
                //登陆信息
                //LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
                //登录人id
                //Long userId = loginAuthDto.getUserId();
                //登陆角色
                //String userTypes = loginAuthDto.getUserType();
                //2.判断登录用户是教练还是用户
                //3.流水账表新增信息：提现价格，提现时间，"支付宝提现"，"交易类型2(支出)"，用户类型
            } else {
                System.out.println("调用失败");
                index = "调用失败";

            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return CommonResult.success(index);

    }

}
