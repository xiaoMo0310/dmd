package com.dmd.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dmd.BeanUtils;
import com.dmd.DateUtil;
import com.dmd.admin.mapper.UmsMemberLoginLogMapper;
import com.dmd.admin.mapper.UmsMemberMapper;
import com.dmd.admin.model.domain.UmsMember;
import com.dmd.admin.model.domain.UmsMemberExample;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsUserVo;
import com.dmd.admin.service.UmsMemberService;
import com.dmd.base.dto.BaseQuery;
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
        JSONObject jsonObject = new JSONObject();
        if(CollectionUtils.isEmpty(members)){
            jsonObject.put("seven", 0);
            return jsonObject;
        }
        List<Long> userIds = members.stream().map(UmsMember::getId).collect(Collectors.toList());
        //查询7天的留存数
        Map map = umsMemberLoginLogMapper.selectRetentionNumber(firstDate, userIds, "member");
        if(map ==null){
            jsonObject.put("seven", 0);
            return jsonObject;
        }
        long sevenDayLogin = Long.parseLong(String.valueOf(map.get("day7")));
        //查询当天新增的用户数
        double seven = sevenDayLogin / (double) members.size();
        seven = Double.valueOf(new BigDecimal(seven).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        jsonObject.put("seven", seven);
        return jsonObject;
    }

    @Override
    public JSONObject countThirtyDayRetentionRate(Integer day) {
        //第7日留存率：（当天新增的用户中，新增日之后的第7天还登录的用户数）/第一天新增总用户数
        //计算起始的时间
        Date firstDate = DateUtil.dateshit("yyyy-MM-dd", new Date(), -day);
        //查询注册的人
        List<UmsMember> members = umsMemberMapper.selectRegisterUser(firstDate);
        JSONObject jsonObject = new JSONObject();
        if(CollectionUtils.isEmpty(members)){
            jsonObject.put("thirty", 0);
            return jsonObject;
        }
        List<Long> userIds = members.stream().map(UmsMember::getId).collect(Collectors.toList());
        Map map = umsMemberLoginLogMapper.selectRetentionNumber(firstDate, userIds, "member");
        if(map ==null){
            jsonObject.put("thirty", 0);
            return jsonObject;
        }
        long thirtyDayLogin = Long.parseLong(String.valueOf(map.get("day30")));
        double thirty = thirtyDayLogin / (double) members.size();
        thirty = Double.valueOf(new BigDecimal(thirty).setScale(2, RoundingMode.DOWN).multiply(new BigDecimal("100")).toString());
        jsonObject.put("thirty", thirty);
        return jsonObject;
    }

    @Override
    public UmsUserVo selectUserById(Long id) {
        UmsMember umsMember = umsMemberMapper.selectByPrimaryKey(id);
        UmsUserVo umsUserVo = new UmsUserVo();
        BeanUtils.copyProperties(umsMember, umsUserVo);
        return umsUserVo;
    }

    @Override
    public PageInfo findByInvitationCode(String invitationCode, BaseQuery baseQuery) {
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        List<UmsUserVo> userVos = umsMemberMapper.selectByInvitationCode(invitationCode);
        return new PageInfo(userVos);
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

    @Override
    public int batchUpdateUserStatus(List<Long> ids, Integer status) {
        UmsMember record = new UmsMember();
        record.setStatus(status);
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andIdIn(ids);
        return umsMemberMapper.updateByExampleSelective(record, example);
    }


}
