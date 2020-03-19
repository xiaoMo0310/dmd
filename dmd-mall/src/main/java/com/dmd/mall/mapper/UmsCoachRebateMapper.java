package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsCoachRebate;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 教练返佣表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-19
 */
@Mapper
@Component
public interface UmsCoachRebateMapper extends MyMapper<UmsCoachRebate> {

    List<UmsCoachRebate> selectByCoachAndUserId(@Param( "userId" ) Long userId, @Param( "coachId" ) Long coachId);

    BigDecimal countCoachRebateByCoachId(Long coachId);
}
