package com.dmd.admin.service;

import com.alibaba.fastjson.JSONObject;

import java.text.ParseException;

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
    Long countYesterdayVisitUser() throws ParseException;

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
    JSONObject countRetentionRate(Integer day) throws ParseException;

}
