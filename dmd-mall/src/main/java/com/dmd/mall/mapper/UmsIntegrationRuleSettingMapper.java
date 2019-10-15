package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsIntegrationRuleSetting;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 积分消费设置 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-15
 */
@Mapper
@Component
public interface UmsIntegrationRuleSettingMapper extends MyMapper<UmsIntegrationRuleSetting> {

}
