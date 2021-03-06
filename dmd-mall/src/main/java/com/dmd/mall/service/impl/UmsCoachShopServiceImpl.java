package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsCoachShopMapper;
import com.dmd.mall.model.domain.UmsCoachShop;
import com.dmd.mall.model.dto.UmsCoachShopDto;
import com.dmd.mall.model.vo.UmsCoachShopVo;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.mall.model.vo.UmsMemberVo;
import com.dmd.mall.security.redis.ValidateCodeRepository;
import com.dmd.mall.security.sms.ValidateCode;
import com.dmd.mall.security.sms.ValidateCodeException;
import com.dmd.mall.service.UmsCoachService;
import com.dmd.mall.service.UmsCoachShopService;
import com.dmd.mall.service.UmsMemberService;
import com.dmd.mall.util.CodeValidateUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 教练店铺店铺表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-02-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsCoachShopServiceImpl extends BaseService<UmsCoachShop> implements UmsCoachShopService {

    @Autowired
    private UmsCoachShopMapper umsCoachShopMapper;
    @Autowired
    private ValidateCodeRepository validateCodeRepository;
    @Autowired
    private UmsMemberService umsMemberService;
    @Autowired
    private UmsCoachService umsCoachService;

    @Override
    public UmsCoachShop findByCoachId(Long coachId) {
        return umsCoachShopMapper.selectByCoachId( coachId );
    }

    @Override
    public int saveOrEditShopMessage(LoginAuthDto loginAuthDto, UmsCoachShopDto umsCoachShopDto, HttpServletRequest request) {
        if(loginAuthDto.getUserName().equals( "member" )){
            throw new UmsBizException( ErrorCodeEnum.GL99990403 );
        }
        UmsCoachShop umsCoachShop = new UmsCoachShop();
        BeanUtils.copyProperties( umsCoachShopDto, umsCoachShop );
        int resultInt;
        umsCoachShop.setUpdateInfo(loginAuthDto);
        if (umsCoachShop.isNew()) {
            umsCoachShop.setCoachId(loginAuthDto.getUserId());
            resultInt = umsCoachShopMapper.insertSelective(umsCoachShop);
        } else {
            //如果修改手机号码检验验证码是否正确
            if(umsCoachShop.getTelephone() != null){
                ValidateCode validateCode=validateCodeRepository.get(new ServletWebRequest(request));
                try {
                    CodeValidateUtil.vailDateCode(validateCode,umsCoachShopDto.getAuthCode());
                }catch (ValidateCodeException e){
                    throw new UmsBizException(e.getMessage());
                }
            }
            resultInt = umsCoachShopMapper.updateByPrimaryKeySelective(umsCoachShop);
        }
        return resultInt;
    }

    @Override
    public UmsCoachShopVo findShopMessage(LoginAuthDto loginAuthDto) {
        if(!loginAuthDto.getUserType().equals( "coach" )){
            throw new UmsBizException( ErrorCodeEnum.UMS10011023 );
        }
        UmsCoachShop umsCoachShop = umsCoachShopMapper.selectByCoachId( loginAuthDto.getUserId() );
        UmsCoachShopVo umsCoachShopVo = new UmsCoachShopVo();
        BeanUtils.copyProperties( umsCoachShop, umsCoachShopVo );
        UmsCoachVo coach = umsCoachService.findUmsCoachByCoachId( loginAuthDto );
        //统计教练粉丝数
        PageInfo<UmsMemberVo> coachInviteUser = umsMemberService.findCoachInviteUser( new BaseQuery(), coach.getInvitationCode() );
        umsCoachShopVo.setFansNumber( coachInviteUser.getTotal() );
        return umsCoachShopVo;
    }
}
