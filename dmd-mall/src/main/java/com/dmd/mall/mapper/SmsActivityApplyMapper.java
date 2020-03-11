package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.SmsActivityApply;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 活动报名表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-11
 */
@Mapper
@Component
public interface SmsActivityApplyMapper extends MyMapper<SmsActivityApply> {

}
