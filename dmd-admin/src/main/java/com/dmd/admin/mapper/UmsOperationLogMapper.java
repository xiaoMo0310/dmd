package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsOperationLog;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-11-17
 */
@Mapper
@Component
public interface UmsOperationLogMapper extends MyMapper<UmsOperationLog> {

    List<UmsOperationLog> getOperationLog(UmsOperationLog umsOperationLog);
    int addOperationLog(UmsOperationLog umsOperationLog);
}
