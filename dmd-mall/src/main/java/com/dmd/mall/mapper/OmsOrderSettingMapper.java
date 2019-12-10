package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.OmsOrderSetting;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 订单设置表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-12-10
 */
@Mapper
@Component
public interface OmsOrderSettingMapper extends MyMapper<OmsOrderSetting> {

}
