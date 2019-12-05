package com.dmd.mall.component;

import com.dmd.base.result.CommonResult;
import com.xiaoleilu.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by macro on 2018/8/6.
 */
@Component
public class GoLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().print(JSONUtil.parse(CommonResult.success(null, "已注销")));
        response.getWriter().flush();
    }
}
