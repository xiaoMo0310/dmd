package com.dmd.sms;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 * 发送验证码返回结果类
 * @author 王海成
 * @since
 */
@Data
public class SmsCodeResponse {
    private String code;
    private String msg;
    private String msgId;
    private String contNum;
}
