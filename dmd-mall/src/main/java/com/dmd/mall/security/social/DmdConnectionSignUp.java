package com.dmd.mall.security.social;

import com.dmd.ChineseNickNameUtil;
import com.dmd.core.utils.ThreadLocalUtil;
import com.dmd.mall.mapper.UmsMemberMapper;
import com.dmd.mall.model.domain.UmsMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * 这里自动授权登录后自动根据获取的qq信息创建一个唯一标识，成为用户的登录账号
 *
 * */
@Component
public class DmdConnectionSignUp implements ConnectionSignUp {
    @Autowired
    private UmsMemberMapper memberMapper;
    @Override
    public String execute(Connection<?> connection) {
        //自定义唯一标识信息，并创造用户信息插入自己用户的表中
        String loginType= ThreadLocalUtil.threadLocal.get().get("loginType");
        String userId = null;
        if (loginType.equals("member")){
            UmsMember umsMember = new UmsMember();
            userId=connection.getDisplayName()+ UUID.randomUUID();
            umsMember.setUsername(userId);
            umsMember.setCreateTime(new Date());
            umsMember.setStatus(2);
            //设置默认昵称
            umsMember.setNickname(connection.getDisplayName());
            //设置默认的头像
            umsMember.setIcon(connection.getImageUrl());
            umsMember.setIntegration(0);
            umsMember.setHistoryIntegration(0);
            memberMapper.insert(umsMember);
        }else if (loginType.equals("coach")){

        }
        return userId;
    }
}
