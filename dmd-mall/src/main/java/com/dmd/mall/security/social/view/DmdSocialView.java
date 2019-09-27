package com.dmd.mall.security.social.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
@Component("connect/status")
public class DmdSocialView extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, List<Connection<?>>> listMap= (Map<String, List<Connection<?>>>) map.get("connectionMap");
        Map<String, Boolean> result=new HashedMap();
        for (String s : listMap.keySet()) {
            result.put(s, CollectionUtils.isNotEmpty(listMap.get(s)));
        }
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
