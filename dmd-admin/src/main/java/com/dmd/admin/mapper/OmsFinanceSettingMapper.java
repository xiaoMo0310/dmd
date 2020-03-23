package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsFinanceSetting;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 财务设置表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-18
 */
@Mapper
@Component
public interface OmsFinanceSettingMapper extends MyMapper<OmsFinanceSetting> {

}
