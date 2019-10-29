package com.dmd.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-21
 */
public interface UmsMemberService{

    /**
     * 统计当日新注册的用户
     * @return
     */
    JSONObject countDayRegisterUser();

    /**
     * 统计昨日用户访问数量
     * @return
     * @throws ParseException
     */
    Long countYesterdayVisitUser();

    /**
     * 总的注册人数
     * @return
     */
    Long countTotalUser();

    /**
     * 统计七日留存率
     * @return
     * @throws ParseException
     */
    JSONObject countRetentionRate(Integer day) ;

    /**
     * 分页查询用户信息
     * @param userQueryParam
     * @return
     */
    PageInfo selectUserList(UmsUserQueryParam userQueryParam);

    /**
     * 修改用户状态
     * @param id
     * @param status
     * @return
     */
    int updateUserStatus(Long id, Integer status);

    /**
     * 批量修改用户状态
     * @param ids
     * @param status
     * @return
     */
    int batchUpdateUserStatus(List<Long> ids, Integer status);

    /**
     * 统计三十日留存lv
     * @param day
     * @return
     */
    JSONObject countThirtyDayRetentionRate(Integer day);
}
