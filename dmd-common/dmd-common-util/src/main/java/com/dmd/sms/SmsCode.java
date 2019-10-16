package com.dmd.sms;

import lombok.Data;
import sun.security.provider.MD5;

/**
 * <p>
 *
 * </p>
 *
 * @author 王海成
 * @since
 */
@Data
public class SmsCode {
    private String username="dycyhy";//用户名
    private String password="2Yj3aBPB";//密码，密码采用32位小写MD5二次加密，例：md5(md5(password)+tkey))
    private String mobile;//手机号码，多个号码中间用英文逗号分隔，每个包最大支持2000个号码，例:13500000000,13000000000,15100000000
    private String content;//短信内容，最多支持1000个字符，例：短信内容【xxx】
    private String tKey;//当前时间，tKey为24小时制的当前时间，所在时区为东八区，精确到秒的10位时间戳格式
    private String time;//定时发送时间（可为空）。若为空，立即发送；若不为空，设置定时字符串日期，定时时间必须大于当前时间10分钟，格式：yyyy-MM-dd HH:mm:ss ，例：2018-06-20 09:00:00。注意：定时时间必须大于当前时间10分钟。
    private String ext;//扩展号。纯数字，最多8位。
    private String extend;//用户自定义信息,在用户获取状态报告时返回
}
