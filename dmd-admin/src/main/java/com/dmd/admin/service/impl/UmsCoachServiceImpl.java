package com.dmd.admin.service.impl;

import com.dmd.BeanUtils;
import com.dmd.RandomUtil;
import com.dmd.admin.mapper.UmsCoachMapper;
import com.dmd.admin.model.domain.UmsCoach;
import com.dmd.admin.model.domain.UmsMemberExample;
import com.dmd.admin.model.dto.UmsCoachDto;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsCoachVo;
import com.dmd.admin.service.UmsCoachService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 教练表  服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsCoachServiceImpl extends BaseService<UmsCoach> implements UmsCoachService {

    @Autowired
    private UmsCoachMapper umsCoachMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int batchUpdateCoachStatus(List<Long> ids, Integer status) {
        UmsCoach umsCoach = new UmsCoach();
        umsCoach.setStatus(status);
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andIdIn(ids);
        return umsCoachMapper.updateByExampleSelective(umsCoach, example);
    }

    @Override
    public UmsCoachVo selectCoachById(Long id) {
        UmsCoach umsCoach = umsCoachMapper.selectByPrimaryKey(id);
        UmsCoachVo umsCoachVo = new UmsCoachVo();
        BeanUtils.copyProperties(umsCoach, umsCoachVo);
        return umsCoachVo;
    }

    @Override
    public int updateCoachStatus(Long id, Integer status) {
        UmsCoach umsCoach = new UmsCoach();
        umsCoach.setId(id);
        umsCoach.setStatus(status);
        return umsCoachMapper.updateByPrimaryKeySelective(umsCoach);
    }

    @Override
    public PageInfo selectCoachList(UmsUserQueryParam userQueryParam) {
        PageHelper.startPage(userQueryParam.getPageNum(), userQueryParam.getPageSize());
        List<UmsCoachVo> umsCoaches = umsCoachMapper.selectCoachList(userQueryParam);
        return new PageInfo(umsCoaches);
    }

    @Override
    public PageInfo selectCoachByStatus(BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsCoachVo> umsCoaches = umsCoachMapper.selectCoachByStatus();
        return new PageInfo(umsCoaches);
    }

    @Override
    public int updateCoachMessageById(UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto) {
        UmsCoach umsCoach = new UmsCoach();
        BeanUtils.copyProperties(umsCoachDto, umsCoach);
        umsCoach.setId(umsCoachDto.getCoachId());
        umsCoach.setUpdateInfo(loginAuthDto);
        if(umsCoachDto.getStatus() == 2){
            //生成默认登录密码并
            String numberCode = RandomUtil.createNumberCode(8);
            //umsCoach.setPassword(passwordEncoder.encode(numberCode));
            umsCoach.setPassword(passwordEncoder.encode(12345678 + ""));
            umsCoach.setFailureReason(null);
            //发送短信通知 todo 待做
        }else if (umsCoachDto.getStatus() == 3){
            //发送短信通知注册失败 todo 待做
        }
        int result = umsCoachMapper.updateByPrimaryKeySelective(umsCoach);
        return result;
    }

    public static void main(String[] args) {
        String numberCode = RandomUtil.createNumberCode(8);
        System.out.println(numberCode);
    }
}
