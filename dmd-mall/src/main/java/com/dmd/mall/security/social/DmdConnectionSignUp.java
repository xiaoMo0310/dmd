package com.dmd.mall.security.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;
/**
 * 这里自动授权登录后自动根据获取的qq信息创建一个唯一标识，成为用户的登录账号
 *
 * */
@Component
public class DmdConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection) {
        //自定义唯一标识信息，并创造用户信息插入自己用户的表中
        String a=connection.getDisplayName()+"aaaaa";
        return a;
    }
}
