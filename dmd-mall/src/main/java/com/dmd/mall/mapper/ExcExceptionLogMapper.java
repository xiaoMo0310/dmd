package com.dmd.mall.mapper;

import com.dmd.core.mybatis.MyMapper;
import com.dmd.mall.model.domain.ExcExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 全局异常记录 Mapper 接口
 * </p>
 *
 * @author YangAnsheng123
 * @since 2019-09-18
 */
@Mapper
@Component
public interface ExcExceptionLogMapper extends MyMapper<ExcExceptionLog> {

}
