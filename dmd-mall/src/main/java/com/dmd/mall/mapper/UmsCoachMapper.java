package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsCoach;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 教练表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-07
 */
@Mapper
@Component
public interface UmsCoachMapper extends MyMapper<UmsCoach> {

    UmsCoach selectCoachMessage(Long id);
}
