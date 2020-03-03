package com.dmd.mall.config;

import java.io.FileWriter;
import java.io.IOException;
public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public  String app_id = "";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public  String merchant_private_key = "";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public  String alipay_public_key = "";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public  String notify_url = "";//回调地址
    //充值回调地址
    /**
     *
     */
    public  String deposit_url = "";//回调地址
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    // public  String return_url = "";

    // 签名方式
    public  String signType = "RSA2";

    // 字符编码格式
    public  String charset = "utf-8";

    // 支付宝请求地址
    public  String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public  String log_path = "C:\\";

    //支付宝仅支持JSON格式
    public  String format="JSON";


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMerchant_private_key() {
        return merchant_private_key;
    }

    public void setMerchant_private_key(String merchant_private_key) {
        this.merchant_private_key = merchant_private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

//    public String getReturn_url() {
//        return return_url;
//    }
//
//    public void setReturn_url(String return_url) {
//        this.return_url = return_url;
//    }


    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getLog_path() {
        return log_path;
    }

    public void setLog_path(String log_path) {
        this.log_path = log_path;
    }

    public String getFormat() {
        return format;
    }

    public String getDeposit_url() {
        return deposit_url;
    }

    public void setDeposit_url(String deposit_url) {
        this.deposit_url = deposit_url;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public  void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
