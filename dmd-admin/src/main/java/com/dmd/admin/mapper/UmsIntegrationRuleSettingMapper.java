package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.UmsIntegrationRuleSetting;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 积分消费设置 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2020-03-18
 */
@Mapper
@Component
public interface UmsIntegrationRuleSettingMapper extends MyMapper<UmsIntegrationRuleSetting> {

}
