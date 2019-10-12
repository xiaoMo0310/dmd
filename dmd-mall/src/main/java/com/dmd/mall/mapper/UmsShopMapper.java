package com.dmd.mall.mapper;

import com.dmd.mall.model.domain.UmsShop;
import com.dmd.core.mybatis.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author YangAnsheng
 * @since 2019-10-11
 */
@Mapper
@Component
public interface UmsShopMapper extends MyMapper<UmsShop> {

}
