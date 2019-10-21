package com.dmd.mall.security.sms;

import com.dmd.mall.component.GoAuthenticationFailureHandler;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.util.CodeValidateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SmsCodeFilter extends OncePerRequestFilter {
    private ValidateCodeRepository validateCodeRepository;

    public ValidateCodeRepository getValidateCodeRepository() {
        return validateCodeRepository;
    }

    public void setValidateCodeRepository(ValidateCodeRepository validateCodeRepository) {
        this.validateCodeRepository = validateCodeRepository;
    }

    private GoAuthenticationFailureHandler goAuthenticationFailureHandler=new GoAuthenticationFailureHandler();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equals("/sso/mobile",request.getRequestURI())&&StringUtils.containsIgnoreCase(request.getMethod(),"post")){
            try {
                vailDate(new ServletWebRequest(request));
            }catch (ValidateCodeException e){
                goAuthenticationFailureHandler.onAuthenticationFailure(request,response,e);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

    private void vailDate(ServletWebRequest request) {
        ValidateCode validateCode=validateCodeRepository.get(request);
        String codeInRequest = null;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"code");
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
        CodeValidateUtil.vailDateCode(validateCode,codeInRequest);

        validateCodeRepository.remove(request);
    }
}
