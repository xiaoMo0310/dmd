package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.ChineseNickNameUtil;
import com.dmd.PublicUtil;
import com.dmd.ShareCodeUtil;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsCoachMapper;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.domain.UmsCoachRebate;
import com.dmd.mall.model.dto.UmsCoachDto;
import com.dmd.mall.model.dto.UmsCoachRegisterDto;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.mall.model.vo.UmsMemberVo;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import com.dmd.mall.service.*;
import com.dmd.mall.util.CodeValidateUtil;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletWebRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 教练表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsCoachServiceImpl extends BaseService<UmsCoach> implements UmsCoachService {

    @Autowired
    private UmsCoachMapper umsCoachMapper;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsCoachIntegrationLogService coachIntegrationLogService;
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsFavoritesService umsFavoritesService;
    @Autowired
    private PmsCourseProductService courseProductService;
    @Autowired
    private UmsCoachRebateService coachRebateService;

    @Override
    public UmsCoachVo selectCoachMessage(Long id) {
        UmsCoach umsCoach = umsCoachMapper.selectById(id);
        if(umsCoach == null){
            throw new UmsBizException(ErrorCodeEnum.UMS10015010, id);
        }
        UmsCoachVo umsCoachVo = new UmsCoachVo();
        BeanUtils.copyProperties(umsCoach, umsCoachVo);
        return umsCoachVo;
    }

    @Override
    public UmsCoach selectByLoginAuthDto(LoginAuthDto loginAuthDto) {
        UmsCoach umsCoach = null;
        if(loginAuthDto.getUserType().equals("coach")){
            umsCoach = umsCoachMapper.selectById(loginAuthDto.getUserId());
        }
        if(umsCoach == null){
            throw new UmsBizException(ErrorCodeEnum.UMS10011003, loginAuthDto.getUserId());
        }
        return umsCoach;
    }

    @Override
    public Wrapper findPassword(String telephone, String newPassword, String confirmPassword, String authCode, HttpServletRequest request) {
        //验证验证码
        try {
            ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
            CodeValidateUtil.vailDateCode(validateCode,authCode);
        }catch (ValidateCodeException e){
            return WrapMapper.error(e.getMessage());
        }
        return verification(telephone,null,newPassword,confirmPassword);
    }

    @Override
    public Wrapper updatePassword(String telephone, String oldPassword, String newPassword, String confirmPassword) {
        return verification(telephone, oldPassword, newPassword, confirmPassword);
    }

    @Override
    public int updateCoachMessage(UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto) {
        //查询教练信息
        UmsCoach umsCoach = umsCoachMapper.selectById(loginAuthDto.getUserId());
        if(PublicUtil.isEmpty(umsCoach)){
            throw new UmsBizException("获取教练信息失败");
        }
        UmsCoach updateCoach = new UmsCoach();
        BeanUtils.copyProperties(umsCoachDto, updateCoach);
        updateCoach.setId(umsCoach.getId());
        return umsCoachMapper.updateByPrimaryKeySelective(updateCoach);
    }

    @Override
    public int register(UmsCoachRegisterDto coachRegisterDto, HttpServletRequest request) {
        //验证验证码
        ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
        try {
            CodeValidateUtil.vailDateCode(validateCode,coachRegisterDto.getAuthCode());
        }catch (ValidateCodeException e){
            throw new UmsBizException(e.getMessage());
        }
        //查询是否已有该用户
        Example example = new Example(UmsCoach.class);
        example.createCriteria().andEqualTo("coachName", coachRegisterDto.getPhone());
        UmsCoach umsCoach = umsCoachMapper.selectOneByExample(example);
        if (umsCoach != null && umsCoach.getStatus() != 3) {
            throw new UmsBizException("用户已经存在");
        }
        //没有该用户进行添加操作
        UmsCoach coach = new UmsCoach();
        coach.setCoachName(coachRegisterDto.getPhone());
        coach.setPhone(coachRegisterDto.getPhone());
        coach.setCertificatePic(coachRegisterDto.getCertificatePic());
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserName(coachRegisterDto.getPhone());
        coach.setStatus(1);
        //如果有审核未通过的信息修改
        if(!PublicUtil.isEmpty(umsCoach)){
            if(umsCoach.getStatus() == 3){
                coach.setId(umsCoach.getId());
                coach.setUpdateInfo(loginAuthDto);
                return umsCoachMapper.updateByPrimaryKeySelective(coach);
            }
        }
        coach.setGender(0);
        coach.setCoachGrade(1 + "");
        coach.setInvitationCode(ShareCodeUtil.toSerialCode(Long.valueOf(coachRegisterDto.getPhone())));
        //设置默认昵称
        coach.setNickName(ChineseNickNameUtil.generateName());
        //设置默认的头像
        coach.setIcon("http://47.107.50.253:8080/webapps/uploadFile/compent/20191208145645.png");
        coach.setIntegration(0);
        coach.setHistoryIntegration(0);
        coach.setUpdateInfo(loginAuthDto);
        return umsCoachMapper.insertSelective(coach);
    }

    @Override
    public synchronized void updateIntegration(UmsCoach umsCoach, Integer integration, String operateNote, int changeType) {
        Preconditions.checkArgument(umsCoach != null, "用户信息不能为空");
        //判断用户积分是否充足
        if(umsCoach.getIntegration() < -integration && changeType == 1){
            throw new UmsBizException(ErrorCodeEnum.OMS10031015);
        }
        Integer totalIntegration = umsCoach.getIntegration();
        umsCoach.setHistoryIntegration(totalIntegration);
        umsCoach.setIntegration(totalIntegration + integration);
        umsCoachMapper.updateByPrimaryKeySelective(umsCoach);
        //记录日志totalIntegration
        coachIntegrationLogService.updateIntegrationAndAddLog(umsCoach, integration, totalIntegration, operateNote, changeType);
    }

    @Override
    public UmsCoachVo findUmsCoachByCoachId(LoginAuthDto loginAuthDto) {
        UmsCoach umsCoach = umsCoachMapper.selectByPrimaryKey(loginAuthDto.getUserId());
        UmsCoachVo umsCoachVo = new UmsCoachVo();
        BeanUtils.copyProperties(umsCoach, umsCoachVo);
        return umsCoachVo;
    }

    @Override
    public UmsCoachVo findCoachMessageAndCountNum(LoginAuthDto loginAuthDto) {
        //查询教练信息
        UmsCoachVo umsCoachVo = this.findUmsCoachByCoachId(loginAuthDto);
        //统计教练邀请得人数
        PageInfo<UmsMemberVo> memberVoPageInfo = memberService.findCoachInviteUser(new BaseQuery(), umsCoachVo.getInvitationCode());
        //统计关注人数
        Integer totalFollow = umsFavoritesService.queryFavoritesCount(loginAuthDto.getUserId(), loginAuthDto);
        //查询发布商品总数
        Long totalProduct = courseProductService.countSellerProductNum(umsCoachVo.getId());
        umsCoachVo.setTotalInvitations(memberVoPageInfo.getTotal());
        umsCoachVo.setTotalFollow(totalFollow);
        umsCoachVo.setTotalProduct(totalProduct);
        return umsCoachVo;
    }

    @Override
    public JSONObject findCoachInviteUserMessage(BaseQuery baseQuery, LoginAuthDto loginAuthDto) {
        UmsCoachVo coach = this.findUmsCoachByCoachId(loginAuthDto);
        PageInfo<UmsMemberVo> umsMemberVoPageInfo = memberService.findCoachInviteUser( baseQuery, coach.getInvitationCode() );
        umsMemberVoPageInfo.getList().forEach( umsMemberVo -> {
            //查询教练的佣金信息
            List<UmsCoachRebate> coachRebates = coachRebateService.selectByCoachAndUserId(umsMemberVo.getUserId(), coach.getId());
            //计算用户总提供佣金
            BigDecimal contribution = coachRebates.stream().map( UmsCoachRebate::getRebateAmount ).reduce( BigDecimal.ZERO, BigDecimal::add );
            umsMemberVo.setContribution( contribution );
        });
        //统计教练所有的佣金
        BigDecimal totalContribution = coachRebateService.countCoachRebateByCoachId(coach.getId());
        JSONObject object = new JSONObject();
        object.put( "list", umsMemberVoPageInfo.getList() );
        object.put( "total", umsMemberVoPageInfo.getTotal() );
        object.put( "totalContribution", totalContribution );
        return object;
    }

    @Override
    public UmsCoach selectCoachMessageByInvitationCode(String invitationCode) {
        return umsCoachMapper.selectByInvitationCode(invitationCode);
    }

    private Wrapper verification(String telephone,String oldPassword,String newPassword,String confirmPassword){
        Example example = new Example(UmsCoach.class);
        example.createCriteria().andEqualTo("coachName", telephone);
        UmsCoach umsCoach = umsCoachMapper.selectOneByExample(example);
        if (PublicUtil.isEmpty(umsCoach)) {
            return WrapMapper.error("该账号不存在");
        }
        if (!newPassword.equals(confirmPassword)){
            return WrapMapper.error("两次输入的密码不一致");
        }
        if (oldPassword!=null&&!passwordEncoder.matches(oldPassword,umsCoach.getPassword())){
            return WrapMapper.error("原密码不正确");
        }
        umsCoach.setPassword(passwordEncoder.encode(newPassword));

        umsCoachMapper.updateByPrimaryKeySelective(umsCoach);
        return WrapMapper.wrap(200, "密码修改成功");
    }
}
