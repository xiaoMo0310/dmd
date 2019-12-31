package com.dmd.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.Data;
import org.json.JSONException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
@Data
@Component
@ConfigurationProperties(prefix = "dmd.tencent.sms")
public class SendUtil {

    private Integer appId;
    private String appKey;
    private String smsSign;
    private Integer templateIdA;
    private Integer templateIdB;
    private Integer templateIdC;

    public void sendMsg(String[] params, String mobile, String template){
        //手机发送验证码的逻辑
        SmsSingleSender sender = new SmsSingleSender(appId, appKey);
        try {
            switch (template) {
                case "A":
                    //发送短信验证码
                    SmsSingleSenderResult resultA = sender.sendWithParam("86", mobile, templateIdA, params, smsSign, "", "");
                    break;
                case "B":
                    //教练注册成功后发送
                    SmsSingleSenderResult resultB = sender.sendWithParam("86", mobile, templateIdB, params, smsSign, "", "");
                    break;
                case "C":
                    //教练审核失败后发送
                    SmsSingleSenderResult resultC = sender.sendWithParam("86", mobile, templateIdC, params, smsSign, "", "");
                    break;
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
