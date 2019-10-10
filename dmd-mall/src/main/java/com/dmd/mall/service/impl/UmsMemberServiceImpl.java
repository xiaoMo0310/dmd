package com.dmd.mall.service.impl;

import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.base.result.CommonResult;
import com.dmd.mall.mapper.UmsMemberMapper;
import com.dmd.mall.model.domain.MemberDetails;
import com.dmd.mall.model.domain.UmsMember;
import com.dmd.mall.model.domain.UmsMemberExample;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import com.dmd.mall.service.RedisService;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.util.CodeValidateUtil;
import com.dmd.mall.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 会员管理Service实现类
 * Created by macro on 2018/8/3.
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private UmsMemberMapper memberMapper;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    /*@Autowired
    private UmsMemberLevelMapper memberLevelMapper;*/
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    private static final String SESSION_KEY="smsCode";

    @Override
    public UmsMember getByUsername(String username) {
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsMember> memberList = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(memberList)) {
            return memberList.get(0);
        }
        return null;
    }

    @Override
    public UmsMember getById(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public CommonResult register(String username, String password, String telephone, String authCode, HttpServletRequest request) {
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
        example.or(example.createCriteria().andPhoneEqualTo(telephone));
        List<UmsMember> umsMembers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsMembers)) {
            return CommonResult.failed("该用户已经存在");
        }
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPhone(telephone);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);
        //获取默认会员等级并设置
        /*UmsMemberLevelExample levelExample = new UmsMemberLevelExample();
        levelExample.createCriteria().andDefaultStatusEqualTo(1);
        List<UmsMemberLevel> memberLevelList = memberLevelMapper.selectByExample(levelExample);
        if (!CollectionUtils.isEmpty(memberLevelList)) {
            umsMember.setMemberLevelId(memberLevelList.get(0).getId());
        }*/
        memberMapper.insert(umsMember);
        umsMember.setPassword(null);
        return CommonResult.success(null, "注册成功");
    }

    @Override
    public CommonResult generateAuthCode(String telephone,HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定设备id并存储到redis
        ValidateCode validateCode=new ValidateCode(sb.toString(),AUTH_CODE_EXPIRE_SECONDS);
        validateCodeRepository.save(new ServletWebRequest(request),validateCode);
        if (telephone.contains("@")){
            try {
                MailUtil.send_mail(telephone,sb.toString());
            } catch (MessagingException e) {
                logger.info(e.getMessage()+"发送邮件失败");
                return CommonResult.failed( "验证码发送失败"+e);
            }
        }else {
            //发送手机验证码的逻辑
            logger.info("这里写手机验证码发送的逻辑");
        }
        return CommonResult.success(sb.toString(), "获取验证码成功");//在真实环境下需要把验证码的数据改成null不该也行可以让app再证一次
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
    public void updateIntegration(Long id, Integer integration) {
        UmsMember record = new UmsMember();
        record.setId(id);
        record.setIntegration(integration);
        memberMapper.updateByPrimaryKeySelective(record);
    }


    //短信登陆时如果没有注册进行注册的方法
    @Override
    public UmsMember register(String username) {
        //没有该用户进行添加操作
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPassword(passwordEncoder.encode("123456"));
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
}
