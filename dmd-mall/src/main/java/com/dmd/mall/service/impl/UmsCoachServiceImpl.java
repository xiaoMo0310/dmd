package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.ChineseNickNameUtil;
import com.dmd.PublicUtil;
import com.dmd.ShareCodeUtil;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsCoachMapper;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.dto.UmsCoachDto;
import com.dmd.mall.model.dto.UmsCoachRegisterDto;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.mall.util.CodeValidateUtil;
import com.dmd.wrapper.WrapMapper;
import com.dmd.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletWebRequest;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public UmsCoachVo selectCoachMessage(Long id) {
        UmsCoach umsCoach = umsCoachMapper.selectCoachMessage(id);
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
    public Wrapper findPassword(String telephone, String password, String confirmPassword, String authCode, HttpServletRequest request) {
        //验证验证码
        try {
            ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
            CodeValidateUtil.vailDateCode(validateCode,authCode);
        }catch (ValidateCodeException e){
            return WrapMapper.error(e.getMessage());
        }
        return verification(telephone,null,password,confirmPassword);
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
        coach.setCertificatePic(coachRegisterDto.getPhone());
        LoginAuthDto loginAuthDto = new LoginAuthDto();
        loginAuthDto.setUserName(coachRegisterDto.getPhone());
        //如果有审核未通过的信息修改
        if(umsCoach.getStatus() == 3){
            coach.setId(umsCoach.getId());
            coach.setUpdateInfo(loginAuthDto);
            return umsCoachMapper.updateByPrimaryKeySelective(coach);
        }
        coach.setGender(0);
        coach.setCoachGrade(1 + "");
        coach.setStatus(1);
        coach.setInvitationCode(ShareCodeUtil.toSerialCode(Long.valueOf(coachRegisterDto.getPhone())));
        //设置默认昵称
        coach.setNickName(ChineseNickNameUtil.generateName());
        //设置默认的头像
        coach.setIcon("http://47.107.50.253:8080/webapps/uploadFile/compent/20191208145645.png");
        coach.setUpdateInfo(loginAuthDto);
        return umsCoachMapper.insertSelective(coach);
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
