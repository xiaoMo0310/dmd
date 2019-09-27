/**
 * 
 */
package com.dmd.mall.security.redis;

import com.dmd.mall.security.sms.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码存取器
 * 
 * @author zhailiang
 *
 */
public interface ValidateCodeRepository {

	/**
	 * 保存验证码
	 * @param request
	 * @param code
	 */
	void save(ServletWebRequest request, ValidateCode code);
	/**
	 * 获取验证码
	 * @param request
	 * @return
	 */
	ValidateCode get(ServletWebRequest request);
	/**
	 * 移除验证码
	 * @param request
	 */
	void remove(ServletWebRequest request);

}
