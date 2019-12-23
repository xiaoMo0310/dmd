package com.dmd.mall.service;

import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.result.CommonResult;
import com.dmd.mall.model.domain.MemberDetails;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.vo.UmsMemberVo;
import com.github.pagehelper.PageInfo;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * 会员管理Service
 * Created by macro on 2018/8/3.
 */
public interface UmsMemberService {
    /**
     * 根据用户名获取会员(客户端)
     */
    UmsMember getByUsername(String username);
    /**
     * 根据用户名获取会员(教练端)
     */
    UmsMember getByUsernameCoach(String username);

    /**
     * 根据会员编号获取会员
     */
    UmsMember getById(Long id);

    /**
     * 用户注册
     */
    @Transactional
    CommonResult register(String username, String password, String telephone, String authCode, HttpServletRequest request);

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone,HttpServletRequest request);

    /**
     * 修改密码
     */
    @Transactional
    CommonResult updatePassword(String telephone, String oldPassword,String newPassword,String confirmPassword);
    /**
     * 修改密码
     */
    @Transactional
    CommonResult findPassword(String telephone, String password,String confirmPassword, String authCode,HttpServletRequest request);

    /**
     * 获取当前登录会员
     */
    UmsMember getCurrentMember();

    /**
     * 扣减用户积分
     * @param umsMember
     * @param integration
     * @param operateNote
     */
    void updateIntegration(UmsMember umsMember, Integer integration, String operateNote, Integer changeType);
    /**
     * 在手机号或邮箱登陆时，没有账号的话注册账号用的
     * */
    @Transactional
    UmsMember register(String username);



    /**
     * 修改个人资料
     * */
    CommonResult updatePersonalData(UmsMember umsMember);

    Set<String> getPermission(String username);


    /**
     * 根据登录人信息查询
     * @param loginAuthDto
     * @return
     */
    UmsMember selectByLoginAuthDto(LoginAuthDto loginAuthDto);
    @Transactional
    CommonResult updatePhone(String telephone,String authCode,HttpServletRequest request);

    /**
     * 退出登录删除redis token
     * @param accessToken
     */
    Boolean deleteRedisToken(String accessToken);

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    UmsMember selectByUserName(String loginName);

    /**
     * 处理用户登录数据
     * @param token
     * @param principal
     * @param request
     */
    void handlerLoginData(OAuth2AccessToken token, MemberDetails principal, HttpServletRequest request);

    /**
     * 查询教练邀请得人
     * @param baseQuery
     * @return
     */
    PageInfo<UmsMemberVo> findCoachInviteUser(BaseQuery baseQuery, String coachInvitationCode);
}
