package com.dmd.mall.service.impl;

import com.dmd.BeanUtils;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.base.enums.ErrorCodeEnum;
import com.dmd.core.support.BaseService;
import com.dmd.mall.exceptions.UmsBizException;
import com.dmd.mall.mapper.UmsCoachMapper;
import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.mall.model.vo.UmsCoachVo;
import com.dmd.mall.service.UmsCoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            umsCoach = umsCoachMapper.selectByPrimaryKey(loginAuthDto.getUserId());
        }
        if(umsCoach == null){
            throw new UmsBizException(ErrorCodeEnum.UMS10011003, loginAuthDto.getUserId());
        }
        return umsCoach;
    }
}
