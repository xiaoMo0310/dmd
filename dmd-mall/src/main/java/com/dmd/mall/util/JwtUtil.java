package com.dmd.mall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class JwtUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();
    private static Logger logger= LoggerFactory.getLogger(JwtUtil.class);
    public static Claims getDate(HttpServletRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
        Claims claims = Jwts.parser().setSigningKey("dmd".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        return claims;
    }
}
