package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsMemberLoginLog;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 会员登录记录 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-13
 */
@Mapper
@Component
public interface UmsMemberLoginLogMapper extends MyMapper<UmsMemberLoginLog> {

}
