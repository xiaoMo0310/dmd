package com.dmd.admin.service;

import com.alibaba.fastjson.JSONObject;
import com.dmd.admin.model.domain.UmsCoach;
import com.dmd.admin.model.dto.UmsCoachDto;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsCoachVo;
import com.dmd.base.dto.BaseQuery;
import com.dmd.base.dto.LoginAuthDto;
import com.dmd.core.support.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 教练表  服务类
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-17
 */
public interface UmsCoachService extends IService<UmsCoach> {

    /**
     * 批量修改用户的状态
     * @param ids
     * @param status
     * @return
     */
    int batchUpdateCoachStatus(List<Long> ids, Integer status);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    UmsCoachVo selectCoachById(Long id);

    /**
     * 修改用户的状态信息
     * @param id
     * @param status
     * @return
     */
    int updateCoachStatus(Long id, Integer status);

    /**
     * 查询用户列表信息
     * @param userQueryParam
     * @return
     */
    PageInfo selectCoachList(UmsUserQueryParam userQueryParam);

    /**
     * 查询审核通过和未通过的信息
     * @return
     * @param baseQuery
     */
    PageInfo selectCoachByStatus(BaseQuery baseQuery);

    /**
     * 修改教练的审核信息
     * @param umsCoachDto
     * @param loginAuthDto
     * @return
     */
    int updateCoachMessageById(UmsCoachDto umsCoachDto, LoginAuthDto loginAuthDto);

    /**
     * 统计当日新注册的教练
     * @return
     */
    JSONObject countDayRegisterCoach();

    /**
     * 统计昨日访问教练的数量
     * @return
     */
    Long countYesterdayVisitCoach();

    /**
     *
     * @return
     */
    Long countTotalCoach();

    /**
     * 统计平台的总教练量
     * @param day
     * @return
     */
    JSONObject countCoachRetentionRate(Integer day);

    /**
     * 统计教练三十日留存率
     * @param day
     * @return
     */
    JSONObject countCoachThirtyDayRetentionRate(Integer day);
}
