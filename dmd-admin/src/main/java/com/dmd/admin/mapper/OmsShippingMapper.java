package com.dmd.admin.mapper;

import com.dmd.admin.model.domain.OmsShipping;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 收货人信息表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-24
 */
@Mapper
@Component
public interface OmsShippingMapper extends MyMapper<OmsShipping> {

}
