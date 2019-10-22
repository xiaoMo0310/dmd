package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsIntegrationChangeLog;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 积分变化历史记录表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-18
 */
@Mapper
@Component
public interface UmsIntegrationChangeLogMapper extends MyMapper<UmsIntegrationChangeLog> {


    List<UmsIntegrationChangeLog> selectIntegralExpend(@Param("userId")Long userId,@Param("startTime")Date startTime, @Param("endTime")Date endTime);

    Integer selectIntegralIncomeSum(Long userId);

    Integer selectIntegralExpendSum(Long userId);

    List<UmsIntegrationChangeLog> selectIntegralIncome(@Param("userId")Long userId, @Param("startTime")Date startTime, @Param("endTime")Date endTime);
}
