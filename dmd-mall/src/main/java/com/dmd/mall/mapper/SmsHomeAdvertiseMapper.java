package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.SmsHomeAdvertise;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 首页轮播广告表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-10
 */
@Mapper
@Component
public interface SmsHomeAdvertiseMapper extends MyMapper<SmsHomeAdvertise> {

}
