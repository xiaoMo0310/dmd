package com.dmd.mall.util;

import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import org.apache.commons.lang.StringUtils;

public class CodeValidateUtil {
    public static void vailDateCode(ValidateCode validateCode, String codeInRequest){
        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码的值不能为空");
        }
        if (validateCode==null){
            throw new ValidateCodeException("验证码不存在");
        }
        if (validateCode.isExpried()){
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInRequest,validateCode.getCode())){
            throw new ValidateCodeException("验证码错误");
        }
    }

}
