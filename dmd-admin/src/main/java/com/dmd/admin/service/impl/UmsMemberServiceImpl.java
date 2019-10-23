package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.DateUtil;
import com.dmd.admin.mapper.UmsMemberLoginLogMapper;
import com.dmd.admin.mapper.UmsMemberMapper;
import com.dmd.admin.model.domain.UmsMember;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.service.UmsMemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private UmsMemberLoginLogMapper umsMemberLoginLogMapper;

    @Override
    public JSONObject countDayRegisterUser() {
        Long dayRegister = (long) umsMemberMapper.selectRegisterUser(new Date()).size();
        Date yesterday = DateUtil.dateshit("yyyy-MM-dd", new Date(), -1);
        Long yesterdayRegister = (long) umsMemberMapper.selectRegisterUser(yesterday).size();
        JSONObject object = new JSONObject();
        object.put("today", dayRegister);
        object.put("yesterday", yesterdayRegister);
        return object;
    }

    @Override
    public Long countYesterdayVisitUser(){
        Date date = DateUtil.dateshit("yyyy-MM-dd", new Date(), -1);
        return umsMemberLoginLogMapper.countYesterdayVisitUser(date);
    }

    @Override
    public Long countTotalUser() {
        return umsMemberMapper.countTotalUser();
    }

    @Override
    public JSONObject countRetentionRate(Integer day){
        //第7日留存率：（当天新增的用户中，新增日之后的第7天还登录的用户数）/第一天新增总用户数
        //计算起始的时间
        Date firstDate = DateUtil.dateshit("yyyy-MM-dd", new Date(), -day);
        //查询注册的人
        List<UmsMember> members = umsMemberMapper.selectRegisterUser(firstDate);
        if(CollectionUtils.isEmpty(members)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("seven", 0);
            jsonObject.put("thirty", 0);
            return jsonObject;
        }
        List<Long> userIds = members.stream().map(UmsMember::getId).collect(Collectors.toList());
        //查询7天的留存数
        Map map = umsMemberLoginLogMapper.selectRetentionNumber(firstDate, userIds);
        long sevenDayLogin = Long.parseLong(String.valueOf(map.get("day7")));
        long thirtyDayLogin = Long.parseLong(String.valueOf(map.get("day30")));
        //查询当天新增的用户数
        double seven = sevenDayLogin / (double) members.size();
        seven = Double.valueOf(new BigDecimal(seven).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        double thirty = thirtyDayLogin / (double) members.size();
        thirty = Double.valueOf(new BigDecimal(thirty).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        JSONObject object = new JSONObject();
        object.put("seven", seven);
        object.put("thirty", thirty);
        return object;
    }

    @Override
    public PageInfo selectUserList(UmsUserQueryParam userQueryParam) {
        PageHelper.startPage(userQueryParam.getPageNum(), userQueryParam.getPageSize());
        List<UmsMember> userList = umsMemberMapper.selectUserList(userQueryParam);
        return new PageInfo(userList);
    }

    @Override
    public int updateUserStatus(Long id, Integer status) {
        UmsMember umsMember = new UmsMember();
        umsMember.setId(id);
        umsMember.setStatus(status);
        return umsMemberMapper.updateByPrimaryKeySelective(umsMember);
    }

}
