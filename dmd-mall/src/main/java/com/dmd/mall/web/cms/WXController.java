package com.dmd.mall.web.cms;

import com.dmd.base.result.CommonResult;
import com.dmd.mall.service.impl.WXserviceImpl;
import com.dmd.mall.util.AuthUtil;
import com.dmd.mall.util.CertHttpUtil;
import com.dmd.mall.util.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@RestController
@Api(tags = "WXController", description = "微信充值/支付/提现")
@RequestMapping("/weixin")
public class WXController {

    @Autowired
    private WXserviceImpl wxPayService;

    /**
     * 微信支付
     * @param user_id
     * @param total_fee
     * @param oid
     * @return
     * @throws Exception
     */
    @ApiOperation("微信支付")
    @PostMapping("/apppay")
    public CommonResult<Map> wxAdd(@RequestParam(value = "user_id") String user_id, @RequestParam(value = "total_fee") String total_fee
            , @RequestParam(value = "oid") String oid) throws Exception {
        return CommonResult.success(wxPayService.dounifiedOrder(user_id, total_fee, oid));
    }


    /**
     * 支付异步结果通知，我们在请求预支付订单时传入的地址
     * 官方文档 ：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
     */
    /**
     * 微信支付回调
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("微信支付回调")
    @PostMapping(value = "/notify")
    public CommonResult<String> wxPayNotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("======================微信支付异步结果通知开始=================================");
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxPayService.payBack(resXml);
            return CommonResult.success(result);
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return CommonResult.failed(result);
        }
    }

    /**
     * 微信充值
     * @param user_id
     * @param total_fee
     * @return
     * @throws Exception
     */
    @ApiOperation("微信充值")
    @PostMapping("/recharge")
    public CommonResult<Map> wxAddRecharge(@RequestParam(value = "user_id") String user_id, @RequestParam(value = "total_fee") String total_fee) throws Exception {
        return CommonResult.success(wxPayService.recharge(user_id, total_fee));
    }


    /**
     * 支付异步结果通知，我们在请求预支付订单时传入的地址
     * 官方文档 ：https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
     */
    /**
     * 微信值充/支付回调
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("微信充值回调")
    @PostMapping(value = "/notifyApp")
    public CommonResult<String> wxPayNotifyApp(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("======================微信支付异步结果通知开始=================================");
        String resXml = "";
        try {
            InputStream inputStream = request.getInputStream();
            //将InputStream转换成xmlString
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            resXml = sb.toString();
            String result = wxPayService.wxPayNotifyApp(resXml);
            return CommonResult.success(result);
        } catch (Exception e) {
            System.out.println("微信手机支付失败:" + e.getMessage());
            String result = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            return CommonResult.failed(result);
        }

    }

    /**
     * @Title: transfer
     * @Description: 企业转账到零钱
     * @param:
     * @return:
     */
    @ApiOperation("微信提现功能")
    @RequestMapping("/wxDeposit")
    public CommonResult<String> transfer(HttpServletRequest request, HttpServletResponse response) {

        // 1.0 拼凑企业支付需要的参数
        String appid = AuthUtil.APPID; // 微信公众号的appid
        String mch_id = AuthUtil.MCHID; // 商户号
        String nonce_str = WXPayUtil.generateNonceStr(); // 生成随机数
        String partner_trade_no = WXPayUtil.generateNonceStr(); // 生成商户订单号
        String openid = "用户的OpenId"; // 支付给用户openid
        String check_name = "NO_CHECK"; // 是否验证真实姓名呢
        String re_user_name = "KOLO"; // 收款用户姓名(非必须)
        String amount = "100"; // 企业付款金额，最少为100，单位为分
        String desc = "用户提现至微信"; // 企业付款操作说明信息。必填。
        String spbill_create_ip = AuthUtil.getRequestIp(request); // 用户的ip地址

        // 2.0 生成map集合
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("mch_appid", appid); // 微信公众号的appid
        packageParams.put("mchid", mch_id); // 商务号
        packageParams.put("nonce_str", nonce_str); // 随机生成后数字，保证安全性

        packageParams.put("partner_trade_no", partner_trade_no); // 生成商户订单号
        packageParams.put("openid", openid); // 支付给用户openid
        packageParams.put("check_name", check_name); // 是否验证真实姓名呢
        packageParams.put("re_user_name", re_user_name);// 收款用户姓名
        packageParams.put("amount", amount); // 企业付款金额，单位为分
        packageParams.put("desc", desc); // 企业付款操作说明信息。必填。
        packageParams.put("spbill_create_ip", spbill_create_ip); // 调用接口的机器Ip地址

        try {
            // 3.0 利用上面的参数，先去生成自己的签名
            String sign = WXPayUtil.generateSignature(packageParams, AuthUtil.PATERNERKEY);

            // 4.0 将签名再放回map中，它也是一个参数
            packageParams.put("sign", sign);

            // 5.0将当前的map结合转化成xml格式
            String xml = WXPayUtil.mapToXml(packageParams);

            // 6.0获取需要发送的url地址
            String wxUrl = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers"; // 获取退款的api接口


            System.out.println("发送前的xml为：" + xml);

            // 7,向微信发送请求转账请求
            String returnXml = CertHttpUtil.postData(wxUrl, xml, AuthUtil.MCHID, AuthUtil.CERTPATH);

            System.out.println("返回的returnXml为:" + returnXml);

            // 8，将微信返回的xml结果转成map格式
            Map<String, String> returnMap = WXPayUtil.xmlToMap(returnXml);

            if (returnMap.get("return_code").equals("SUCCESS")) {
                // 付款成功
                System.out.println("returnMap为:" + returnMap);
                //1.获取用户id
                //登陆信息
                //LoginAuthDto loginAuthDto = RequestUtil.getLoginUser();
                //登录人id
                //Long userId = loginAuthDto.getUserId();
                //登陆角色
                //String userTypes = loginAuthDto.getUserType();
                //2.判断登录用户是教练还是用户
                //3.流水账表新增信息：提现价格，提现时间，"微信提现"，"交易类型2(支出)"，用户类型

            }

            return CommonResult.success(returnXml);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return CommonResult.failed("error");
    }
}
