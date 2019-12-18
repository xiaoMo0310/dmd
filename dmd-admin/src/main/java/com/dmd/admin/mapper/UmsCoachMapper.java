package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsCoach;
import com.dmd.admin.model.dto.UmsUserQueryParam;
import com.dmd.admin.model.vo.UmsCoachVo;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 教练表  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-17
 */
@Mapper
@Component
public interface UmsCoachMapper extends MyMapper<UmsCoach> {

    List<UmsCoachVo> selectCoachList(UmsUserQueryParam userQueryParam);

    List<UmsCoachVo> selectCoachByStatus();
}
