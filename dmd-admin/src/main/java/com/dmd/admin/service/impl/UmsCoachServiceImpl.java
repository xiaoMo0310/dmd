package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.BeanUtils;
import com.dmd.DateUtil;
import com.dmd.RandomUtil;
import com.dmd.admin.mapper.UmsCoachMapper;
import com.dmd.admin.mapper.UmsMemberLoginLogMapper;
import com.dmd.admin.model.domain.UmsCoach;
import com.dmd.admin.model.domain.UmsMemberExample;
import com.dmd.admin.model.dto.UmsCoachDto;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsCoachVo;
import com.dmd.admin.service.UmsCoachService;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.BaseService;
import com.dmd.sms.SendUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private UmsMemberLoginLogMapper umsMemberLoginLogMapper;
    @Autowired
    private SendUtil sendUtil;


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
        UmsCoach umsCoach = umsCoachMapper.selectByPrimaryKey(umsCoachDto.getCoachId());
        BeanUtils.copyProperties(umsCoachDto, umsCoach);
        umsCoach.setUpdateInfo(loginAuthDto);
        if(umsCoachDto.getStatus() == 2){
            //生成默认登录密码并
            String numberCode = RandomUtil.createNumberCode(8);
            //umsCoach.setPassword(passwordEncoder.encode(numberCode));
            umsCoach.setPassword(passwordEncoder.encode(12345678 + ""));
            umsCoach.setFailureReason(null);
            //发送短信通知 todo 待放开
            /*String[] params = {numberCode};
            sendUtil.sendMsg(params, umsCoach.getPhone(), "B");*/
        }else if (umsCoachDto.getStatus() == 3){
            //发送短信通知注册失败 todo 待放开
            /*String[] params = {umsCoachDto.getFailureReason()};
            sendUtil.sendMsg(params, umsCoach.getPhone(), "C");*/
        }
        int result = umsCoachMapper.updateByPrimaryKeySelective(umsCoach);
        return result;
    }

    @Override
    public JSONObject countDayRegisterCoach() {
        Long dayRegister = (long) umsCoachMapper.selectRegisterCoach(new Date()).size();
        Date yesterday = DateUtil.dateshit("yyyy-MM-dd", new Date(), -1);
        Long yesterdayRegister = (long) umsCoachMapper.selectRegisterCoach(yesterday).size();
        JSONObject object = new JSONObject();
        object.put("today", dayRegister);
        object.put("yesterday", yesterdayRegister);
        return object;
    }

    @Override
    public Long countYesterdayVisitCoach() {
        Date date = DateUtil.dateshit("yyyy-MM-dd", new Date(), -1);
        return umsCoachMapper.countYesterdayVisitCoach(date);
    }

    @Override
    public Long countTotalCoach() {
        return umsCoachMapper.countTotalCoach();
    }

    @Override
    public JSONObject countCoachRetentionRate(Integer day) {
        //第7日留存率：（当天新增的用户中，新增日之后的第7天还登录的用户数）/第一天新增总用户数
        //计算起始的时间
        Date firstDate = DateUtil.dateshit("yyyy-MM-dd", new Date(), -day);
        //查询注册的人
        List<UmsCoach> coachs = umsCoachMapper.selectRegisterCoach(firstDate);
        JSONObject jsonObject = new JSONObject();
        if(CollectionUtils.isEmpty(coachs)){
            jsonObject.put("seven", 0);
            return jsonObject;
        }
        List<Long> coachIds = coachs.stream().map(UmsCoach::getId).collect(Collectors.toList());
        //查询7天的留存数
        Map map = umsMemberLoginLogMapper.selectRetentionNumber(firstDate, coachIds, "coach");
        if(map ==null){
            jsonObject.put("seven", 0);
            return jsonObject;
        }
        long sevenDayLogin = Long.parseLong(String.valueOf(map.get("day7")));
        //查询当天新增的用户数
        double seven = sevenDayLogin / (double) coachs.size();
        seven = Double.valueOf(new BigDecimal(seven).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        jsonObject.put("seven", seven);
        return jsonObject;
    }

    @Override
    public JSONObject countCoachThirtyDayRetentionRate(Integer day) {
        //第7日留存率：（当天新增的用户中，新增日之后的第7天还登录的用户数）/第一天新增总用户数
        //计算起始的时间
        Date firstDate = DateUtil.dateshit("yyyy-MM-dd", new Date(), -day);
        //查询注册的人
        List<UmsCoach> coachs = umsCoachMapper.selectRegisterCoach(firstDate);
        JSONObject jsonObject = new JSONObject();
        if(CollectionUtils.isEmpty(coachs)){
            jsonObject.put("thirty", 0);
            return jsonObject;
        }
        List<Long> coachIds = coachs.stream().map(UmsCoach::getId).collect(Collectors.toList());
        Map map = umsMemberLoginLogMapper.selectRetentionNumber(firstDate, coachIds, "coach");
        if(map ==null){
            jsonObject.put("thirty", 0);
            return jsonObject;
        }
        long thirtyDayLogin = Long.parseLong(String.valueOf(map.get("day30")));
        double thirty = thirtyDayLogin / (double) coachs.size();
        thirty = Double.valueOf(new BigDecimal(thirty).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        jsonObject.put("thirty", thirty);
        return jsonObject;
    }

    public static void main(String[] args) {
        String numberCode = RandomUtil.createNumberCode(8);
        System.out.println(numberCode);
    }
}
