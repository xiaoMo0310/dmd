package com.dmd.mall.service.impl;

import com.dmd.ChineseNickNameUtil;
import com.dmd.PublicUtil;
import com.dmd.RedisKeyUtil;
import com.dmd.base.constant.GlobalConstant;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.base.result.CommonResult;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsMemberMapper;
import com.dmd.mall.model.domain.MemberDetails;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.domain.UmsMemberExample;
import com.dmd.mall.model.vo.UmsMemberVo;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import com.dmd.mall.service.UmsGroupChatService;
import com.dmd.mall.service.UmsIntegrationChangeLogService;
import com.dmd.mall.service.UmsMemberLoginLogService;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.util.CodeValidateUtil;
import com.dmd.mall.util.JwtUtil;
import com.dmd.mall.util.MailUtil;
import com.dmd.sms.SendUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Slf4j
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UmsMemberLoginLogService memberLoginLogService;
    @Autowired
    private UmsIntegrationChangeLogService integrationChangeLogService;
    @Autowired
    private UmsGroupChatService umsGroupChatService;
    @Autowired
    private SendUtil sendUtil;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    private static final String SESSION_KEY="smsCode";
    @Value("${spring.profiles.active}")
    private String profile;

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username).andStatusEqualTo( 2 );
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getByUsernameCoach(String username) {
        return memberMapper.getByUsernameCoach(username);
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult register(String username, String password, String invitationCode, String authCode, HttpServletRequest request) {
        //验证邀请码是否存在
        if (!StringUtils.isEmpty(invitationCode)){
            int verifiedInvitationCode=memberMapper.verifiedInvitationCode(invitationCode);
            if(verifiedInvitationCode != 1 ){
                return CommonResult.failed("邀请码不存在");
            }
        }
        //验证验证码
        ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
        try {
            CodeValidateUtil.vailDateCode(validateCode,authCode);
        }catch (ValidateCodeException e){
            return CommonResult.failed(e.getMessage());
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            return CommonResult.failed("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        if (!username.contains("@")){
            umsMember.setPhone(username);
        }
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(2);
        //获取默认会员等级并设置
        /*UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }*/
        //设置默认昵称
        umsMember.setNickname(ChineseNickNameUtil.generateName());
        //设置默认的头像
        umsMember.setIcon("http://47.107.50.253:8080/webapps/uploadFile/compent/20191208145645.png");
        umsMember.setIntegration(0);
        umsMember.setHistoryIntegration(0);
        memberMapper.insert(umsMember);
        if (invitationCode!=null){
            //在邀请码不等于null的时候自动加入邀请码对应的教练的群
            UmsCoach umsCoach =memberMapper.getCoachUser(invitationCode);
            //调用腾讯接口查询教练群组并加入
            if(!PublicUtil.isEmpty(umsCoach)){
                //添加创建群组信息
                //查询该教练是否未首次绑定
                long memberNum = memberMapper.countNumByInvitationCode(invitationCode);
                if(memberNum == 0){
                    umsGroupChatService.createGroupChartMessage(umsCoach.getPhone(), username, 1);
                }else {
                    umsGroupChatService.createGroupChartMessage(umsCoach.getPhone(), username, 0);
                }
            }

        }
        return CommonResult.success(null, "注册成功");
    }

    @Override
    public CommonResult generateAuthCode(String telephone,HttpServletRequest request) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(telephone), "手机号码不能为空");
        String smsCode = RandomStringUtils.randomNumeric(6);
        //验证码绑定设备id并存储到redis  todo 验证码死的
        ValidateCode validateCode=new ValidateCode("666666",AUTH_CODE_EXPIRE_SECONDS);
        validateCodeRepository.save(new ServletWebRequest(request),validateCode);//保存验证码到redis
//        SmsCode smsCode=new SmsCode();
//        SmsCodeResponse result=null;
        if (telephone.contains("@")){
            try {
                MailUtil.send_mail(telephone,"666666");
            } catch (MessagingException e) {
                logger.info(e.getMessage()+"发送邮件失败");
                validateCodeRepository.remove(new ServletWebRequest(request));
                return CommonResult.failed( "验证码发送失败"+e);
            }
        }else {
            if (GlobalConstant.DEV_PROFILE.equals(profile)) {
                smsCode = "666666";
            }
            if (GlobalConstant.PRO_PROFILE.equals(profile)) {
                // todo 待放开
                /*String[] params = {smsCode};
                sendUtil.sendMsg(params, telephone, "A");
                smsCode = null;*/
            }
        }
        return CommonResult.success("666666", "发送成功");
    }

    @Override
    public CommonResult findPassword(String telephone, String password,String confirmPassword, String authCode,HttpServletRequest request) {
        //验证验证码
        try {
            ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
            CodeValidateUtil.vailDateCode(validateCode,authCode);
        }catch (ValidateCodeException e){
            return CommonResult.failed(e.getMessage());
        }
        return verification(telephone,null,password,confirmPassword);
    }

    @Override
    public CommonResult updatePassword(String telephone, String oldPassword, String newPassword,String confirmPassword) {
        return verification(telephone,oldPassword,newPassword,confirmPassword);
    }

    @Override
    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) auth.getPrincipal();
        return memberDetails.getUmsMember();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized void updateIntegration(UmsMember umsMember, Integer integration, String operateNote, Integer changeType) {
        Preconditions.checkArgument(umsMember != null, "用户信息不能为空");
        //判断用户积分是否充足
        if(umsMember.getIntegration() < -integration && changeType == 1){
            throw new UmsBizException(ErrorCodeEnum.OMS10031015);
        }
        Integer totalIntegration = umsMember.getIntegration();
        umsMember.setHistoryIntegration(totalIntegration);
        umsMember.setIntegration(totalIntegration + integration);
        memberMapper.updateByPrimaryKeySelective(umsMember);
        //记录日志totalIntegration
        integrationChangeLogService.updateIntegrationAndAddLog(umsMember, integration, totalIntegration, operateNote, changeType);
    }

    //短信登陆时如果没有注册进行注册的方法
    @Override
    public UmsMember register(String username) {
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPassword(passwordEncoder.encode("123456"));
        umsMember.setRole("customer");
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        /*//获取默认会员等级并设置
        UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }*/
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        CommonResult.success(null, "登陆成功");
        return umsMember;
    }

    private CommonResult verification(String telephone,String oldPassword,String newPassword,String confirmPassword){
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(telephone);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberList)) {
            return CommonResult.failed("该账号不存在");
        }
        if (!newPassword.equals(confirmPassword)){
            return CommonResult.failed("两次输入的密码不一致");
        }
        UmsMember umsMember = memberList.get(0);
        if (oldPassword!=null&&!passwordEncoder.matches(oldPassword,umsMember.getPassword())){
            return CommonResult.failed("原密码不正确");
        }
        umsMember.setPassword(passwordEncoder.encode(newPassword));
        memberMapper.updateByPrimaryKeySelective(umsMember);
        return CommonResult.success(null, "密码修改成功");
    }
    //更新个人信息
    @Override
    public CommonResult updatePersonalData(UmsMember umsMember ){
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(umsMember.getUsername());
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberList)) {
            return CommonResult.failed("该账号不存在");
        }
        try {
            memberMapper.updateByExampleSelective(umsMember,example);
        }catch (Exception e){
            logger.info(e.getMessage());
            if (e.getCause().getMessage().contains("Duplicate entry '13720096444' for key 'idx_phone")){
                return CommonResult.failed(ErrorCodeEnum.getEnum(10050016));
            }else {
                return CommonResult.failed("更新个人信息时候的未知异常，请联系后台开发人员"+e.getMessage());
            }
        }
        return CommonResult.success(umsMember);
    }

    @Override
    public Set<String> getPermission(String username) {
        return memberMapper.getPermission(username);
    }

    @Override
    public UmsMember selectByLoginAuthDto(LoginAuthDto loginAuthDto) {
        UmsMember umsMember = null;
        if(loginAuthDto.getUserType().equals("member")){
            umsMember = memberMapper.selectById(loginAuthDto.getUserId());
        }
        if(umsMember == null){
            throw new UmsBizException(ErrorCodeEnum.UMS10011003, loginAuthDto.getUserId());
        }
        return umsMember;
    }

    @Override
    public CommonResult updatePhone(String telephone, String authCode, HttpServletRequest request) {
        try {
            ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
            CodeValidateUtil.vailDateCode(validateCode,authCode);
        }catch (ValidateCodeException e){
            return CommonResult.failed(e.getMessage());
        }
        String username=null;
        try {
            username=(String) JwtUtil.getDate(request).get("username");
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success(memberMapper.updatePhone(telephone,username));
    }

    @Override
    public Boolean deleteRedisToken(String accessToken) {
        Boolean delete = redisTemplate.delete(RedisKeyUtil.getAccessTokenKey(accessToken));
        return delete;
    }

    @Override
    public UmsMember selectByUserName(String loginName) {
        return memberMapper.selectByUserName(loginName);
    }

    @Override
    public void handlerLoginData(OAuth2AccessToken token, MemberDetails principal, HttpServletRequest request) {
        String accessToken = token.getValue();
        String refreshToken = token.getRefreshToken().getValue();
        UmsMember umsMember = principal.getUmsMember();
        LoginAuthDto loginAuthDto = new LoginAuthDto(umsMember.getId(), umsMember.getUsername(), umsMember.getNickname(), umsMember.getLoginType(), umsMember.getPhone());
        memberLoginLogService.saveLoginUserLog(accessToken, refreshToken, loginAuthDto, request);
    }

    @Override
    public PageInfo<UmsMemberVo> findCoachInviteUser(BaseQuery baseQuery, String coachInvitationCode) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsMemberVo> umsMemberVos = memberMapper.selectUmsMemberByInvitationCode(coachInvitationCode);
        return new PageInfo<>(umsMemberVos);
    }
}
