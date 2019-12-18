package com.dmd.mall.service;

import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.dto.UmsCoachDto;
import com.dmd.mall.model.dto.UmsCoachRegisterDto;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.wrapper.Wrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 教练表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
public interface UmsCoachService extends IService<UmsCoach> {

    /**
     * 根据id查询教练的信息
     * @param id
     * @return
     */
    UmsCoachVo selectCoachMessage(Long id);

    UmsCoach selectByLoginAuthDto(LoginAuthDto loginAuthDto);

    /**
     * 找回密码
     * @param telephone
     * @param password
     * @param confirmPassword
     * @param authCode
     * @param request
     * @return
     */
    Wrapper findPassword(String telephone, String password, String confirmPassword, String authCode, HttpServletRequest request);

    /**
     *修改密码
     * @param telephone
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    Wrapper updatePassword(String telephone, String oldPassword, String newPassword, String confirmPassword);

    /**
     * 修改教练信息
     * @param umsCoachDto
     * @param loginAuthDto
     * @return
     */
    int updateCoachMessage(UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto);

    /**
     * 教练注册
     * @param coachRegisterDto
     * @param request
     * @return
     */
    int register(UmsCoachRegisterDto coachRegisterDto, HttpServletRequest request);
}
