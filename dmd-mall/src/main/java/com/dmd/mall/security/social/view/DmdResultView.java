package com.dmd.mall.security.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DmdResultView extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        httpServletResponse.setContentType("application/json;charset=utf-8");
        if (map.get("connect")!=null){
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString("绑定成功"));
        }else{
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString("解绑成功"));
        }
    }
}
